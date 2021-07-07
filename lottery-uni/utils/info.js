const infoKey = 'lottery_info'

export function setInfo(info){
	return uni.setStorageSync(infoKey,info)
}

export function getInfo(){
	return uni.getStorageSync(infoKey)
}

export function removeInfo(){
	return uni.removeStorageSync(infoKey)
}