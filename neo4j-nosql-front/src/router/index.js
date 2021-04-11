import Vue from 'vue'
import Router from 'vue-router'
import NosqlFront from '@/views/NosqlFront/index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'NosqlFront',
      component: NosqlFront,
      meta:{
        // 页面标题title
        title: '非关系形数据库可视化操作'
      }
    }
  ]
})
