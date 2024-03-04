<template>

  <div class="manage">


    <div class="manage-header">
      <!--from搜索区-->
      <el-form :inline="true">
        <el-form-item>
          <el-input v-model="input" placeholder="请输入课程号"></el-input>
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
            prop="courseId"
            label="课程号"
        >
        </el-table-column>
        <el-table-column
            prop="courseName"
            label="课程名"
        >
        </el-table-column>
        <el-table-column
            prop="maxGrade"
            label="最高分">
        </el-table-column>
        <el-table-column
            prop="minGrade"
            label="最低分">
        </el-table-column>
        <el-table-column
            prop="passNum"
            label="及格人数">
        </el-table-column>
        <el-table-column
            prop="studentNum"
            label="课程人数">
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
import {showScoreAdmin} from '../../api/index'
export default {
  data(){
    return {
      input: '',
      dialogVisible: false,
      tableData:[

      ],
      total: 0,  //当前数据总条数
    }
  },
  methods: {
    // 获取列表的数据，使得增加数据或者删除数据能够体现
    getList() {
      showScoreAdmin().then(({data}) => {
        console.log(data)
        this.tableData = data.data.courseGradeResponseList
        this.total = data.data.courseGradeResponseList ? data.data.courseGradeResponseList.length : 0
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
        showScoreAdmin().then(({data}) => {
          this.total = data.data.courseGradeResponseList ? data.data.courseGradeResponseList.length : 0
          let num =0;
          const result0 = [{}];
          for(let i=0;i<this.total;i++)
            if(data.data.courseGradeResponseList[i].courseId === this.input)
              result0[num++] = data.data.courseGradeResponseList[i]
          console.log(result0)
          this.tableData = result0;
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