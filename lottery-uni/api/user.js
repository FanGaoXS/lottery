import request from '@/utils/request.js'

export function registerUser(user){
	return request({
		method: 'POST',
		url: 'lottery-server/user/registerUser',
		data: {
			name: user.nickName.trim(),
			avatar: user.avatarUrl
		}
	})
}

export function isRegister(user){
	return request({
		method: 'GET',
		url: 'lottery-server/user/isRegister',
		params: {
			name: user.nickName,
			avatar: user.avatarUrl
		}
	})
}