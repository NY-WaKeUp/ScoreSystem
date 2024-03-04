<template>
  <div>
    <!--    <h3>课程</h3>-->
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="name"
          label="课程名">
      </el-table-column>
      <el-table-column
          prop="courseId"
          label="课程号"
      >
      </el-table-column>
      <el-table-column
          prop="date"
          label="上课时间"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getTeacherMineCourse} from '../../api/index'
import global from "@/views/global";
export default {
  data() {
    return {
      tableData: [
      ]
    }
  },
  mounted() {
    getTeacherMineCourse({userId:global.UserId}).then(({data})=>{
      if(data.code === 0){
        this.tableData = data.data
      }
    })
  }
}
</script>
