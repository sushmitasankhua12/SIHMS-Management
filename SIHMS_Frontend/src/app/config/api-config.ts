import { environment } from "src/environments/environment";

let baseUrl = environment.baseUrl;
export const loginUrl = baseUrl + "/login/loginapi";

export const addstudent = baseUrl + "/api/addstudent";
export const getClassroom = baseUrl + "/api/getClassroom";
export const getClassroomdetails = baseUrl + "/api/getClassroomdetails";
export const getstudentdata = baseUrl + "/api/getstudentdata";

export const asignhomework = baseUrl + "/api/asignhomework";
export const asignindividulahomework = baseUrl + "/api/asignindividulahomework";
export const getasignhomework = baseUrl + "/api/getasignhomework";


export const getstudentdatafordashbord = baseUrl + "/api/getstudentdatafordashbord";
export const getteacherdata = baseUrl + "/api/getteacherdata";

export const scheduleclass = baseUrl + "/api/scheduleclass";
export const getscheduledclass = baseUrl + "/api/getscheduledclass";







