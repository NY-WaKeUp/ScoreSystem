<template>
<div>
  <el-dialog
      title="课程学生信息"
      :visible.sync="dialogVisible"
      width="30%"
  >
    <el-form ref="form" :model="form" :inline="true" label-width="80px" :rules="rules">
      <el-form-item label="账号" prop="userId">
        <el-input   v-model="form.userId" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="班级" prop="klassId">
        <el-input placeholder="请输入班级"  v-model="form.klassId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="课程号" prop="courseId">
        <el-input placeholder="请输入课程号"  v-model="form.courseId" ></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="showStudentTeacherSubmit">确 定</el-button>
      </span>
  </el-dialog>

  <el-button class="showClassScore" @click="showStudentTeacherCourse" type="primary">查看课程学生</el-button>

  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="_class" label="班级" ></el-table-column>
    <el-table-column prop="email" label="email" ></el-table-column>
    <el-table-column prop="gender" label="性别"></el-table-column>
    <el-table-column prop="major" label="专业"></el-table-column>
    <el-table-column prop="name" label="姓名"></el-table-column>
<!--    <el-table-column prop="userId" label="账号"></el-table-column>-->
  </el-table>

</div>
</template>

<script>
import {getTeacherStudentCourse} from '../../api/index'
import global from "@/views/global";
export default {
  data(){
    return {
      form:{
        courseId:"",
        klassId:"",
        userId:global.UserId
      },
      tableData:[

      ],
      dialogVisible:false,
      rules:{
        klassId: [
          {required:true,message:'请输入班级号'}
        ],
        courseId: [
          {required:true,message:'请输入课程号'}
        ],
      }
    }
  },
  methods:{
    showStudentTeacherCourse(){
      this.dialogVisible = true
    },
    showStudentTeacherSubmit(){
      getTeacherStudentCourse(this.form).then(({data})=>{
        console.log(data);
        if(data.code === 0){
          this.$message({
            message: '查看成功',
            type: 'success'
          });
          this.tableData = data.data
          this.dialogVisible=false
        }
        else{
          this.$message.error('相关信息错误');
          this.dialogVisible=false
        }
      })
    }
  }
}
</script>

<style scoped>

</style>