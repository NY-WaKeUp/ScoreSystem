<template>
  <div class="classScore">
<!--    录入成绩的弹窗-->
    <el-dialog
        title="录入成绩"
        :visible.sync="dialogVisible"
        width="30%"
    >
      <el-form ref="addForm" :model="addForm" :inline="true" label-width="80px" :rules="rules" >
        <el-form-item label="账号" prop="userId2">
          <el-input   v-model="addForm.userId2" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="学生学号" prop="stuNo">
          <el-input placeholder="请输入学生学号"  v-model="addForm.stuNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程号" prop="courseId">
          <el-input placeholder="请输入课程号"  v-model="addForm.courseId" ></el-input>
        </el-form-item>
        <el-form-item label="平时成绩" prop="usualGrade2">
          <el-input placeholder="请输入平时成绩"  v-model="addForm.usualGrade2" ></el-input>
        </el-form-item>
        <el-form-item label="考试成绩" prop="examGrade2">
          <el-input placeholder="请输入考试成绩"  v-model="addForm.examGrade2" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="InClassScoreSubmit">确 定</el-button>
      </span>
    </el-dialog>

<!--    录入成绩的按钮-->
    <el-button class="showClassScore" @click="InClassScore" type="primary">成绩录入</el-button>
    <el-button class="showClassScore" @click="test" type="primary">测试</el-button>
<!--    显示录入完成绩返回的信息的表格-->
    <el-table :data="tableData" style="width: 100%" >
      <el-table-column prop="klass" label="班级"></el-table-column>
      <el-table-column prop="major" label="专业"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="userId" label="学号"></el-table-column>
      <el-table-column prop="usualGrade" label="平时成绩"></el-table-column>
      <el-table-column prop="examGrade" label="考试成绩"></el-table-column>
      <el-table-column prop="grade" label="总成绩"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import {inClassStudentScore} from '../../api/index'
import global from "@/views/global";
export default {
  data() {
    return {
      // tableData: [
      //   {
      //     examGrade:'',
      //     grade:"",
      //     klass:'',
      //     major:"",
      //     name:"",
      //     userId:"",
      //     usualGrade:"",
      //   },
      // ],
      tableData:[

      ],
      // tableData:{
      //
      // },
      tranData:[],
      addForm:{
        courseId:"",
        examGrade2:"",
        stuNo:"",
        userId2:"",
        usualGrade2:""
      },
      dialogVisible: false,
      rules:{
        courseId: [
          {required:true,message:'请输入课程'}
        ],
        examGrade2: [
          {required:true,message:'请输入考试成绩'}
        ],
        stuNo: [
          {required:true,message:'请输入学生学号'}
        ],
        usualGrade2: [
          {required:true,message:'请输入平时成绩'}
        ],
      }
    }
  },
  mounted(){
    this.addForm.userId2 = global.UserId

  },
  methods:{
    InClassScoreSubmit(){
      inClassStudentScore({courseId:this.addForm.courseId,examGrade:this.addForm.examGrade2,
        stuNo:this.addForm.stuNo,userId:this.addForm.userId2,usualGrade:this.addForm.usualGrade2}).then(({data}) =>{
      // inClassStudentScore(this.addForm).then(({data}) =>{
      //   console.log(data.code);
      //   console.log(data.data);
        if(data.code===0){
          // this.tableData[0] = data.data
          this.tranData[0] = data.data
          this.tableData = this.tranData
          console.log(data.data);
          console.log("111")
          console.log(this.tableData[0]);
          // this.tableData = data.data
          this.$message({
            message: '录入成功，点击下方查看录入信息',
            type: 'success'
          });
          this.dialogVisible=false
        }
        else{
          this.$message.error('相关信息错误');
          this.dialogVisible=false
        }
      })
    },
    InClassScore(){
      this.dialogVisible=true
    },
    test(){
      console.log("66666")
      console.log(this.tableData);
    }
  }
}
</script>