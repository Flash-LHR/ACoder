<template>
    <div class="gossip-block">
        <h1 class="gossip-title">吐槽区</h1>
        <div class="gossip-ground">
            <!-- <div class="gossip-cnt">{{fuck}}</div> -->
            <vue-baberrage
            :isShow="barrageIsShow"
            :barrageList="barrageList"
            :maxWordCount="maxWordCount"
            :throttleGap="throttleGap"
            :loop="barrageLoop"
            :boxHeight="boxHeight"
            :messageHeight="messageHeight">
            </vue-baberrage>
            <!-- <embed src="../assets/video/gossip-movie.mp4" height="100%" width="100%"> -->
            <video class="gossip-video" poster="../assets/img/load.png" loop autoplay="autoplay" muted>
                <source src="../assets/video/gossip-movie.mp4" type="video/mp4">
            </video>
        </div>
        <el-input v-model="input" size="mini"  placeholder="尽情吐槽，释放压力~" @keyup.enter.native="readySendGossip()" maxlength="30" show-word-limit class="gossip-input"></el-input>
        <el-button id="el-btn" size="mini" @click="readySendGossip()">发送弹幕</el-button>
        <!-- <h1>{{fuck}}</h1> -->
    </div>
</template>

<script>
import Vue from 'vue';
import {mapGetters} from 'vuex';
import { vueBaberrage, MESSAGE_TYPE } from 'vue-baberrage';
Vue.use(vueBaberrage);
export default {
    name: 'Barrages',
    data() {
        return {
            fuck: 0,
            input: '',
            gossip: '享受音乐，享受生活~',
            barrageIsShow: true,
            messageHeight: 30,
            boxHeight: 300,
            barrageLoop: true,
            maxWordCount: 3,
            throttleGap: 5000,
            barrageList: []
        };
    },
    computed:{
      ...mapGetters([
          'activeName',
          'loginIn',
          'avator'
      ])
    },
    mounted() {
        this.sendGossip();
    },
    methods: { 
        setSex(sex,val) {              //根据性别获取用户数
            let count = 0;
            for(let item of val){
                if(sex == item.sex){
                    count++;
                }
            }
            return count;
        },
        //获取图片地址
        attachImageUrl (srcUrl) {
            return srcUrl? this.$store.state.configure.HOST+srcUrl: '../assets/img/user.jpg';
        },
        readySendGossip() {
            this.gossip = this.input;
            this.sendGossip();
            this.input = '';
            this.gossip = '享受音乐，享受生活~';
        },
        sendGossip() {
            this.fuck = this.fuck + 1;
            let list = [
                {
                    id: 1,
                    avatar: this.attachImageUrl(this.avator),
                    msg: this.gossip,
                    time: parseInt(Math.random() * (4 - 3 + 1) + 2, 20),
                    barrageStyle: 'red'
                },
            ];
            if(list[0].avatar == "../assets/img/user.jpg")
                list[0].avatar = "http://127.0.0.1:8888//img/user.jpg";
            list.forEach((v) => {
                this.barrageList.push({
                id: v.id,
                avatar: v.avatar,
                msg: v.msg,
                time: v.time,
                type: MESSAGE_TYPE.NORMAL,
                barrageStyle: v.barrageStyle
                });
            });
        }
    }
}
</script>

<style scoped>
.gossip-title {
    margin-top: 20px;
}
.gossip-ground {
    border-radius: 10px;
    position: relative;
    left: 200px;
    margin-top: 20px;
    width: 800px;
    height: 400px;
    background-size: 100% 100%;
    /* background: url('../assets/video/gossip-movie.mov'); */
    background-color:#1E90FF;
}
.gossip-input {
    width: 800px;
}

.gossip-cnt {
    font-size: 50px;
    transition: 3s;
}

.gossip-cnt:hover {
    font-size: 200px;
}

.gossip-video {
    height: inherit;
    width: inherit;
    object-fit: fill;
    border-radius: 10px;
}
</style>

<style lang="scss" scoped>
@import '../assets/css/gossip.scss';
</style>