import Vue from 'vue'
import VueRouter from 'vue-router'
// 管理员
import HomeAdmin from '../views/Admin/homeAdmin'
import MainAdmin from '../views/Admin/MainAdmin'
import CourseInAdmin from '../views/Admin/courseInAdmin'
import ScoreInAdmin from '../views/Admin/scoreInAdmin'
import StudentMaAdmin from '../views/Admin/studentMaAdmin'
import TeacherMaAdmin from '../views/Admin/teacherMaAdmin'
import changeStudentAdmin from  '../views/Admin/changeStudentAdmin'
import remakeCourseAdmin from "@/views/Admin/remakeCourseAdmin";
//登录
import Login from "@/views/Login";
//学生
import homeStudent from "@/views/Student/homeStudent";
import MainStudent from "@/views/Student/MainStudent";
import courseStudent from "@/views/Student/courseStudent";
import scoreStudent from "@/views/Student/scoreStudent";
import infoStudent from "@/views/Student/infoStudent";
//教师
import homeTeacher from "@/views/Teacher/homeTeacher";
import MainTeacher from "@/views/Teacher/MainTeacher";
import courseTeacher from "@/views/Teacher/courseTeacher";
import scoreTeacher from "@/views/Teacher/scoreTeacher";
import infoTeacher from "@/views/Teacher/infoTeacher";
import scoreInTeacher from "@/views/Teacher/scoreInTeacher";
import courseStudentTeacher from "@/views/Teacher/courseStudentTeacher";

Vue.use(VueRouter)
//创建路由组件
const routes = [
    {
        path:'/login',
        component: Login,
        name:'login'
    },
    {  //管理员
        path:'/admin',
        component: MainAdmin,
        redirect:'/admin/homeAdmin',
        children:[
            //子路由
            //管理员
            {path:'homeAdmin',name:'homeAdmin',component:HomeAdmin},
            // {path:'courseSearchAdmin',name:'courseSearchAdmin',component:CourseSearchAdmin},
            {path:'courseInAdmin',name:'courseInAdmin',component:CourseInAdmin},
            // {path:'scoreSearchAdmin',name:'scoreSearchAdmin',component:ScoreSearchAdmin},
            {path:'scoreInAdmin',name:'scoreInAdmin',component:ScoreInAdmin},
            {path:'studentMaAdmin',name:'studentMaAdmin',component:StudentMaAdmin},
            {path:'teacherMaAdmin',name:'teacherMaAdmin',component:TeacherMaAdmin},
            {path:'changeStudentAdmin',name:'changeStudentAdmin',component:changeStudentAdmin},
            {path:'remakeCourseAdmin',name:'remakeCourseAdmin',component:remakeCourseAdmin},
        ]
    },
    {  //学生
        path:'/student',
        component: MainStudent,
        redirect:'/student/homeStudent',
        children:[
            //子路由
            {path:'homeStudent',name:'homeStudent',component:homeStudent},
            {path:'courseStudent',name:'courseStudent',component:courseStudent},
            {path:'scoreStudent',name:'scoreStudent',component:scoreStudent},
            {path:'infoStudent',name:'infoStudent',component:infoStudent},
        ]
    },
    {//教师
        path:'/teacher',
        component: MainTeacher,
        redirect:'/teacher/homeTeacher',
        children:[
            //子路由
            {path:'homeTeacher',name:'homeTeacher',component:homeTeacher},
            {path:'courseTeacher',name:'courseTeacher',component:courseTeacher},
            {path:'scoreTeacher',name:'scoreTeacher',component:scoreTeacher},
            {path:'infoTeacher',name:'infoTeacher',component:infoTeacher},
            {path:'scoreInTeacher',name:'scoreInTeacher',component:scoreInTeacher},
            {path:'courseStudentTeacher',name:'courseStudentTeacher',component:courseStudentTeacher},
        ]
    }

]

const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})

export default router
