<template>
<div>
<!--  增加课程表单-->
  <el-dialog title="增加课程" :visible.sync="dialogVisible1" width="30%" class="add" >
    <el-form ref="addForm" :inline="true" :model="addForm" label-width="80px" :rules="rules1">
      <el-form-item label="课程号" prop="courseId1">
        <el-input placeholder="请输入课程号" v-model="addForm.courseId1"></el-input>
      </el-form-item>
      <el-form-item label="上课时间" prop="date1">
        <el-input placeholder="请输入上课时间" v-model="addForm.date1"></el-input>
      </el-form-item>
      <el-form-item label="课程名" prop="name1">
        <el-input placeholder="请输入课程名" v-model="addForm.name1"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addSubmit">确定</el-button>
        <el-button @click="addOut">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

<!--  删除课程表单-->
  <el-dialog title="删除课程" :visible.sync="dialogVisible2" width="30%" class="add" >
    <el-form ref="deleteForm" :inline="true" :model="deleteForm" label-width="80px" :rules="rules2">
      <el-form-item label="课程号" prop="courseId2">
        <el-input placeholder="请输入课程号" v-model="deleteForm.courseId2"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="deleteSubmit">确定</el-button>
        <el-button @click="deleteOut">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

<!--  编辑表单-->
  <el-dialog title="修改课程" :visible.sync="dialogVisible3" width="30%" class="add" >
    <el-form ref="editForm" :inline="true" :model="editForm" label-width="80px" :rules="rules3">
      <el-form-item label="课程号" prop="courseId3">
        <el-input placeholder="请输入课程号" v-model="editForm.courseId3"></el-input>
      </el-form-item>
      <el-form-item label="上课时间" prop="date3">
        <el-input placeholder="请输入上课时间" v-model="editForm.date3"></el-input>
      </el-form-item>
      <el-form-item label="课程名" prop="name3">
        <el-input placeholder="请输入课程名" v-model="editForm.name3"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="editSubmit">确定</el-button>
        <el-button @click="editOut">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

<!--  赋予老师课程表单-->
  <el-dialog title="老师赋课" :visible.sync="dialogVisible4" width="30%" class="add" >
    <el-form ref="giveForm" :inline="true" :model="giveForm" label-width="80px" :rules="rules4">
      <el-form-item label="课程号" prop="courseId">
        <el-input placeholder="请输入课程号" v-model="giveForm.courseId"></el-input>
      </el-form-item>
      <el-form-item label="教师姓名" prop="teacherName">
        <el-input placeholder="请输入教师姓名" v-model="giveForm.teacherName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="giveSubmit">确定</el-button>
        <el-button @click="giveOut">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-button type="primary" @click="showAdd">增加课程</el-button>
  <el-button type="danger" @click="showDelete">删除课程</el-button>
  <el-button type="info" @click="showEdit">修改课程</el-button>
  <el-button type="success" @click="showCoTe">老师赋课</el-button>

  <!--  下面这个table没用，就是占个坑-->
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="courseId"
        label="课程编号"
        >
    </el-table-column>
    <el-table-column
        prop="name"
        label="课程名字"
        >
    </el-table-column>
    <el-table-column
        prop="date"
        label="课程时间">
    </el-table-column>
  </el-table>
</div>
</template>

<script>
import {
  addCourseAdmin,
  deleteCourseAdmin,
  editCourseAdmin,
  getAllCourseAdmin,
  giveTeacherCourseAdmin
} from '../../api/index'
export default {
  data(){
    return {
      tableData: [],
      dialogVisible1:false,
      dialogVisible2:false,
      dialogVisible3:false,
      dialogVisible4:false,
      addForm:{
        courseId1:"",
        date1:"",
        name1:""
      },
      deleteForm:{
        courseId2:"",
      },
      editForm:{
        courseId3:"",
        date3:"",
        name3:""
      },
      giveForm:{
        courseId:"",
        teacherName:""
      },
      rules1:{
        courseId1: [
          {required:true,message:'请输入课程号'}
        ],
        date1: [
          {required:true,message:'请输入上课时间 '}
        ],
        name1:[
          {required:true,message:'请输入课程名'}
        ]
      },
      rules2:{
        courseId2: [
          {required:true,message:'请输入课程号'}
        ]
      },
      rules3:{
        courseId3: [
          {required:true,message:'请输入课程号'}
        ],
        date3: [
          {required:true,message:'请输入上课时间 '}
        ],
        name3:[
          {required:true,message:'请输入课程名'}
        ]
      },
      rules4:{
        courseId: [
          {required:true,message:'请输入课程号'}
        ],
        teacherName:[
          {required:true,message:'请输入教师姓名'}
        ]
      }
    }
  },
  methods:{
    showAdd(){
      this.dialogVisible1=true
    },
    addSubmit(){
      addCourseAdmin({courseId:this.addForm.courseId1,date:this.addForm.date1,name:this.addForm.name1}).then(({data})=>{
        console.log(data);
        if(data.code===0){
          this.$message({
            type:'success',
            message:'增加成功'
          });
          this.dialogVisible1=false
          this.flushData()
        }else{
          this.$message({
            type:'warning',
            message:'输入有误,增加失败'
          });
          this.dialogVisible1=false
          this.flushData()
        }
      })
    },
    addOut(){
      this.dialogVisible1=false
    },
    showDelete(){
      this.dialogVisible2=true
    },
    deleteSubmit(){
      deleteCourseAdmin({courseId:this.deleteForm.courseId2}).then(({data})=>{
        console.log(data);
        if(data.code===0){
          this.$message({
            type:'success',
            message:'删除成功'
          });
          this.dialogVisible2=false
          this.flushData()
        }else{
          this.$message({
            type:'warning',
            message:'输入有误,删除失败'
          });
          this.dialogVisible2=false
          this.flushData()
        }
      })
    },
    deleteOut(){
      this.dialogVisible2=false
    },
    showEdit(){
      this.dialogVisible3=true
    },
    editSubmit(){
      editCourseAdmin({courseId:this.editForm.courseId3,date:this.editForm.date3,name:this.editForm.name3}).then(({data})=>{
        console.log(data);
        if(data.code===0){
          this.$message({
            type:'success',
            message:'修改成功'
          });
          this.dialogVisible3=false
          this.flushData()
        }else{
          this.$message({
            type:'warning',
            message:'输入有误,修改失败'
          });
          this.dialogVisible3=false
          this.flushData()
        }
      })
    },
    editOut(){
      this.dialogVisible3=false
    },
    flushData(){
      getAllCourseAdmin().then(({data})=>{
        // console.log(data);
        if(data.code === 0){
          // console.log("1111111111")
          this.tableData = data.data
        }
      })
    },
    showCoTe(){
      this.dialogVisible4=true
    },
    giveOut(){
      this.dialogVisible4=false
    },
    giveSubmit(){
      giveTeacherCourseAdmin(this.giveForm).then(({data})=>{
        if(data.code === 0){
          this.$message({
            type:'success',
            message:'赋予成功'
          });
          this.dialogVisible4=false
        }else{
          this.$message({
            type:'warning',
            message:'输入有误,赋予失败'
          });
          this.dialogVisible4=false
        }
      })
    }
  },
  mounted() {
    this.flushData()
  }
}
</script>

<style scoped>

</style>