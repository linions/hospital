import request from '@/utils/request'

export default{
  getHospSetList(current, limit, hospSet) {
    return request({
      url: `/admin/hosp/hospSet/pageHospSet/${current}/${limit}`,
      method: 'post',
      data: hospSet,
    })
  },
  removeDataById(id) {
    return request({
      url: `/admin/hosp/hospSet/logicDelete/${id}`,
      method: 'delete',
    })
  },
  batchRemoveHospSet(idList){
    return request({
      url: `/admin/hosp/hospSet/batchRemove/${idList}`,
      method: 'delete',
    })
  },
  lockHostSet(id,status){
    return request({
      url: `/admin/hosp/hospSet/lockHospSet/${id}/${status}`,
      method: 'put',
    })
  },
  saveHospitalSet(hospSet){
    return request({
      url: `/admin/hosp/hospSet/saveHospSet`,
      method: 'post',
      data: hospSet
    })
  },
  updateHospSet(hospSet){
    return request({
      url: `/admin/hosp/hospSet/updateHospSet`,
      method: 'put',
      data: hospSet
    })
  },
  GetHospitalById(id){
    return request({
      url: `/admin/hosp/hospSet/getHospSetById/${id}`,
      method: 'get',
    })
  },
}
