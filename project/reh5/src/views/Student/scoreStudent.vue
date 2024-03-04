<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column  type="expand">
      <template v-slot="slot">
        <el-table :data="slot.row.gradeList">
          <el-table-column prop="courseName" label="课程名" ></el-table-column>
          <el-table-column prop="examGrade" label="考试成绩" ></el-table-column>
          <el-table-column prop="usualGrade" label="平时成绩" ></el-table-column>
          <el-table-column prop="grade" label="总成绩" ></el-table-column>
        </el-table>
      </template>
    </el-table-column>
    <el-table-column prop="aveGrade" label="平均分" width="180"></el-table-column>
    <el-table-column prop="totalCourse" label="总科目数" width="180"></el-table-column>
    <el-table-column prop="passCourse" label="及格科目"></el-table-column>
  </el-table>
</template>

<script>
import {getStudentMineScore} from '../../api/index'
import global from "@/views/global";
export default {
  data() {
    return {
      tableData: [
        {
          aveGrade:'',
          totalCourse:"",
          passCourse:'',
          gradeList:[
            {
              courseName:"",
              examGrade:'',
              usualGrade:'',
              grade:''
            }
          ]
        },
      ]
    }
  },
  mounted(){
    getStudentMineScore({userId:global.UserId}).then(({data}) =>{
      // console.log(global.UserId);
      this.tableData[0] = data.data
      // console.log(data);
      // console.log(this.tableData[0]);
    })
  }
}
</script>
