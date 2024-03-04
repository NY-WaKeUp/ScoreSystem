<template>
  <div>
<!--    修改密码弹出框-->
    <el-dialog
        title="修改密码"
        :visible.sync="dialogVisible"
        width="30%"
        >
<!--      <span>这是一段信息</span>-->
      <el-form ref="editForm" :model="editForm" :inline="true" label-width="80px" :rules="rules">
        <el-form-item label="账号" prop="userId">
          <el-input   v-model="editForm.userId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="原密码" prop="oldPassIn">
          <el-input placeholder="请输入原密码" type="password" v-model="editForm.oldPassIn" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input placeholder="请输入新密码" type="password" v-model="editForm.newPassword" ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input placeholder="请再次输入新密码" type="password" v-model="editForm.checkPass" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editPasswordSubmit">确 定</el-button>
  </span>
    </el-dialog>
<!--    //显示个人信息的主页面-->
    <el-card class="infoStudent">
    <el-form ref="tranForm" :model="tranForm" label-width="80px">
      <el-form-item prop="name" label="姓名" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm.name"></el-input>
      </el-form-item>
      <el-form-item prop="userId" label="账号" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm.userId"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm.password"></el-input>
      </el-form-item>
      <el-form-item prop="_class" label="班级" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm._class"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="email" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm.email"></el-input>
      </el-form-item>
      <el-form-item prop="gender" label="性别" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm.gender"></el-input>
      </el-form-item>
      <el-form-item prop="major" label="专业" style="width: 90%">
        <el-input :disabled="true" v-model="tranForm.major"></el-input>
      </el-form-item>
    </el-form>
      <el-button class="editPassword" @click="editPassword" type="primary">修改密码</el-button>
    </el-card>
  </div>
</template>

<script>
import {getStudentMineInfo,changeStudentMinePass} from "@/api";
import global from "@/views/global";

export default {
  data(){
    return {
      form:[

      ],
      tranForm:{
          _class: "",
          email: "",
          gender: "",
          major: "",
          name: "",
          password: "",
          userId: ""
      },
      dialogVisible: false,
      editForm:{
        userId:global.UserId,
        newPassword:"",
        checkPass:"",
        oldPass:global.PassWord,
        oldPassIn:""
      },
      rules:{
        oldPassIn: [
          {required:true,message:'请输入原密码'}
        ],
        newPassword: [
          {required:true,message:'请输入新密码'}
        ],
        checkPass:[
          {required:true,message:'请再次输入新密码'}
        ],
      }
    }
  },
  mounted(){
    getStudentMineInfo({userId:global.UserId}).then(({data})=>{
      // console.log(data.data);
      this.form[0] = data.data
      // console.log(this.form[0]);
      this.tranForm = this.form[0]
    })
  },
  methods:{
    editPassword(){
      this.dialogVisible=true
    },
    editPasswordSubmit(){
      // console.log(this.editForm.oldPassIn)
      // console.log(this.editForm.oldPass);
      // console.log('-----');
      if(this.editForm.oldPassIn !== this.editForm.oldPass) {
        this.$message.error('原密码错误');
        this.dialogVisible=false
      }
      else if(this.editForm.newPassword !== this.editForm.checkPass){
        this.$message.error('新密码两次不一致');
        this.dialogVisible=false
      }
      else{
        console.log('---------');
        changeStudentMinePass({newPassword:this.editForm.newPassword,userId:this.editForm.userId}).then(({data})=>{
          if(data.code === 0){
            this.$message({
              message: '密码修改成功',
              type: 'success'
            });
            this.$router.push({name:'login'})
          }
        })
      }
    }

  }
}
</script>

<style>
.infoStudent {
  width: 60%;
  margin: auto;
}
.editPassword {
  margin-left: 500px;
}
</style>