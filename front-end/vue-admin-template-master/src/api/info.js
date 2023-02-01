import request from '@/utils/request'

export default{
  getHospInfo(hoscode) {
    return request({
      url: `admin/hospital/hospData/getHospInfo/${hoscode}`,
      method: 'get'
    })
  },
  UpdateHospInfo(hospital) {
    return request({
      url: `admin/hospital/hospData/updateHospInfo`,
      method: 'put',
      data: hospital,
    })
  },
  findChildData(id){
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
      method: 'get',
    })
  },
  //根据value获取名称
  getDictName(value){
    return request({
        url: `/admin/cmn/dict/getName/${value}`,
        method: 'get',
     })
  },
//根据dictCode获取其下级节点
  findByDictCode(dictCode){
    return request({
        url: `/admin/cmn/dict/getByDictCode/${dictCode}`,
        method: 'get',
     })
  }, 
  // 上传图片
  uninstallLogo(fileName){
    return request({
        url: `/admin/hospital/hospData/uninstallLogo/${fileName}`,
        method: 'delete',
     })
  },
  
}