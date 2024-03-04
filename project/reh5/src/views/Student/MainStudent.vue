<template>
  <div>
    <el-container>
      <!--    <el-aside  width="auto" >-->
      <el-aside  width="200px" >
        <CoAsStudent></CoAsStudent>
      </el-aside>
      <el-container>
        <el-header>
          <CoHeStudent></CoHeStudent>
        </el-header>
        <CoTaStudent></CoTaStudent>
        <el-main>
          <router-view></router-view>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
import CoAsStudent from "@/components/Student/CoAsStudent";
import CoHeStudent from "@/components/Student/CoHeStudent";
import CoTaStudent from "@/components/Student/CoTaStudent";
export default {
  data(){
    return {

    }
  },
  components:{
    CoAsStudent,
    CoHeStudent,
    CoTaStudent
  },
  computed: {
    isCollapse(){
      return this.$store.state.tab2.isCollapse
    }
  }
}
</script>

<style>
.el-header {
  padding: 0;

}
</style>