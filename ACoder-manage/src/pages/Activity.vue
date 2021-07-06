<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入活动名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加活动</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="680px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="activityName" label="活动" width="180" align="center"></el-table-column>
            <el-table-column label="活动管理" width="110" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="activityEdit(scope.row.activityID)">活动管理</el-button>
                </template>
            </el-table-column>
            <el-table-column label="备注" align="center">
                <template slot-scope="scope">
                    <el-progress :width="100" :height="100" type="circle" :percentage="scope.row.percentage" :status="scope.row.status"></el-progress>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button> 
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination 
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange" 
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pageSize"
                background layout="total, sizes, prev, pager, next, jumper"
                :current-page="currentPage"
                :total="tableData.length">
            </el-pagination>
        </div>

        <el-dialog title="添加活动" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px">
                <el-form-item prop="activityName" label="活动名" size="mini">
                    <el-input v-model="registerForm.activityName" placeholder="活动名"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="addActivity">确定</el-button>
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改活动" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px">
                <el-form-item prop="activityName" label="活动名" size="mini">
                    <el-input v-model="form.activityName" placeholder="活动名"></el-input>
                </el-form-item>             
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editSave">确定</el-button>                
                <el-button size="mini" @click="editVisible = false">取消</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除活动" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复，是否确定删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="deleteRow">确定</el-button>                
                <el-button size="mini" @click="delVisible = false">取消</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {getAllProblem,setActivity, getAllActivity,updateActivity,delActivity} from '../api/index';
import { mixin } from '../mixins/index';
export default {
    mixins: [mixin],
    data(){
        return{
            centerDialogVisible: false, //添加弹窗是否显示
            editVisible: false,         //编辑弹窗是否显示
            delVisible: false,          //删除弹窗是否显示
            registerForm:{      //添加框
                activityID: '',
                activityName: ''
            },
            form:{      //编辑框
                id: '',
                activityID: '',
                activityName: '',
            },
            tableData: [],
            tempData: [],
            select_word: '',
            pageSize: 5,    //分页每页大小
            currentPage: 1,  //当前页
            idx: -1,          //当前选择项
            multipleSelection: []   //哪些项已经打勾
        }
    },
    computed:{
        //计算当前搜索结果表里的数据
        data(){
            return this.tableData.slice((this.currentPage - 1) * this.pageSize,this.currentPage * this.pageSize)
        }
    },
    watch:{
        //搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
        select_word: function(){
            if(this.select_word == ''){
                this.tableData = this.tempData;
            }else{
                this.tableData = [];
                for(let item of this.tempData){
                    if(item.activityName.includes(this.select_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.getData();
    },
    methods:{
        handleSizeChange(val) {
            this.pageSize = val;
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
        },
        //查询所有活动
        getData(){
            this.tempData = [];
            this.tableData = [];

            getAllActivity().then(list1 => {
                getAllProblem().then(list2 => {
                    for(this.i = 0; this.i < list1.length; ++this.i) {
                        list1[this.i].num = 0;
                        list1[this.i].percentage = 0;
                        for(this.j = 0; this.j < list2.length; ++this.j) {
                            if(list1[this.i].activityID == list2[this.j].activityID) {
                                ++list1[this.i].num;
                                if(list2[this.j].problemStatus == "AC") 
                                    ++list1[this.i].percentage;
                            }
                        }
                        if(list1[this.i].num == 0)      list1[this.i].percentage = 100;
                        else                            list1[this.i].percentage = parseInt(list1[this.i].percentage * 100 / list1[this.i].num);
                        if(list1[this.i].num == 0)                  list1[this.i].status = "";
                        else if(list1[this.i].percentage == 100)    list1[this.i].status = "success";
                    }
                    this.tempData = list1;
                    this.tableData = list1;
                    this.currentPage = 1;
                })
            })

            
        },
        //添加活动
        addActivity(){
            let params = new URLSearchParams();
            this.registerForm.activityID = this.tableData.length + 1;
            params.append('activityID',this.registerForm.activityID);
            params.append('activityName',this.registerForm.activityName);

            setActivity(params)
            .then(res => {
                if(res.code == 1){
                    this.getData();
                    this.notify("添加成功","success");
                }else{
                    this.notify("添加失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.centerDialogVisible = false;
        },
        //弹出编辑页面
        handleEdit(row){
            this.editVisible = true;
            this.form = {
                id: row.id,
                activityID: row.activityID,
                activityName: row.activityName
            }
        },
        //保存编辑页面修改的数据
        editSave(){
            let params = new URLSearchParams();
            params.append('id',this.form.id);
            params.append('activityID',this.form.activityID);
            params.append('activityName',this.form.activityName);

            updateActivity(params)
            .then(res => {
                if(res.code == 1){
                    this.getData();
                    this.notify("修改成功","success");
                }else{
                    this.notify("修改失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.editVisible = false;
        },
        //删除一个活动
        deleteRow(){
            delActivity(this.idx)
            .then(res => {
                if(res){
                    this.getData();
                    this.notify("删除成功","success");
                }else{
                    this.notify("删除失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.delVisible = false;
        },
        //转向章节管理页面
        activityEdit(activityID){
            this.$router.push({path:`/Chapter`,query:{activityID}});
        }
    }
}
</script>

<style scoped>
    .handle-box{
        margin-bottom: 20px;
    }
    .singer-img{
        width: 100%;
        height: 80px;
        border-radius: 5px;
        margin-bottom: 5px;
        overflow: hidden;
    }
    .handle-input{
        width: 300px;
        display: inline-block;
    }
    .pagination{
        display: flex;
        justify-content: center;
    }
</style>