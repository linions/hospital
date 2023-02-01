import request from '@/utils/request'

export default{
  getHospitalList(page, pageSize, hospital) {
    return request({
      url: `/admin/hosp/hospSet/getHospitalSet/${page}/${pageSize}`,
      method: 'post',
      data: hospital,
    })
  },
  // 根据数据id查询子数据列表
  findChildData(id){
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
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
   
// 更新hospital表的状态值
  updateHospStatus(id,status){
    return request({
        url: `/admin/hospital/hospData/updateStatus/${id}/${status}`,
        method: 'put',
     })
  },
// 根据hoscode获取hospital详细信息
  getHospInfoByHoscode(hoscode) {
    return request({
      url: `admin/hosp/hospList/getHospitalInfo/${hoscode}`,
      method: 'get'
    })
  },

// 根据hoscode获取dep详细信息
getDepInfoByHoscode(hoscode) {
  return request({
    url: `admin/hosp/hospList/getHospitalDep/${hoscode}`,
    method: 'get'
  })
},

// 根据hoscode获取排班详细分页信息
getScheInfoByHoscode(page,pageSize,hoscode,depcode) {
  return request({
    url: `admin/hosp/hospList/getScheByHospAndDepcode/${page}/${pageSize}/${hoscode}/${depcode}`,
    method: 'get'
  })
},
//根据医院编号，科室编号以及排班日期查询排班信息
getScheDetailByWorkDate(hoscode,depcode,workDate) {
  return request({
    url: `admin/hosp/hospList/getHospScheDetail/${hoscode}/${depcode}/${workDate}`,
    method: 'get'
  })
},
}
