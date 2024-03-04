<template>

    <div class="manage">
      <el-dialog
          title="教师用户"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose"
      >
<!--        用户信息-->
        <el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="80px">
          <el-form-item label="姓名" prop="name">
            <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="userId">
            <el-input placeholder="请输入账号" v-model="form.userId"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="email" prop="email">
            <el-input placeholder="请输入email" v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-select v-model="form.gender" placeholder="请选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="handleClose">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </span>
      </el-dialog>

      <div class="manage-header">
        <el-button @click="handleAdd" type="primary">
          +新增
        </el-button>

      <!--from搜索区-->
        <el-form :inline="true">
          <el-form-item>
            <el-input v-model="input" placeholder="请输入用户姓名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmitToSea">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <!--表单-->
      <div class="common-table">
        <el-table
            height="90%"
            :data="tableData"
            style="width: 100%">
          <el-table-column
              prop="name"
              label="姓名"
          >
          </el-table-column>
          <el-table-column
              prop="gender"
              label="性别"
          >
          </el-table-column>
          <el-table-column
              prop="email"
              label="email">
          </el-table-column>
          <el-table-column
              prop="userId"
              label="账号">
          </el-table-column>
<!--          <el-table-column-->
<!--              prop="password"-->
<!--              label="密码">-->
<!--          </el-table-column>-->
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pager">
          <el-pagination
              layout="prev, pager, next"
              :total="total"
              @current-change="handlePage"
          >
          </el-pagination>
        </div>
      </div>

    </div>

</template>

<script>
import {getTeacherInfo,addTeacher,editTeacher,deleteUser,searchTeacher} from '../../api/index'
export default {
  data(){
    return {
      input: '',
      dialogVisible: false,
      form :{
        name:"",
        email: "",
        gender: "",
        password: "",
        userId: ""
      },
      tableData:[

      ],
      modelType:0,  // 0表示新增的弹窗，1表示编辑
      total: 0,  //当前数据总条数
      rules:{
        name: [
          {required:true,message:'请输入姓名'}
        ],
        email: [
          {required:true,message:'请输入email'}
        ],
        gender:[
          {required:true,message:'请选择性别'}
        ],
        password: [
          {required:true,message:'请输入密码'}
        ],
        userId: [
          {required:true,message:'请输入账号'}
        ],
      }
    }
  },
  methods: {
    submit() {
      this.$refs.form.validate((valid) => {
        // console.log(valid,'valid');
        if (valid) {
          // 处理
          if (this.modelType === 0) {
            addTeacher(this.form).then(() => {
              //更新完数据重新获取列表接口
              this.getList()
            })
          } else {
            editTeacher(this.form).then(() => {
              //更新完数据重新获取列表接口
              this.getList()
            })
          }
          this.handleClose()
        }
      })
    },
    handleClose() {
      this.$refs.form.resetFields()
      this.dialogVisible = false
    },
    handleEdit(row) {
      this.modelType = 1
      //************************************************************
      //这一部分由于级联就先不写了
      //***********************************************************
      // this.dialogVisible = true
      //注意要对当前行进行深拷贝，不然会报错
      // this.form = JSON.parse(JSON.stringify(row))
      this.$message({
        // type: 'success',
        // message: '删除成功!'
        type:'info',
        message:'数据库级连,请先修改相关课程等'
      });
    },
    handleDelete(row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser({role:"教师",userId:row.userId}).then(()=>{
          this.$message({
            // type: 'success',
            // message: '删除成功!'
            type:'info',
            message:'数据库级连,请先删除相关课程等'
          });
          this.getList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleAdd() {
      this.modelType = 0
      this.dialogVisible = true
    },
    // 获取列表的数据，使得增加数据或者删除数据能够体现
    getList() {
      getTeacherInfo().then(({data}) => {
        console.log(data)
        this.tableData = data.data
        this.total = data.data ? data.data.length : 0
        // console.log(this.total)
      })
    },
    handlePage(val){
      //选择页码的回调函数
      console.log(val);
    },
    //查询功能
    onSubmitToSea(){
      if(this.input === ''){
        this.getList()
      }
      else{
        // getTeacherInfo().then(({data}) => {
        //   this.total = data.data ? data.data.length : 0
        //   let num =0;
        //   const result0 = [{}];
        //   for(let i=0;i<this.total;i++)
        //     if(data.data[i].name === this.input)
        //       result0[num++] = data.data[i]
        //   console.log(result0)
        //   this.tableData = result0;
        // })
        searchTeacher({name:this.input}).then(({data}) =>{
          this.tableData = data.data
        })
      }
    }
  },
  mounted(){
    this.getList()
    // getTeacherInfo().then(({data}) =>{
    //   console.log(data)
    //   this.tableData = data.data
    // })
  }
}
</script>

<style lang="less" scoped>
.manage {
  height: 90%;
  .manage-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .common-table {
    position: relative;
    height: calc(100% - 62px);
    .pager {
      position: absolute;
      bottom: 0;
      right: 20px;
    }
  }
}
//el-table {
//  height: 90%;
//}
</style>