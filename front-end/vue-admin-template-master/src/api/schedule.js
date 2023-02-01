import request from '@/utils/request'

export default{
  getHospScheList(page, pageSize,schedule) {
    return request({
      url: `/admin/hospital/hospSche/list/${page}/${pageSize}`,
      method: 'post',
      data: schedule,
    })
  },
  removeScheById(id) {
    return request({
      url: `/admin/hospital/hospSche/deleteHosSche/${id}`,
      method: 'delete',
    })
  },
  batchRemoveSchedule(idList){
    return request({
      url: `/admin/hospital/hospSche/batchRemove/${idList}`,
      method: 'delete',
    })
  },
  // lockHostSet(id,status){
  //   return request({
  //     url: `/admin/hosp/hospSet/lockHospSet/${id}/${status}`,
  //     method: 'put',
  //   })
  // },
  saveSchedule(schedule){
    return request({
      url: `/admin/hospital/hospSche/addScheInfo`,
      method: 'post',
      data: schedule
    })
  },
  updateSchedule(schedule){
    return request({
      url: `/admin/hospital/hospSche/updateScheInfo`,
      method: 'put',
      data: schedule
    })
  },
  GetScheduleById(id){
    return request({
      url: `/admin/hospital/hospSche/getScheById/${id}`,
      method: 'get',
    })
  },
  //    根据depcode获取医院设置
  getHospDep(depCode) {
    return request({
      url: `/admin/hospital/hospDep/findChildData/${depCode}`,
      method: 'get'
    })
  },
   //    根据hoscode获取医生信息
   getHospDoc(hoscode) {
    return request({
      url: `/admin/hospital/hospDoc/getHospDoc/${hoscode}`,
      method: 'get',
    })
  },
  //    根据depcode获取科室信息
  getHospDep(hospital) {
    return request({
      url: `/admin/hospital/hospDep/getAllChildDep`,
      method: 'post',
      data: hospital
    })
  },

  //    根据depcode获取医生信息
  getHospDocByDep(depcode) {
    return request({
      url: `/admin/hospital/hospDoc/getDocByDepcode/${depcode}`,
      method: 'get',
    })
  },
  
}
