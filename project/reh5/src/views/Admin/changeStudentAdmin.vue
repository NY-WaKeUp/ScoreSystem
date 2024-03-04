<template>
  <div>
    <el-dialog
        title="学籍变动汇总"
        :visible.sync="dialogVisible"
        width="30%"
    >
      <el-form ref="form" :model="form" :inline="true" label-width="80px" :rules="rules">
        <el-form-item label="专业号" prop="majorId">
          <el-input placeholder="请输入专业号"  v-model="form.majorId" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="showChangeSubmit">确 定</el-button>
      </span>
    </el-dialog>

    <el-button  @click="showChange" type="primary">查看学籍变动学生</el-button>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="_class" label="班级" ></el-table-column>
      <el-table-column prop="email" label="email" ></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="major" label="专业"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
<!--      <el-table-column prop="userId" label="账号"></el-table-column>-->
      <!--    password不进行显示了-->
    </el-table>
  </div>
</template>

<script>
import {changeStudentAdmin} from '../../api/index'
export default {
  data(){
    return {
      form:{
        majorId:"",
      },
      rules:{
        majorId: [
          {required:true,message:'请输入专业号'}
        ],
      },
      tableData:[

      ],
      dialogVisible:false,
    }
  },
  methods:{
    showChange(){
      this.dialogVisible = true
    },
    showChangeSubmit(){
      changeStudentAdmin(this.form).then(({data})=>{
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
  },
}
</script>

<style scoped>

</style>