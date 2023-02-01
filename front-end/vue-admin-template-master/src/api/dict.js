import request from '@/utils/request'

export default{
  getDictList(id) {
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
      method: 'get'
    })
  },
  exportDictData(){
    return request({
        url: `/admin/cmn/dict/exportData`,
        method: 'get'
      })
  },
  
}
