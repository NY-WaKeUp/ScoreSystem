<template>
  <div class="classScore">
    <el-dialog
        title="班级单科成绩汇总"
        :visible.sync="dialogVisible"
        width="30%"
    >
      <el-form ref="addForm" :model="addForm" :inline="true" label-width="80px" :rules="rules">
        <el-form-item label="账号" prop="userId2">
          <el-input   v-model="addForm.userId2" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="klass2">
          <el-input placeholder="请输入班级"  v-model="addForm.klass2" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程号" prop="courseId2">
          <el-input placeholder="请输入课程号"  v-model="addForm.courseId2" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="showClassScoreSubmit">确 定</el-button>
      </span>
    </el-dialog>

    <el-button class="showClassScore" @click="showClassScore" type="primary">汇总成绩</el-button>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column  type="expand">
        <template v-slot="slot">
          <el-table :data="slot.row.klassGradeResponses">
            <el-table-column prop="grade" label="成绩" ></el-table-column>
            <el-table-column prop="name" label="姓名" ></el-table-column>
            <el-table-column prop="studentId" label="学号" ></el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column prop="aveGrade" label="平均分"></el-table-column>
      <el-table-column prop="courseId" label="课程编号"></el-table-column>
      <el-table-column prop="courseName" label="课程姓名"></el-table-column>
      <el-table-column prop="klass" label="班级"></el-table-column>
      <el-table-column prop="maxGrade" label="课程最高分"></el-table-column>
      <el-table-column prop="minGrade" label="课程最低分"></el-table-column>
      <el-table-column prop="passNum" label="及格人数"></el-table-column>
      <el-table-column prop="studentNum" label="课程人数"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import {getClassMineScore} from '../../api/index'
import global from "@/views/global";
export default {
  data() {
    return {
      tableData: [
        {
          aveGrade:'',
          courseId:"",
          courseName:'',
          klass:"",
          maxGrade:"",
          minGrade:"",
          passNum:"",
          studentNum:"",
          klassGradeResponses:[
            {
              grade:"",
              name:'',
              studentId:'',
            }
          ],
        },
      ],
      addForm:{
        klass2:"",
        courseId2:"",
        userId2:""
      },
      dialogVisible: false,
      rules:{
        klass2: [
          {required:true,message:'请输入班级号'}
        ],
        courseId2: [
          {required:true,message:'请输入课程号'}
        ],
      }
    }
  },
  mounted(){
    this.addForm.userId2 = global.UserId

  },
  methods:{
    showClassScoreSubmit(){
      console.log(this.addForm.courseId2);
      console.log(this.addForm.klass2);
      getClassMineScore({courseId:this.addForm.courseId2,klass:this.addForm.klass2,userId:this.addForm.userId2}).then(({data}) =>{
        if(data.code===0){
          this.tableData[0] = data.data
          console.log(data);
          this.$message({
            message: '汇总成功，点击下方展开查看汇总信息',
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
    showClassScore(){
      this.dialogVisible=true
    }
  }
}
</script>