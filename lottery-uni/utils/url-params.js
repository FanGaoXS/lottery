
/**
 * @param {Object} url
 * @param {Object} param
 * 传入完整的url，根据param获取url中的参数
 */
export function getQRCodeQueryParam(url,param){
	const paramsStr = url.substring(url.indexOf('?')+1)
	const params = paramsStr.split('&')
	for (let i = 0; i < params.length; i++) {
		let key = (params[i].split('='))[0]
		let value = (params[i].split('='))[1]
		if ( key === param ) return value
	}
	return false
}
