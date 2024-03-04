import http from "@/utils/request";

//请求登录数据
// export const getLoginData = (level,password,userId) =>{
//     //返回一个promise对象
//     return http.post('/project/admin/Login',
//         {
//             level:level,
//             password:password,
//             userId:userId
//         })
// }
// 管理员的教师管理
export const getTeacherInfo = () =>{
    return http.post('/project/admin/checkTeacher')
}
export const addTeacher = (data) =>{
    return http.post('/project/admin/addTeacher',data)
}
export const editTeacher = (data) =>{
    return http.post('/project/admin/updateTeacher',data)
}
export const searchTeacher = (data) =>{
    return http.post('/project/admin/searchTeacher',data)
}
// 管理员对于学生和教师的删除均是这个
export const deleteUser = (data) =>{
    return http.post('/project/admin/deleteUser',data)
}
// 管理员的学生管理
export const getStudentInfo = () =>{
    return http.post('/project/admin/checkStudent')
}
export const addStudent = (data) =>{
    return http.post('/project/admin/addStudent',data)
}
export const editStudent = (data) =>{
    return http.post('/project/admin/updateStudent',data)
}
export const searchStudent = (data) =>{
    return http.post('/project/admin/searchStudent',data)
}
// 管理员的成绩管理
export const showScoreAdmin = ()=>{
    return http.post('/project/grade/aggregateGrade',{userId:"admin"})
}
//管理员的课程管理
export const addCourseAdmin = (data)=>{
    return http.post('/project/course/addCourse',data)
}
export const deleteCourseAdmin = (data)=>{
    return http.post('/project/course/deleteCourse',data)
}
export const editCourseAdmin = (data)=>{
    return http.post('/project/course/updateCourse',data)
}
export const getAllCourseAdmin = ()=>{
    return http.post('/project/admin/searchCourse',{userId:"admin"})
}
export const giveTeacherCourseAdmin = (data)=>{
    return http.post('/project/admin/setTeacherCourse',data)
}
//重修以及学籍变动
export const remakeCourseAdmin = (data)=>{
    return http.post('/project/admin/statisticRetrainingStudents',data)
}
export const changeStudentAdmin = (data)=>{
    return http.post('/project/admin/statisticsStatusChangeStudents',data)
}
//登陆管理
export const loginCheck = (data)=>{
    return http.post('/project/admin/Login',data)
}


//学生部分
export const getStudentMineScore = (data)=>{
    return http.post('/project/grade/checkGrade',data)
}
export const getStudentMineInfo = (data)=>{
    return http.post('/project/student/studentInformation',data)
}
export const changeStudentMinePass = (data)=>{
    return http.post('/project/student/StudentChangePassword',data)
}
export const getStudentMineCourse = (data)=>{
    return http.post('/project/student/studentCheckCourse',data)
}

//教师部分
export const getTeacherMineInfo = (data)=>{
    return http.post('/project/teacher/teacherInformation',data)
}
export const changeTeacherMinePass = (data)=>{
    return http.post('/project/teacher/TeacherChangePassword',data)
}
export const getClassMineScore = (data)=>{
    return http.post('/project/grade/aggregateClassGrade',data)
}
export const inClassStudentScore = (data)=>{
    return http.post('/project/grade/enterGrade',data)
}
export const getTeacherMineCourse = (data)=>{
    return http.post('/project/teacher/teacherCheckCourse',data)
}
export const getTeacherStudentCourse = (data)=>{
    return http.post('/project/teacher/teacherCheckCourseStudent',data)
}