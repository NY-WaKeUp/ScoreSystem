<template>
  <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           :collapse="isCollapse"
           background-color="#545c64"
           text-color="#fff"
           active-text-color="#ffd04b">
    <h3>{{isCollapse ? '管理员' : '管理员后台系统' }}</h3>
    <el-menu-item  @click="clickMenu(item)" v-for="item in noChildren" :key="item.name" :index="item.name">
      <i :class="item.icon"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>


    <el-submenu v-for="item in hasChildren" :key="item.label" :index="item.label">
      <template slot="title">
        <i :class="item.icon"></i>
        <span slot="title">{{item.label}}</span>
      </template>
      <el-menu-item-group v-for="subItem in item.children" :key="subItem.name" :index="subItem.name">
        <el-menu-item @click="clickMenu(subItem)" :index="subItem.name">{{ subItem.label }}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<style lang="less" scoped>
//el-scrollbar {
//  width: 100%;
//}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  //width: 200px; //不去底部除有滚动条
  min-height: 400px;
}
.el-menu {
  height: 100vh;
  border-right: none;
  h3 {
    color:#fff;
    font-size: 16px;
    font-weight: 400;
    text-align: center;
    line-height: 48px;
  }
}
</style>

<script>

export default {
  data() {
    return {
      // isCollapse: false,
      menuData:[
        {
          path:'/admin/homeAdmin',
          name:'homeAdmin',
          label:'首页',
          icon:'el-icon-s-home',
          url:'Home/HomeAdmin'
        },
        // {
        //   path:'/admin/courseSearchAdmin',
        //   name:'courseSearchAdmin',
        //   label:'课程查询',
        //   icon:'el-icon-search',
        //   url:'CourseSearch/CourseSearchAdmin'
        // },
        {
          path:'/admin/courseInAdmin',
          name:'courseInAdmin',
          label:'课程管理',
          icon:'el-icon-edit',
          url:'CourseIn/CourseInAdmin'
        },
        // {
        //   path:'/admin/scoreSearchAdmin',
        //   name:'scoreSearchAdmin',
        //   label:'成绩查询',
        //   icon:'el-icon-stopwatch',
        //   url:'ScoreSearch/ScoreSearchAdmin'
        // },
        {
          path:'/admin/scoreInAdmin',
          name:'scoreInAdmin',
          label:'成绩统计',
          icon:'el-icon-edit-outline',
          url:'ScoreIn/ScoreInAdmin'
        },
        {
          label:'学生变动',
          icon:'el-icon-stopwatch',
          children:[
            {
              path:'/admin/remakeCourseAdmin',
              name:'remakeCourseAdmin',
              label:'重修学生',
              icon:'el-icon-cherry',
              url:'RemakeCo/RemakeCourseAdmin'
            },
            {
              path:'/admin/changeStudentAdmin',
              name:'changeStudentAdmin',
              label:'学籍变动',
              icon:'el-icon-watermelon',
              url:'ChangeStu/ChangeStudentAdmin'
            }
          ]
        },
        {
          label:'用户管理',
          icon:'el-icon-s-custom',
          children:[
            {
              path:'/admin/studentMaAdmin',
              name:'studentMaAdmin',
              label:'学生管理',
              icon:'el-icon-cherry',
              url:'StudentMa/StudentMaAdmin'
            },
            {
              path:'/admin/teacherMaAdmin',
              name:'teacherMaAdmin',
              label:'教师管理',
              icon:'el-icon-watermelon',
              url:'TeacherMa/TeacherMaAdmin'
            }
          ]
        }
      ]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    clickMenu(item){
      if(this.$route.path != item.path && !(this.$route.path ==='/admin/homeAdmin' && item.path === '/admin'))
        this.$router.push(item.path)
      this.$store.commit('selectMenu',item)
    }
  },
  computed:{
    noChildren(){
      // console.log(localhost)
      return this.menuData.filter(item => !item.children)
    },
    hasChildren(){
      return this.menuData.filter(item => item.children)
    },
    isCollapse(){
      return this.$store.state.tab.isCollapse
    }
  }
}
</script>