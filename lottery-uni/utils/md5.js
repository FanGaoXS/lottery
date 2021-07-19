import md5Libs from "uview-ui/libs/function/md5";

export function md5NameAndAvatar(name,avatar){
	return md5Libs.md5(name + '|' + avatar)
}