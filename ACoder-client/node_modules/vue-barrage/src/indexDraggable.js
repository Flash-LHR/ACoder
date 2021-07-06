/*
 * @Author: your name
 * @Date: 2019-07-13 20:48:47
 * @LastEditTime: 2019-12-26 23:35:42
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: /vue-draggable/src/index.js
 */
/**
 * @author Nickyzhang
 * @date 2019/7/13
 * @description file templates
 */
import Draggable from './draggable.vue'

Draggable.install = function (Vue) {
    Vue.component('Draggable', Draggable)
}

export default Draggable