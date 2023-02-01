import request from '@/utils/request'

export default{
  getUserInfoList(page, pageSize,searchObj) {
    return request({
      url: `/admin/hospUser/info/getList/${page}/${pageSize}`,
      method: 'post',
      data: searchObj,
    })
  },
  deleteUserInfo(id) {
    return request({
      url: `/admin/hospUser/info/deleteById/${id}`,
      method: 'delete',
    })
  },
  batchRemoveUserInfo(idList) {
    return request({
      url: `/admin/hospUser/info/batchRemove/${idList}`,
      method: 'delete',
    })
  },

  
}
