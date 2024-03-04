<template>
  <div class="tags">
    <el-tag
        v-for="(item,indexx) in tags"
        :key="item.path"
        :closable="item.name !== 'homeTeacher'"
        :effect="$route.name === item.name ? 'dark' : 'plain' "
        @click="changeMenu(item)"
        @close="handleClose(item,indexx)"
        size="small"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>

<script>
import {mapState,mapMutations} from 'vuex'
export default {
  data(){
    return {

    }
  },
  computed:{
    ...mapState({
      tags:state=>state.tab3.tabListTeacher
    })
  },
  methods:{
    ...mapMutations(['closeTag3']),
    //点击tag进行跳转
    changeMenu(item){
      this.$router.push({name: item.name})
    },
    //点击tag删除
    handleClose(item,index){
      this.closeTag2(item)
      const length = this.tags.length
      //删除之后的跳转逻辑
      if(item.name !== this.$route.name){
        return
      }
      if(index === length){
        this.$router.push({
          name: this.tags[index-1].name
        })
      } else{
        this.$router.push({
          name:this.tags[index].name
        })
      }
    },
  }
}
</script>

<style lang="less" scoped>
.tags {
  padding: 20px;
  .el-tag {
    margin-right: 15px;
    cursor: pointer;
  }
}
</style>