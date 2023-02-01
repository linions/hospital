import request from '@/utils/request'

const api_name = `/admin/cmn/dict`
export default {
    //根据dictCode获取其下节点
    findByDictCode(dictCode){
        return request({
            url:`${api_name}/getByDictCode/${dictCode}`,
            method:'get',
        })
    },
    //根据id获取其下级节点
    getHospByName(parentId){
        return request({
            url:`${api_name}/findChildData/${parentId}`,
            method:'get',
        })
    }
}