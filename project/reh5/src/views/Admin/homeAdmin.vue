<template>
<div>
  <el-row>
    <el-col :span="8">
      <div class="grid-content bg-purple">
      <el-card class="box-card" style="margin-right: 10px;margin-bottom: 20px">
        <div class="user">
          <img src="../../assets/pyy.jpg">
          <div class="user-info">
            <p class="name">Admin</p>
            <p class="access">管理员</p>
          </div>
        </div>
        <div class="login-info">
          <p>上次登录的时间：<span>2022-11-29</span></p>
          <p>上次登录的地点：<span>河北工业大学</span></p>
        </div>
      </el-card>
<!--        <el-card style="height:280px;margin-right: 10px;">-->
<!--&lt;!&ndash;          左下图&ndash;&gt;-->
<!--          <div ref="echarts1" style="height: 250px;"></div>-->
<!--        </el-card>-->
    </div>
    </el-col>

    <el-col :span="16">
      <div class="grid-content bg-purple-light">
        <el-card class="circle" style="height: 307px;margin-bottom: 20px">
<!--          <circle></circle>-->
<!--          右上图-->
          <div ref="echarts3" style="height: 250px;width: 800px;"></div>
        </el-card>
<!--        右下-->
<!--          <div class="right-bottom">-->
<!--            <el-card style="height:280px;">-->
<!--&lt;!&ndash;              <div ref="echarts2" style="height: 290px; width: 800px"></div>&ndash;&gt;-->
<!--              <div ref="echarts2" style="height: 290px; width: 800px"></div>-->
<!--            </el-card>-->
<!--          </div>-->

      </div>
    </el-col>
  </el-row>
</div>
</template>

<script>

import * as echarts from 'echarts'
import {showScoreAdmin} from "@/api";
export default {
  data(){
    return {
      tableData:[

      ],
      // data_xAxis: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      // data_series: [120, 200, 150, 80, 70, 110, 130],
      data_xAxis:[],
      data_series:[],
      length:""
    }
  },
  mounted() {
    showScoreAdmin().then(({data})=>{
      this.tableData = data.data.courseGradeResponseList
      this.length = data.data.courseGradeResponseList.length
      // console.log(this.length);
      // console.log(this.tableData);
      for(let i=0;i<this.length;i++){
        this.data_xAxis[i] = this.tableData[i].courseName
        this.data_series[i] = this.tableData[i].aveGrade
      }
      //第一个表格
      // const myChart = echarts.init(this.$refs.echarts1);
      // const myChart2 = echarts.init(this.$refs.echarts2);
      const myChart3 = echarts.init(this.$refs.echarts3);

      // let option = {};
      // option = {
      //   backgroundColor: '#2c343c',
      //   title: {
      //     text: '学生成绩等级分布',
      //     left: 'center',
      //     top: 20,
      //     textStyle: {
      //       color: '#ccc'
      //     }
      //   },
      //   tooltip: {
      //     trigger: 'item'
      //   },
      //   visualMap: {
      //     show: false,
      //     min: 80,
      //     max: 600,
      //     inRange: {
      //       colorLightness: [0, 1]
      //     }
      //   },
      //   series: [
      //     {
      //       name: '学生成绩评级',
      //       type: 'pie',
      //       radius: '55%',
      //       center: ['50%', '50%'],
      //       data: [
      //         { value: 170, name: 'A' },
      //         { value: 310, name: 'B' },
      //         { value: 274, name: 'C' },
      //         { value: 200, name: 'D' },
      //         { value: 183, name: 'E' }
      //       ].sort(function (a, b) {
      //         return a.value - b.value;
      //       }),
      //       roseType: 'radius',
      //       label: {
      //         color: 'rgba(255, 255, 255, 0.3)'
      //       },
      //       labelLine: {
      //         lineStyle: {
      //           color: 'rgba(255, 255, 255, 0.3)'
      //         },
      //         smooth: 0.2,
      //         length: 10,
      //         length2: 20
      //       },
      //       itemStyle: {
      //         color: '#c23531',
      //         shadowBlur: 200,
      //         shadowColor: 'rgba(0, 0, 0, 0.5)'
      //       },
      //       animationType: 'scale',
      //       animationEasing: 'elasticOut',
      //       animationDelay: function (idx) {
      //         return Math.random() * 200;
      //       }
      //     }
      //   ]
      // };

      let option3 ;
      option3 = {
        xAxis: {
          type: 'category',
          data: this.data_xAxis,
          axisLabel:{
            interval: 0,
            formatter: function (value, index) {
              if(value.length>5){
                return value.substr(0,5)+'...'
              }else{
                return value
              }
            }
          },
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.data_series,
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ],
        grid:{
          top:"10%",
          bottom:"10%"
        },

      };
      // option && myChart.setOption(option);
      // option2 && myChart2.setOption(option2);
      option3 && myChart3.setOption(option3);
    })
    // //第一个表格
    // const myChart = echarts.init(this.$refs.echarts1);
    // // const myChart2 = echarts.init(this.$refs.echarts2);
    // const myChart3 = echarts.init(this.$refs.echarts3);
    //
    // let option = {};
    // option = {
    //   backgroundColor: '#2c343c',
    //   title: {
    //     text: '学生成绩等级分布',
    //     left: 'center',
    //     top: 20,
    //     textStyle: {
    //       color: '#ccc'
    //     }
    //   },
    //   tooltip: {
    //     trigger: 'item'
    //   },
    //   visualMap: {
    //     show: false,
    //     min: 80,
    //     max: 600,
    //     inRange: {
    //       colorLightness: [0, 1]
    //     }
    //   },
    //   series: [
    //     {
    //       name: '学生成绩评级',
    //       type: 'pie',
    //       radius: '55%',
    //       center: ['50%', '50%'],
    //       data: [
    //         { value: 170, name: 'A' },
    //         { value: 310, name: 'B' },
    //         { value: 274, name: 'C' },
    //         { value: 200, name: 'D' },
    //         { value: 183, name: 'E' }
    //       ].sort(function (a, b) {
    //         return a.value - b.value;
    //       }),
    //       roseType: 'radius',
    //       label: {
    //         color: 'rgba(255, 255, 255, 0.3)'
    //       },
    //       labelLine: {
    //         lineStyle: {
    //           color: 'rgba(255, 255, 255, 0.3)'
    //         },
    //         smooth: 0.2,
    //         length: 10,
    //         length2: 20
    //       },
    //       itemStyle: {
    //         color: '#c23531',
    //         shadowBlur: 200,
    //         shadowColor: 'rgba(0, 0, 0, 0.5)'
    //       },
    //       animationType: 'scale',
    //       animationEasing: 'elasticOut',
    //       animationDelay: function (idx) {
    //         return Math.random() * 200;
    //       }
    //     }
    //   ]
    // };
    //
    // let option3 ;
    // option3 = {
    //   xAxis: {
    //     type: 'category',
    //     data: this.data_xAxis
    //   },
    //   yAxis: {
    //     type: 'value'
    //   },
    //   series: [
    //     {
    //       data: this.data_series,
    //       type: 'bar',
    //       showBackground: true,
    //       backgroundStyle: {
    //         color: 'rgba(180, 180, 180, 0.2)'
    //       }
    //     }
    //   ],
    //   grid:{
    //     top:"10%",
    //     bottom:"10%"
    //   }
    // };
    // option && myChart.setOption(option);
    // // option2 && myChart2.setOption(option2);
    // option3 && myChart3.setOption(option3);

  }
}
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  img {
    width: 170px;
    height: 170px;
    border-radius: 50%;
    margin-right: 40px;
  }
  .user-info {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: #999999;
    }
  }
}
.login-info{
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;
    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
.right-bottom {
  display: flex;
  justify-content: space-between;
}
</style>