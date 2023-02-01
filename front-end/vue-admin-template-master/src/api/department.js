import request from '@/utils/request'

export default{
  getHospMainDep(hospital) {
    return request({
      url: `/admin/hospital/hospDep/getHospDep`,
      method: 'post',
      data: hospital,
    })
  },
  getHospDep(depCode) {
    return request({
      url: `/admin/hospital/hospDep/findChildData/${depCode}`,
      method: 'get'
    })
  },
  getHospDepList(hospDep) {
    return request({
      url: `/admin/hospital/hospDep/searchHospDep`,
      method: 'post',
      data: hospDep,
    })
  },
  removeHospDepById(id) {
    return request({
      url: `/admin/hospital/hospDep/logicDelete/${id}`,
      method: 'delete',
    })
  },
  batchRemoveHospDep(idList){
    return request({
      url: `/admin/hospital/hospDep/batchRemove/${idList}`,
      method: 'delete',
    })
  },

  saveHospitalDep(hospDep){
    return request({
      url: `/admin/hospital/hospDep/saveHospDep`,
      method: 'post',
      data: hospDep
    })
  },
  updateHospDep(hospDep){
    return request({
      url: `/admin/hospital/hospDep/updateHospDep`,
      method: 'put',
      data: hospDep
    })
  },
  getHospitalDepByCode(depcode){
    return request({
      url: `/admin/hospital/hospDep/getDepByCode/${depcode}`,
      method: 'get',
    })
  },
  
}
