export default {
    state: {
        isCollapse:false,   //控制菜单展开还是收起
        tabListTeacher:[
            {
                path:'/teacher/homeTeacher',
                name:'homeTeacher',
                label:'首页',
                icon:'el-icon-s-home',
                url:'Home/HomeTeacher'
            },
        ]//学生面包屑数据
    },
    mutations:{
        collapseMenu3(state){
            state.isCollapse = !state.isCollapse
        },
        //更新面包屑数据
        selectMenu3(state,val){
            // console.log(val);
            if(val !== 'homeTeacher'){
                const index = state.tabListTeacher.findIndex(item  => item.name === val.name)
                //如果不存在
                if(index === -1 ){
                    state.tabListTeacher.push(val)
                }
            }
        },
        //删除指定的tag数据
        closeTag3(state,item){
            // console.log(val);
            //删除图标
            const index = state.tabListTeacher.findIndex(val=>val.name===item.name)
            state.tabListTeacher.splice(index,1)

        }
    }
}