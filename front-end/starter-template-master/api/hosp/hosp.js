import request from '@/utils/request'

const api_name = `/api/hosp/hospital`
export default {
    //查询医院列表
    getPageList(page,pageSize,searchObj){
        return request({
            url:`${api_name}/findHospList/${page}/${pageSize}`,
            method:'get',
            params: searchObj
        })
    },
    //根据医院名称做模糊查询
    getByHosname(hosname){
        return request({
            url:`${api_name}/findHospByName/${hosname}`,
            method:'get',
        })
    }
}