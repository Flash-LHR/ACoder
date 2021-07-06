import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: resolve => require(['../pages/Login.vue'], resolve)
    },
    {
      path: '/Home',
      component: resolve => require(['../components/Home.vue'], resolve),
      children:[
        {
          path: '/Info',
          component: resolve => require(['../pages/InfoPage.vue'], resolve)
        },
        {
          path: '/Activity',
          component: resolve => require(['../pages/Activity.vue'], resolve)
        },
        {
          path: '/Chapter',
          component: resolve => require(['../pages/Chapter.vue'], resolve)
        },
        {
          path: '/Content',
          component: resolve => require(['../pages/Content.vue'], resolve)
        },
        {
          path: '/Problem',
          component: resolve => require(['../pages/Problem.vue'], resolve)
        }
      ]
    }
  ]
})
