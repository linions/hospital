<template>
    <div class="app-container">
        <h4>基本信息</h4>
        <table class="showTable" width="100%">
            <tbody>
                <tr>
                    <th width="15%">医院名称</th>
                    <td width="35%" style="text-align:center"><b style="font-size: 14px">{{ hospital.hosname }}</b> | {{ this.hospital.param.hostypeName }}</td>
                    <th width="15%">医院logo</th>
                    <td width="35%" style="text-align:center">
                        <img :src="require('../../../build/hospLogo/' + img)" width="80">
                    </td>
                </tr>
                <tr>
                    <th>医院编码</th>
                    <td style="text-align:center">{{ hospital.hoscode }}</td>
                    <th>地址</th>
                    <td style="text-align:center">{{ hospital.param.fullAddress + hospital.address}}</td>
                </tr>
                <tr>
                    <th>坐车路线</th>
                    <td colspan="3">{{ hospital.route }}</td>
                </tr>
                <tr>
                    <th>医院简介</th>
                    <td colspan="3">{{ hospital.intro }}</td>
                </tr>
            </tbody>
        </table>

        <h4>预约规则信息</h4>
        <table class="showTable" width="100%">
            <tbody>
                <tr>
                    <th width="15%">预约周期</th>
                    <td width="35%" style="text-align:center">{{ bookingRule.cycle }}天</td>
                    <th width="15%">放号时间</th>
                    <td width="35%" style="text-align:center">{{ bookingRule.releaseTime }}</td>
                </tr>
                <tr>
                    <th>停挂时间</th>
                    <td style="text-align:center">{{ bookingRule.stopTime }}</td>
                    <th>退号时间</th>
                    <td style="text-align:center">{{ bookingRule.quitDay == -1 ? '就诊前一工作日' : '就诊当日' }}{{ bookingRule.quitTime }} 前取消</td>
                </tr>
                <tr>
                    <th>预约规则</th>
                    <td colspan="3">
                    <ol>
                    <li v-for="item in bookingRule.rule" :key="item">{{ item }}</li>
                    </ol>
                    </td>
                </tr>
                <br>
                
            </tbody>
          </table>
        <el-row>
          <el-button @click="back" style="float:right">返回</el-button>
        </el-row>
                 
    </div>
</template>
  
<style >
.showTable {
  border-spacing: 0;
  border-collapse: collapse;
}

.showTable tr th , .showTable tr td {
  border-spacing: 0;
  border-collapse: collapse;
  border: 1px solid rgb(189, 186, 186);
}


</style>
<script>
import ShowApi from '@/api/list'
export default {
    data() {
        return {
            hospital:{
                hosname: ' ',
                param:{
                  hostypeName:'',
                },
                address:'',
                bookingRule: '',
                intro:'',
                logoData:'',
                route:'',
            },  //医院信息
            bookingRule:{}, //预约信息
            img:'',
        }
    },
    created() {
        //获取路由id
        const hoscode = this.$route.params.hoscode
        console.log(hoscode)
        //调用方法，根据id查询医院详情
        this.fetachHospDetail(hoscode)

    },
    methods:{
        //根据id查询医院详情
        fetachHospDetail(hoscode) {
            ShowApi.getHospInfoByHoscode(hoscode)
                .then(response => {
                    this.hospital = response.data
                    this.img = this.hospital.logoData.split('\\')
                    this.img = this.img[this.img.length - 1]
                    console.log(this.hospital)
                    this.bookingRule = response.data.bookingRule
                })
        },
        //返回医院列表
        back() {
            this.$router.push({ path: '/hospSet/list' })
        }
    }
}
</script>
  