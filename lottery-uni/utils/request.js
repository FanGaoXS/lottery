// 引入luch-request
import Request from 'luch-request'

const env = 'dev'

let baseURL = ''

switch(env){
	case 'dev': 
		baseURL = 'http://localhost:8080'
		break;
	case 'stage':
		baseURL = 'https://draw.wqkd.blctek.com'
		break;
	case 'prod':
		baseURL = 'http://localhost:8080'
		break;
	default:
		baseURL = 'http://localhost:8080'
}

const http = new Request();

//全局配置
http.config.baseURL = baseURL
http.config.timeout = 1000*10

// request拦截器
http.interceptors.request.use(config=>{
	// request成功发出前做处理
	return config
},error=>{
	console.log('request拦截器错误->',error);
	return Promise().reject(error)
})

// response拦截器
http.interceptors.response.use(response=>{
	// response接收成功的处理
	return response.data
},error=>{
	console.log('response拦截器错误->',error.data);
	return Promise().reject(error.data)
})

export default function request(config){
	// config的method需要传递PUT|GET|DOWNLOAD|UPLOAD（必须大写）
	return http.middleware(config)
}

