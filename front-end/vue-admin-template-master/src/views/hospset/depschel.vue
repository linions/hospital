<template>
    <div class="app-container">
        <div style="margin-bottom: 10px;font-size: 10px;">选择：{{ baseMap.hosname }}  /  {{ depname }}  /  {{ workDate }}</div>
            <el-container style="height: 100%">
            <el-aside width="200px" style="border: 1px silver solid">
                <!-- 部门 -->
                <el-tree
                :data="data"
                :props="defaultProps"
                :default-expand-all="true"
                @node-click="handleNodeClick">
                </el-tree>
            </el-aside>
            <el-main style="padding: 0 0 0 20px;">
                <el-row style="width: 100%">
                    <!-- 排班日期 分页 -->
                    <el-tag v-for="(item,index) in bookingScheduleList" :key="item.id" @click="selectDate(item.workDate, index)" 
                    :type="index == activeIndex ? '' : 'info'" style="height: 60px;margin-right: 5px;margin-right:6px;cursor:pointer;">
                        {{ item.workDate }} {{ item.dayOfWeek }}<br/>
                        {{ item.availableNumber }} / {{ item.reservedNumber }}
                    </el-tag>

                    <!-- 分页 -->
                    <el-pagination
                        :current-page="page"
                        :total="total"
                        :page-size="page"
                        class="pagination"
                        layout="prev, pager, next"
                        @current-change="getPage">
                    </el-pagination>
                </el-row>

                <!-- v-loading="listLoading" -->

                <el-row style="margin-top: 20px;">
                <!-- 排班日期对应的排班医生 -->
                    <el-table
                        :data="scheduleList"
                        border
                        fit
                        highlight-current-row>
                        <el-table-column
                            label="序号"
                            width="60"
                            align="center">
                            <template slot-scope="scope">
                                {{ scope.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column label="职称" width="150" align="center">
                            <template slot-scope="scope">
                                {{ scope.row.param.doctor.title }} | {{ scope.row.param.doctor.name }}
                            </template>
                        </el-table-column>
                        <el-table-column label="号源时间" width="80" align="center">
                            <template slot-scope="scope">
                                {{ scope.row.workTime == 0 ? "上午" : "下午" }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="reservedNumber" label="可预约数" width="80" align="center"/>
                        <el-table-column prop="availableNumber" label="剩余预约数" width="100" align="center"/>
                        <el-table-column prop="param.doctor.amount" label="挂号费(元)" width="90" align="center"/>
                        <el-table-column prop="param.doctor.skill" label="擅长技能" align="center"/>
                    </el-table>
                </el-row>


            </el-main>
        </el-container>
    </div>

</template>
  
<style>
.el-tree-node.is-current > .el-tree-node__content {
    background-color: #409EFF !important ;
    color: white
}
.el-checkbox__input.is-checked+.el-checkbox__label {
    color: black;
}
</style>
<script>
import depSche from '@/api/list'
export default {
    data() {
        return {
            hoscode:'',
            data:[],
            defaultProps: {
                children: 'children',
                label: 'depname'
            },
            hoscode: null,
            activeIndex: 0,
            depcode: null,
            depname: null,
            workDate: null,

            bookingScheduleList: [],
            baseMap: {},
            scheduleList:[], //排班详情

            page: 1, // 当前页
            pageSize: 7, // 每页个数
            total: 0 // 总页码

        }
    },
    created() {
        //获取路由id
        this.hoscode = this.$route.params.hoscode
        this.workDate = this.getCurDate()
        //调用方法，根据id查询医院详情
        this.fetachDepDetail(this.hoscode)

    },
    methods:{
        listLoading(){},
        //获取该医院科室的所有信息
        fetachDepDetail(hoscode) {
            depSche.getDepInfoByHoscode(hoscode)
            .then(response => {
                this.data = response.data
                console.log(this.data)
                // 默认选中第一个
                if (this.data.length > 0) {
                        this.depcode = this.data[0].children[0].depcode
                        this.depname = this.data[0].children[0].depname
                        this.getPage()
                    }
            })
            .catch(error =>{
                console.log(error)
            })
        },

        getPage(page = 1) {
            this.page = page
            this.workDate = null
            this.activeIndex = 0
            this.getScheduleRule()
        },

        getScheduleRule() {
            depSche.getScheInfoByHoscode(this.page, this.pageSize, this.hoscode, this.depcode).then(response => {
            this.bookingScheduleList = response.data.bookingScheduleRuleList

            this.total = response.data.total

            this.scheduleList = response.data.scheduleList
            this.baseMap = response.data.baseMap

            // 分页后workDate=null，默认选中第一个
            if (this.workDate == null) {
                this.workDate = this.bookingScheduleList[0].workDate
            }
        })
    },

    handleNodeClick(data) {
        // 科室大类直接返回
        if (data.children != null) return
        this.depcode = data.depcode
        this.depname = data.depname
        this.getPage(1)
    },

    selectDate(workDate, index) {
        this.workDate = workDate
        this.activeIndex = index
        this.getDetailSchedule()
    },

    getCurDate() {
        var datetime = new Date()
        var year = datetime.getFullYear()
        var month = datetime.getMonth() + 1 < 10 ? '0' + (datetime.getMonth() + 1) : datetime.getMonth() + 1
        var date = datetime.getDate() < 10 ? '0' + datetime.getDate() : datetime.getDate()
        return year + '-' + month + '-' + date
    },

    //查询排班详情
    getDetailSchedule() {
        depSche.getScheDetailByWorkDate(this.hoscode,this.depcode,this.workDate)
            .then(response => {
                this.scheduleList = response.data
                console.log(this.scheduleList)
            })
    },

        //返回医院列表
        back() {
            this.$router.push({ path: '/hospSet/list' })
       }
    }
}
</script>
  