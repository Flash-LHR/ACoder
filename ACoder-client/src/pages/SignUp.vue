<template>
<div>
    <loginLogo/>
    <div class="signUp">
        <div class="signUp-head">
            <span>用户注册</span>
        </div>
        <el-form :model="registerForm" ref="registerForm" label-width="70px" class="demo-ruleForm" :rules="rules">
            <el-form-item prop="username" label="用户名">
                <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="密码">
                <el-input type="password" v-model="registerForm.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item prop="sex" label="性别">
                <el-radio-group v-model="registerForm.sex">
                    <el-radio :label="0">女</el-radio>
                    <el-radio :label="1">男</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item prop="phoneNum" label="手机">
                <el-input v-model="registerForm.phoneNum" placeholder="手机"></el-input>
            </el-form-item>
            <el-form-item prop="email" label="邮箱">
                <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="birth" label="生日">
                <el-date-picker v-model="registerForm.birth" placeholder="选择日期" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item prop="introduction" label="签名">
                <el-input v-model="registerForm.introduction" placeholder="签名"></el-input>
            </el-form-item>
            <el-form-item prop="location" label="地区">
                <el-select v-model="registerForm.location" placeholder="地区" style="width: 100%;">
                    <el-option v-for=" item in cities" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item prop="verify" label="验证码">
                <drag-verify v-on:update="updateIsPassing()"
                  ref="dragVerify"
                  :width="280"
                  :height="40"
                  progressBarBg="#FFA500"
                  background="#F5F5F5"
                  text="请按住滑块拖动"
                  successText="验证通过"
                  handlerIcon="el-icon-d-arrow-right"
                  successIcon="el-icon-circle-check"
                  @update:isPassing="updateIsPassing">
          </drag-verify>
            </el-form-item>
            <div class="login-btn">
                <el-button :disabled="!isPassing" type="primary" @click="SignUp">确定</el-button>
                <el-button type="danger" @click="clearInput">重置</el-button>
            </div>
        </el-form>
    </div>
</div>
</template>
<script>
import dragVerify from '../../node_modules/vue-drag-verify/src/dragVerify.vue'
// import dragVerify from 'vue-drag-verify'
import loginLogo from '../components/LoginLogo'
import {rules,cities} from '../assets/data/form'
import {mixin} from '../mixins'
import {SignUp} from '../api/index'

export default {
    name: 'sing-up',
    mixins: [mixin],
    components: {
        'drag-verify': dragVerify,
        // dragVerify,
        loginLogo,
    },
    data() {
        return {
            isPassing: false,
            registerForm: {
                username: '',       //用户名
                password: '',       //密码
                sex: '',            //性别
                phoneNum: '',       //手机
                email: '',          //邮箱
                birth: '',          //生日
                introduction: '',   //签名
                location: ''       //地区
            },
            cities: [],            //所有的地区--省
            rules: {}               //表单提交的规则
        }        
    },
    created() {
        this.rules = rules;
        this.cities = cities;
    },
    methods:{
        updateIsPassing(val) {
            this.isPassing = val;
            this.$refs.dragVerify.isPassing = val;
        },
        clearInput() {
            this.registerForm.username = '';
            this.registerForm.password = '';
            this.registerForm.sex = '';
            this.registerForm.phoneNum = '';
            this.registerForm.email = '';
            this.registerForm.birth = '';
            this.registerForm.introduction = '';
            this.registerForm.location = '';
            this.isPassing = false;
            this.$refs.dragVerify.isPassing = false;
            if(this.$refs.dragVerify != undefined){
                this.$refs.dragVerify.reset();
            }
        },
        SignUp(){
            let _this = this;
            let d = this.registerForm.birth;
            let datetime = d.getFullYear() + '-' +(d.getMonth() + 1) +'-' + d.getDate();
            let params = new URLSearchParams();
            params.append('username',this.registerForm.username);
            params.append('password',this.registerForm.password);
            params.append('sex',this.registerForm.sex);
            params.append('phoneNum',this.registerForm.phoneNum);
            params.append('email',this.registerForm.email);
            params.append('birth',datetime);
            params.append('introduction',this.registerForm.introduction);
            params.append('location',this.registerForm.location);
            params.append('avator','/img/user.jpg');
            SignUp(params)
                .then(res => {
                    if(res.code == 1){
                        this.$store.commit('setLoginUsername', this.registerForm.username);
                        this.$store.commit('setLoginPassword', this.registerForm.password);
                        // console.log(this.$store.getters.loginUsername);
                        // console.log(this.$store.getters.loginPassword);
                        _this.notify('注册成功','success');
                        setTimeout(function(){
                            _this.$router.push({path: '/login-in'});
                        },2000);
                    }else{
                        _this.notify('注册失败','error');
                    }
                })
                .catch(err => {
                    _this.notify('注册失败','error');
                })
        },
        goback(index){
            this.$router.go(index);
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/sign-up.scss';
</style>