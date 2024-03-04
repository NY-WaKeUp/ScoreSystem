export default {
    state: {
        isCollapse:false,   //控制菜单展开还是收起
        tabListStudent:[
            {
                path:'/student/homeStudent',
                name:'homeStudent',
                label:'首页',
                icon:'el-icon-s-home',
                url:'Home/HomeStudent'
            },
        ]//学生面包屑数据
    },
    mutations:{
        collapseMenu2(state){
            state.isCollapse = !state.isCollapse
        },
        //更新面包屑数据
        selectMenu2(state,val){
            // console.log(val);
            if(val !== 'homeStudent'){
                const index = state.tabListStudent.findIndex(item  => item.name === val.name)
                //如果不存在
                if(index === -1 ){
                    state.tabListStudent.push(val)
                }
            }
        },
        //删除指定的tag数据
        closeTag2(state,item){
            // console.log(val);
            //删除图标
            const index = state.tabListStudent.findIndex(val=>val.name===item.name)
            state.tabListStudent.splice(index,1)

        }
    }
}