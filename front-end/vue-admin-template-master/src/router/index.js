import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/hospSet',
    component: Layout,
    redirect: '/hospSet',
    name: '医院设置管理',
    meta: { title: '医院设置管理', icon: 'example' },
    children: [
      {
        path: 'register',
        name: 'register',
        component: () => import('@/views/hospset/register'),
        meta: { title: '医院注册', icon: 'form' }
      },
      
      {
        path: 'list',
        name: '医院列表',
        component: () => import('@/views/hospset/list'),
        meta: { title: '医院列表', icon: 'table' },
        children: [
          
        ]
        
      }, 

      {
          path: 'hospital/show/:hoscode',
          name: '医院详情',
          component: () => import('@/views/hospset/show'),
          meta: { title: '医院详情', noCache: true },
          hidden: true
      },

      {
          path: 'hospital/schedule/:hoscode',
          name: '医院排班',
          component: () => import('@/views/hospset/depschel'),
          meta: { title: '医院排班', noCache: true },
          hidden: true
        }
      
    ]
  },

  {
    path: '/dict',
    component: Layout,
    redirect: '/dict',
    children: [
      {
        path: 'list',
        name: '数据字典管理',
        component: () => import('@/views/dict/list'),
        meta: { title: '数据字典管理', icon: 'tree' }
      }
    ]
  },

  {
    path: '/hospUser',
    component: Layout,
    redirect: '/hospUser',
    name: 'userInfo',
    meta: { title: '用户管理', icon: 'table' },
    // alwaysShow: true,
    children: [
      {
        path: 'userInfo/list',
        name: '用户列表',
        component: () =>import('@/views/hospUser/info'),
        meta: { title: '用户列表', icon: 'table' }
      }
    ]
  },



  {
    path: '/hospital',
    component: Layout,
    redirect: '/hospital',
    name: '医院管理',
    meta: { title: '医院管理', icon: 'nested' },
    children: [
      {
        path: 'data',
        name: '医院管理',
        component: () => import('@/views/hospital/data'),
        meta: { title: '医院管理', icon: 'nested' },
      },
      {
        path: 'info',
        hidden: true,
        name: '信息设置',
        component: () => import('@/views/hospital/info'),
        meta: { title: '信息设置', icon: 'nested' }
      },
      {
        path: 'department',
        hidden: true,
        name: '科室管理',
        component: () => import('@/views/hospital/department'),
        meta: { title: '科室管理', icon: 'tree' }
      },
      {
        path: 'schedule',
        hidden: true,
        name: '排班计划',
        component: () => import('@/views/hospital/schedule'),
        meta: { title: '排班计划' ,icon: 'table'}
      },
      {
        path: 'doctor',
        hidden: true,
        name: '医生信息',
        component: () => import('@/views/hospital/doctor'),
        meta: { title: '医生信息' ,icon: 'form'}
      },
 
    ]
  },


  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: '医院管理',
  //   meta: {
  //     title: '医院管理',
  //     icon: 'link'
  //   },
  //   children: [
  //     {
  //       path: '信息设置',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: '信息设置',
  //       meta: { title: '信息设置' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: '科室管理',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: '科室管理' }
  //     },
  //     {
  //       path: '排班计划',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: '排班计划' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
