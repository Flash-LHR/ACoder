<template>
    <div class="song-list-album">
        <div class="album-slide">
            <div class="album-img">
                <img :src="attachImageUrl(tempList.pic)">
            </div>
            <div class="album-info">
                <h2>简介：</h2>
                <span>
                    {{tempList.introduction}}
                </span>
            </div>
        </div>
        <div class="album-content">
            <div class="album-title">
                <p>{{tempList.title}}</p>
            </div>
            <div class="songs-body">
                <album-content :songList="listOfSongs">
                    <template slot="title">歌单</template>
                </album-content>
                <comment :playId="songListId" :type="1"></comment>
            </div>
        </div>        
    </div>
</template>
<script>
import {mixin} from '../mixins';
import {mapGetters} from 'vuex';
import {listSongDetail,songOfSongId,setRank,getRankOfSongListId} from '../api/index';
import AlbumContent from "../components/AlbumContent";
import Comment from "../components/Comment";

export default {
    name: 'song-list-album',
    mixins: [mixin],
    components:{
        AlbumContent,
        Comment
    },
    data(){
        return {
            songLists: [],      //当前页面需要展示的歌曲列表
            songListId: ''     //前面传来的歌单id
        }
    },
    computed:{
        ...mapGetters([
            'listOfSongs',      //当前播放列表
            'tempList',         //当前歌单对象
            'loginIn',          //用户是否已登录
            'userId',           //当前登录用户id
        ])
    },
    created(){
        this.songListId = this.$route.params.id;
        this.getSongId();
        this.getRank(this.songListId);
    },
    methods:{
        //获取当前歌单的歌曲列表
        getSongId(){
            listSongDetail(this.songListId)
                .then(res => {
                    for(let item of res){
                        this.getSongList(item.songId);
                    }
                    this.$store.commit('setListOfSongs',this.songLists);
                })
                .catch(err =>{
                    console.log(err)
                })
        },
        //根据歌曲id获取歌曲信息
        getSongList(id){
            songOfSongId(id)
                .then(res => {
                    this.songLists.push(res);
                })
                .catch(err =>{
                    console.log(err)
                })
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/song-list-album.scss';
</style>
