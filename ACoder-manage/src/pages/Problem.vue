<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入题目名" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加题目</el-button>
            </div>
        </div>
        <el-table size="mini" ref="multipleTable" border style="width:100%" height="680px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="活动名" width="150" align="center">{{activityName}}</el-table-column>
            <el-table-column label="章节名" width="150" align="center">{{chapterName}}</el-table-column>
            <el-table-column label="内容名" width="180" align="center">{{contentName}}</el-table-column>
            <el-table-column label="题目名" width="230" align="center">
                <template slot-scope="scope">
                    <a target="_blank" :href="scope.row.problemLink" class="aProblemName">
                        {{scope.row.problemName}}
                    </a>
                </template>
            </el-table-column>
            <!-- <el-table-column prop="problemLink" label="题目链接" width="280" align="center"></el-table-column> -->
            <el-table-column label="题目状态" width="110" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" :type="getByProblemStatus(scope.row.problemStatus)">{{scope.row.problemStatus}}</el-button>
                </template>
            </el-table-column>
            <el-table-column label="备注" align="center">
                <template slot-scope="scope">
                    <p style="height:100px;">{{scope.row.introduction}}</p>
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

        <el-dialog title="添加题目" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" action="" id="tf">
                <div>
                    <label>活动名</label>
                    <el-input disabled :placeholder="activityName"></el-input>
                </div>
                <div>
                    <label>章节名</label>
                    <el-input disabled :placeholder="chapterName"></el-input>
                </div>
                <div>
                    <label>内容名</label>
                    <el-input disabled :placeholder="contentName"></el-input>
                </div>
                <div>
                    <label>题目名</label>
                    <el-input type="text" v-model="registerForm.problemName"></el-input>
                </div>
                <div>
                    <label>题目链接</label>
                    <el-input type="text" v-model="registerForm.problemLink"></el-input>
                </div>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="addProblem">确定</el-button>                
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改题目" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px">
                <el-form-item prop="activityName" label="活动名" size="mini">
                    <el-input disabled :placeholder="activityName"></el-input>
                </el-form-item>
                <el-form-item prop="chapterName" label="章节名" size="mini">
                    <el-input disabled :placeholder="chapterName"></el-input>
                </el-form-item>     
                <el-form-item prop="contentName" label="内容名" size="mini">
                    <el-input disabled :placeholder="contentName"></el-input>
                </el-form-item>                
                <el-form-item prop="problemName" label="题目名" size="mini">
                    <el-input v-model="form.problemName" placeholder="题目名"></el-input>
                </el-form-item>
                <el-form-item prop="problemLink" label="题目链接" size="mini">
                    <el-input v-model="form.problemLink" placeholder="题目链接"></el-input>
                </el-form-item>
                <el-form-item prop="problemStatus" label="题目状态" size="mini">
                    <el-select v-model="form.problemStatus">
                        <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editSave">确定</el-button>                
                <el-button size="mini" @click="editVisible = false">取消</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除题目" :visible.sync="delVisible" width="300px" center>
            <div align="center">删除不可恢复，是否确定删除？</div>
            <span slot="footer">
                <el-button size="mini" @click="deleteRow">确定</el-button>                
                <el-button size="mini" @click="delVisible = false">取消</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mixin } from '../mixins/index';
import '@/assets/js/iconfont.js';
import {getAllActivity,getAllChapter,getAllContent,setProblem,problemOfActivityIDAndChapterIDAndContentID,updateProblem,delProblem} from '../api/index';

export default {
    mixins: [mixin],
    data(){
        return{
            options: [{
                value: 'UA',
                label: 'UA'
            },
            {
                value: 'AC',
                label: 'AC'
            },
            {
                value: 'WA',
                label: 'WA'
            },
            {
                value: 'RE',
                label: 'RE'
            },
            {
                value: 'SF',
                label: 'SF'
            },
            {
                value: 'TLE',
                label: 'TLE'
            },
            {
                value: 'MLE',
                label: 'MLE'
            },
            {
                value: 'PE',
                label: 'PE'
            },
            {
                value: 'OLE',
                label: 'OLE'
            },
            {
                value: 'CE',
                label: 'CE'
            },
            {
                value: 'Q',
                label: 'Q'
            }],
            activityID: '',
            activityName: '',
            chapterID: '',
            chapterName: '',
            contentID: '',
            contentName: '',
            problemID: '',
            problemName: '',
            problemLink: '',
            problemStatus: '',
            centerDialogVisible: false, //添加弹窗是否显示
            editVisible: false,         //编辑弹窗是否显示
            delVisible: false,          //删除弹窗是否显示
            registerForm:{      //添加框
                activityID: '',
                chapterID: '',
                contentID: '',
                problemID: '',
                problemName: '',
                problemLink: '',
            },
            form:{      //编辑框
                activityID: '',
                chapterID: '',
                contentID: '',
                problemID: '',
                problemName: '',
                problemLink: '',
            },
            tableData: [],
            tempData: [],
            select_word: '',
            pageSize: 5,    //分页每页大小
            currentPage: 1,  //当前页
            idx: -1,          //当前选择项
            multipleSelection: [],   //哪些项已经打勾
            toggle: false           //播放器的图标状态
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
                    if(item.problemName.includes(this.select_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.activityID = this.$route.query.activityID;
        this.chapterID = this.$route.query.chapterID;
        this.contentID = this.$route.query.contentID;
        this.getData();
    },
    methods:{
        handleSizeChange(val) {
            this.pageSize = val;
        },
        getByProblemStatus(problemStatus) {
            if(problemStatus == "AC") return "success";
            else if(problemStatus == "UA") return "info";
            return "danger";
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
        },
        //查询所有章节
        getData(){
            getAllActivity().then(res => {
                this.i = 0;
                while(this.i < res.length && 
                    !(res[this.i].activityID == this.activityID)) ++this.i;
                if(this.i < res.length) this.activityName = res[this.i].activityName;
            })

            getAllChapter().then(res => {
                this.i = 0;
                while(this.i < res.length && 
                    !(res[this.i].activityID == this.activityID &&
                     res[this.i].chapterID == this.chapterID)) ++this.i;
                if(this.i < res.length) this.chapterName = res[this.i].chapterName;
            })

            getAllContent().then(res => {
                this.i = 0;
                while(this.i < res.length && 
                    !(res[this.i].activityID == this.activityID &&
                      res[this.i].chapterID == this.chapterID &&
                      res[this.i].contentID == this.contentID)) ++this.i;
                if(this.i < res.length) this.contentName = res[this.i].contentName;
            })

            this.tempData = [];
            this.tableData = [];
            problemOfActivityIDAndChapterIDAndContentID(this.activityID, this.chapterID, this.contentID).then(res => {
                this.tempData = res;
                this.tableData = res;
                this.currentPage = 1;
            })
        },
        //添加题目
        addProblem(){
            let params = new URLSearchParams();
            this.registerForm.problemID = this.tableData.length + 1;
            params.append('problemID', this.registerForm.problemID);
            params.append('problemName', this.registerForm.problemName);
            params.append('problemLink', this.registerForm.problemLink);
            params.append('problemStatus', 'UA');
            params.append('contentID',this.contentID);
            params.append('activityID', this.activityID);
            params.append('chapterID', this.chapterID);
            setProblem(params)
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
                problemID: row.problemID,
                problemName: row.problemName,
                problemLink: row.problemLink,
                problemStatus: row.problemStatus,
                contentID: this.contentID,
                activityID: this.activityID,
                chapterID: this.chapterID,
            }
        },
        //保存编辑页面修改的数据
        editSave(){
            let params = new URLSearchParams();
            params.append('id',this.form.id);
            params.append('problemID', this.form.problemID);
            params.append('problemName', this.form.problemName);
            params.append('problemLink', this.form.problemLink);
            params.append('problemStatus', this.form.problemStatus);
            params.append('contentID', this.contentID);
            params.append('chapterID',this.chapterID);
            params.append('activityID',this.activityID);
            updateProblem(params)
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
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.HOST}/song/updateSongPic?id=${id}`
        },
        //删除一个章节
        deleteRow(){
            delProblem(this.idx)
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
        }
    }   
}
</script>

<style scoped>
    .my-autocomplete li{
        line-height: normal;
        padding: 7px;
    }

    .my-autocomplete .problemStatus {
        text-overflow: ellipsis;
        overflow: hidden;
    }



    a{
        text-decoration: none;
        color:#333;
        
    }

    .aProblemName:hover {
        text-decoration:underline;
    }

    .handle-box{
        margin-bottom: 20px;
    }
    .song-img{
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
    .play {
        position: absolute;
        z-index: 100;
        width: 80px;
        height: 80px;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        top: 18px;
        left: 15px;
    }

    .icon {
        width: 2em;
        height: 2em;
        color: white;
        fill: currentColor;
        overflow: hidden;
    }
</style>