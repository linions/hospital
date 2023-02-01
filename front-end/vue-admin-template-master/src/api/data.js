import request from '@/utils/request'

export default{
  getHospSetList(current, limit, hospSet) {
    return request({
      url: `/admin/hosp/hospSet/pageHospSet/${current}/${limit}`,
      method: 'post',
      data: hospSet,
    })
  },
}
