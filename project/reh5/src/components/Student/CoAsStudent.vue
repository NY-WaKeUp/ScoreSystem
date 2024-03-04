<template>
  <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           :collapse="isCollapse"
           background-color="#545c64"
           text-color="#fff"
           active-text-color="#ffd04b">
    <h3>{{isCollapse ? '学生' : '学生使用平台' }}</h3>
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
          path:'/student/homeStudent',
          name:'homeStudent',
          label:'首页',
          icon:'el-icon-s-home',
          url:'Home/HomeStudent'
        },
        {
          path:'/student/courseStudent',
          name:'courseStudent',
          label:'课程查看',
          icon:'el-icon-edit',
          url:'Course/CourseStudent'
        },
        {
          path:'/student/scoreStudent',
          name:'scoreStudent',
          label:'成绩查看',
          icon:'el-icon-edit-outline',
          url:'Score/ScoreStudent'
        },
        {
          path:'/student/infoStudent',
          name:'infoStudent',
          label:'个人信息',
          icon:'el-icon-s-custom',
          url:'Info/InfoStudent'
        },
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
      if(this.$route.path != item.path && !(this.$route.path ==='/student/homeStudent' && item.path === '/student'))
        this.$router.push(item.path)
      this.$store.commit('selectMenu2',item)
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
      return this.$store.state.tab2.isCollapse
    }
  }
}
</script>