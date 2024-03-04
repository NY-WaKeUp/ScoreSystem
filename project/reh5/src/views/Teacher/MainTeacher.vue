<template>
  <div>
    <el-container>
      <!--    <el-aside  width="auto" >-->
      <el-aside  width="200px" >
        <CoAsTeacher></CoAsTeacher>
      </el-aside>
      <el-container>
        <el-header>
          <CoHeTeacher></CoHeTeacher>
        </el-header>
        <CoTaTeacher></CoTaTeacher>
        <el-main>
          <router-view></router-view>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
import CoAsTeacher from "@/components/Teacher/CoAsTeacher";
import CoHeTeacher from "@/components/Teacher/CoHeTeacher";
import CoTaTeacher from "@/components/Teacher/CoTaTeacher";
export default {
  data(){
    return {

    }
  },
  components:{
    CoAsTeacher,
    CoHeTeacher,
    CoTaTeacher
  },
  computed: {
    isCollapse(){
      return this.$store.state.tab3.isCollapse
    }
  }
}
</script>

<style>
.el-header {
  padding: 0;

}
</style>