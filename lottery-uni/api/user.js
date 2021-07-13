import request from '@/utils/request.js'

/**
 * @param {Object} user
 * 根据昵称和头像以及会场编号登记用户
 */
export function registerUser(user,placeId){
	return request({
		method: 'POST',
		url: 'lottery-server/user/insertOneUser',
		data: {
			name: user.nickName.trim(),
			avatar: user.avatarUrl,
			placeId
		}
	})
}

/**
 * @param {Object} user
 * 根据昵称和会场编号查询用户是否登记
 */
export function isRegister(user,placeId){
	return request({
		method: 'GET',
		url: 'lottery-server/user/selectOneUserByNameWithPlaceId',
		params: {
			name: user.nickName,
			placeId
		}
	})
}

/**
 * @param {Object} id
 * 根据登记编号查询该用户的登记信息
 */
export function getRegister(id){
	return request({
		method: 'GET',
		url: 'lottery-server/user/selectOneUserById',
		params: {
			id
		}
	})
}