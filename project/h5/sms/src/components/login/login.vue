<template>
  <div class="login" :style="note">
    <div class="star test"></div>
    <div class="star pink"></div>
    <div class="star blue"></div>
    <div class="star black"></div>
    <div class="star red"></div>
    <div class="star white"></div>
    <div class="star yellow"></div>
    <div class="star gray"></div>
    <div class="star orange"></div>
    <div class="title">
      <h2>
        <i class="el-icon-eleme"></i> 学生成绩管理系统
      </h2>
    </div>
    <div class="loginForm">
      <el-tabs type="border-card" v-model="tabName" style="height: 380px">
        <el-tab-pane label="登录" name="student">
<!--          <Form class="flip" ref="student_tab"></Form>-->
          <div style="margin-top: 40px;">
            <el-form ref="form" :model="form" label-width="20px" class="form">
            </el-form>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>


  </div>
</template>

<script>
    import Form from './form'
    import '../../common/css/star.css'

    export default {
        data() {
            return {
                starClass: 'star',
                starArr: '',
                keyLabel: '学生',
                tabName: 'student',
                loading: false,
                note: {
                    backgroundImage: 'url(' + require('../../assets/home.jpg') + ')',
                    backgroundRepeat: 'no-repeat',
                    backgroundSize: '100%'
                },
                starSty: ''
            }
        },
        methods: {

        },
        components: {
            Form
        },
    }


</script>
<style>
  * {
    margin: 0;
    padding: 0;
  }

  html, body, #app, .login {
    height: 100%;
  }
</style>
<style scoped>
  /deep/ .el-tabs {
    /*3d效果*/
    transform-style: preserve-3d;
     -webkit-perspective: 1000px;
    transition: 0.8s
  }

  .loginForm {
    width: 400px;
    margin: 150px 580px 0 580px;
  }
  .title {
    padding: 10px 10px;
  }
  .oblique {
    background-color:#D5D5D5;
    transform: rotate(120deg);
    box-sizing: border-box;
  }
</style>
