import request from '@/utils/request.js'
import { md5NameAndAvatar } from '@/utils/md5.js'

/**
 * @param {Object} user
 * 根据昵称和头像以及会场编号登记用户
 */
export function registerUser(user,placeId){
	return request({
		method: 'POST',
		url: 'lottery-server/user/insertOneUserWithPlaceId',
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
export function getRegister(nickName,placeId){
	return request({
		method: 'GET',
		url: 'lottery-server/user/selectOneUserByNameWithPlaceId',
		params: {
			name: nickName,
			placeId
		}
	})
}

export function getRegisterByMd5(nickName,avatar,placeId){
	const md5 = md5NameAndAvatar(nickName,avatar)
	return request({
		method: 'GET',
		url: 'lottery-server/user/selectOneUserByMd5WithPlaceId',
		params:{
			md5,
			placeId
		}
	})
}

/**
 * @param {Object} id
 * 根据具体编号查询用户的登记信息
 */
export function getRegisterById(id){
	return request({
		method: 'GET',
		url: 'lottery-server/user/selectOneUserById',
		params:{
			id
		}
	})
}

/**
 * @param {Object} placeId
 * 根据会场编号查询其他用户
 */
export function getUserListByPlaceId(placeId){
	return request({
		method: 'GET',
		url: 'lottery-server/user/selectListUserByPlaceId',
		params:{
			placeId
		}
	})
}