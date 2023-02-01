import request from '@/utils/request'

export default{
  getDocList(page, pageSize,doctor) {
        return request({
          url: `/admin/hospital/hospDoc/list/${page}/${pageSize}`,
          method: 'post',
          data: doctor,
        })
      },
  getDocById(id) {
    return request({
      url: `/admin/hospital/hospDoc/getDocById/${id}`,
      method: 'get',
    })
  },
  removeDocById(id) {
    return request({
      url: `/admin/hospital/hospDoc/deleteDoc/${id}`,
      method: 'delete',
    })
  },
  batchRemoveDoc(idList){
    return request({
      url: `/admin/hospital/hospDoc/batchRemove/${idList}`,
      method: 'delete',
    })
  },

  saveDoc(doctor){
    return request({
      url: `/admin/hospital/hospDoc/saveDoc`,
      method: 'post',
      data: doctor
    })
  },
  updateDoc(doctor){
    return request({
      url: `/admin/hospital/hospDoc/updateDoc`,
      method: 'put',
      data: doctor
    })
  },

   //    根据hoscode获取医生信息
   getHospDocAll(hoscode) {
    return request({
      url: `/admin/hospital/hospDoc/getHospDoc/${hoscode}`,
      method: 'get',
    })
  },
  //    根据depcode获取医生信息
  getDocByDepcode(depcode) {
    return request({
      url: `/admin/hospital/hospDoc/getDocByDepcode/${depcode}`,
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
  
}
