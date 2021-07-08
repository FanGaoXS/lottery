import request from "@/utils/request";

export function getUserList(){
  return request({
    url: 'lottery-server/user/selectUserList',
    method: 'GET'
  })
}
