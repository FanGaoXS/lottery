import request from '@/utils/request.js'

export function registerUser(user){
	return request({
		method: 'POST',
		url: '/user/registerUser',
		data: {
			name: user.nickName.trim(),
			avatar: user.avatarUrl
		}
	})
}

export function isRegister(user){
	return request({
		method: 'GET',
		url: '/user/isRegister',
		params: {
			name: user.nickName,
			avatar: user.avatarUrl
		}
	})
}