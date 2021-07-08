import axios from "axios";

//axios实例
const axiosInstance = axios.create({
  baseURL: '//localhost:8080',
  timeout: 1000*5
});

//request拦截器
axiosInstance.interceptors.request.use(config=>{
  // console.log('request拦截器成功->',config)
  return config
},error => {
  console.log('request拦截器异常->',error)
})

//response拦截器
axiosInstance.interceptors.response.use(response=>{
  // console.log('response拦截器成功->',response)
  return response.data
},error => {
  console.log('response拦截器异常->',error)
})

export default axiosInstance
