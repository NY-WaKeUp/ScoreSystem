<template>
  <div>
    <el-row>
      <el-col :span="10">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="margin-right: 10px;margin-bottom: 20px">
            <div class="user">
              <img src="../../assets/hg.jpg">
              <div class="user-info">
                <p class="name">{{this.userId}}</p>
                <p class="access">学生</p>
              </div>
            </div>
            <div class="login-info">
              <p>上次登录的时间：<span>2022-12-01</span></p>
              <p>上次登录的地点：<span>河北邢台沙河显德旺</span></p>
            </div>
          </el-card>
        </div>
      </el-col>

      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <el-card class="circle" style="height: 562px;margin-bottom: 20px">
            <el-calendar v-model="value">
            </el-calendar>
          </el-card>

        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import global from "@/views/global";
import * as echarts from 'echarts'
export default {
  data(){
    return {
      userId:"",
      password:"",
      value: new Date()
    }
  },
  mounted() {
    // console.log(global.UserId)
    this.userId = global.UserId
  }
}
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  img {
    width: 170px;
    height: 170px;
    border-radius: 50%;
    margin-right: 40px;
  }
  .user-info {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: #999999;
    }
  }
}
.login-info{
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;
    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
.right-bottom {
  display: flex;
  justify-content: space-between;
}
</style>