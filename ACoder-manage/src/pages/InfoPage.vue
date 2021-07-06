<template>
    <div>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{activityCount}}</div>
                            <div>活动总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{chapterCount}}</div>
                            <div>章节总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{contentCount}}</div>
                            <div>内容总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{problemCount}}</div>
                            <div>题目总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">题目状态饼状图</h3>
                <div style="background-color:white">
                    <ve-pie :data="problemStatus"></ve-pie>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">题目状态柱状图</h3>
                <div style="background-color:white">
                    <ve-histogram :data="problemStatus"></ve-histogram>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import {getAllActivity,getAllChapter,getAllContent,getAllProblem} from '../api/index';
export default {
    data(){
        return {
            activityCount: 0,
            chapterCount: 0,
            contentCount: 0,
            problemCount: 0,
            problemStatus:{
                columns: ['题目状态','总数'],
                rows: [  
                    {'题目状态': 'UA','总数': 0},                  
                    {'题目状态': 'AC','总数': 0},
                    {'题目状态': 'PE','总数': 0},
                    {'题目状态': 'WA','总数': 0},
                    {'题目状态': 'TLE','总数': 0},
                    {'题目状态': 'MLE','总数': 0},
                    {'题目状态': 'OLE','总数': 0},
                    {'题目状态': 'RE','总数': 0},
                    {'题目状态': 'CE','总数': 0},
                    {'题目状态': 'SF','总数': 0},
                    {'题目状态': 'Q','总数': 0},
                ]
            },
        }
    },
    created() {

    },
    mounted() {
        this.getActivity();
        this.getChapter();
        this.getContent();
        this.getProblem();
    },
    methods: {
        getActivity() {
            getAllActivity().then(res => {
                this.activityCount = res.length;
            })
        }, 
        getChapter() {
            getAllChapter().then(res => {
                this.chapterCount = res.length;
            })
        },
        getContent() {
            getAllContent().then(res => {
                this.contentCount = res.length;
            })
        },
        getProblem() {
            getAllProblem().then(res => {
                this.problemCount = res.length;
                for(let item of res) {
                    this.getByProblemStatus(item.problemStatus);
                }
            })
        },
        getByProblemStatus(problemstatus) {
            for(let item of this.problemStatus.rows) {
                if(problemstatus.includes(item['题目状态'])) {
                    item['总数']++;
                }
            }
        }
    }
}

</script>

<style scoped>
.grid-content {
    display: flex;
    align-items: center;
    height: 50px;
}

.grid-cont-center {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: darkgray;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}
</style>