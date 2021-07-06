import Vue from 'vue'
import Vuex from 'vuex'
import configure from './configure'
import user from './user'
import song from './song'
import login from './login'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        configure,
        user,
        login,
        song,
    }
})

export default store