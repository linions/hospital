<template>
  <div class="app-container">

    <!-- 条件搜索 -->
    <el-form :inline="true"  class="demo-form-inline">
     <div>
      <el-form-item label="科室名称" >
        <el-select v-model="schedule.depcode" filterable placeholder="科室名称">
            <el-option v-for="item in depList"
            :key="item.id"
            :label="item.depname"
            :value="item.depcode">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="医生名称" :label-width="formLabelWidth">
        <el-select v-model="schedule.docid" filterable placeholder="医生名称">
            <el-option v-for="item in docList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item >
        <el-button type="primary" @click="getRequest" size="medium">查询</el-button>
        <el-button @click="back" style="margin-left: 245px;">返回</el-button>
        <!-- <el-button type="danger" @click="clear" size="medium" >清空</el-button> -->

      </el-form-item>
     </div>
      <el-form-item label="安排日期">
        <div class="block" >
          <el-date-picker
            style="width: 185px"
            v-model="schedule.workDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="排班状态" :label-width="formLabelWidth" >
        <el-select v-model="schedule.status" filterable placeholder="排班状态">
            <el-option v-for="item in statusList"
            :key="item.label"
            :label="item.value"
            :value="item.label">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item >
        <!-- <el-button type="primary" @click="getRequest" size="medium">查询</el-button> -->
        <el-button type="danger" @click="clear" size="medium" >清空</el-button>

      </el-form-item>
      
    </el-form>

    <!-- 工具条 -->
<div>
  <!-- @click="AddHospitalSet" -->
   <el-button type="danger" style="margin-bottom: 5px;" size="mini" @click="removeRows()">批量删除</el-button>
   <el-button type="success" style="margin-bottom: 5px;" size="mini" @click="addSchedule()">添加</el-button>




  <!-- 添加表单 -->
   <el-dialog  title="添加排班" :visible.sync="dialogFormVisible" style="text-align: center; " >
    <el-form :model="schedule">
      <el-form-item label="科室名称" :label-width="formLabelWidth">
        <el-select  @change="depChanged" v-model="schedule.depcode"  filterable placeholder="科室名称" style="margin-left: 60px;float: left;">
            <el-option v-for="item in depList"
            :key="item.id"
            :label="item.depname"
            :value="item.depcode">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="医生名称" :label-width="formLabelWidth">
        <el-select v-model="schedule.docid" filterable placeholder="医生名称" style="margin-left: 60px;float: left;">
            <el-option v-for="item in doctors"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="安排日期" :label-width="formLabelWidth">
        <div class="block" style="margin-left: 60px;float: left;">
          <el-date-picker
            v-model="schedule.workDate"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="安排时间" :label-width="formLabelWidth">
        <el-select v-model="schedule.workTime" filterable placeholder="安排时间" style="margin-left: 60px;float: left;">
            <el-option v-for="item in timeList"
            :key="item.label"
            :label="item.value"
            :value="item.label">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="可预约数" :label-width="formLabelWidth">
        <el-input v-model="schedule.reservedNumber" style="width: 150px;margin-left: 60px;float: left;" 
        placeholder="最大不超过50" oninput="if(value>50)value=50;value=value.replace(/[^\d]/g,'')" maxLength='2'/>
      </el-form-item>
      <el-form-item label="剩余预约数" :label-width="formLabelWidth">
        <el-input v-model="schedule.availableNumber" style="width: 150px; margin-left: 60px;float: left;" 
        placeholder="最大不超过50" oninput="if(value>50)value=50;value=value.replace(/[^\d]/g,'')" maxLength='2'/>
      </el-form-item>
      <el-form-item label="预约状态" :label-width="formLabelWidth" >
        <el-select v-model="schedule.status" filterable placeholder="预约状态" style="margin-left: 60px;float: left;" >
            <el-option v-for="item in statusList"
            :key="item.label"
            :label="item.value"
            :value="item.label">
            </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" >
      <!-- @click="saveAndUpdateHospSet" -->
      <el-button @click="dialogFormVisible = false;schedule = {}">取 消</el-button>
      <el-button type="primary" @click="saveAndUpdateSchedule">确 定</el-button>
    </div>
  </el-dialog>
</div>




<!-- 查询列表 -->
    <el-table
    :data="list"
    border
    stripe
          style="width: 100%" @selection-change="handleSelectionChange">
    <el-table-column type="selection" align="center" width="40"/>
    <el-table-column prop="id" align="center" label="id" width="50"/>
    <el-table-column prop="param.department.depname" align="center" label="科室名称" width="150"/>
    <el-table-column prop="param.doctor.name" align="center" label="医生名称" width="150"/>
    <el-table-column prop="workDate" align="center" label="安排日期" width="100"/>
    <el-table-column label="安排时间" align="center" width="80">
    <template slot-scope="scope">
              {{ scope.row.workTime === 0 ? '上午' : '下午' }}
    </template>
    </el-table-column>
    <el-table-column prop="reservedNumber" align="center" label="可预约数" width="80"/>
    <el-table-column prop="availableNumber" align="center" label="剩余预约数" width="100"/>
    <!-- <el-table-column prop="amount" align="center" label="挂号费" width="100"/> -->
    <el-table-column label="状态" align="center" width="65">
    <template slot-scope="scope">
              {{ scope.row.status ===  -1 ? '停诊': scope.row.status ===  0 ? '停约' : '可约' }}
    </template>
    </el-table-column>
    <el-table-column label="操作" align="center">
    <template slot-scope="scope">
      <el-button type="primary" size="mini"  @click="updateDataById(scope.row.id)"
         icon="el-icon" >修改</el-button>
      <el-button type="danger" size="mini" @click="removeDataById(scope.row.id)"
         icon="el-icon" >删除</el-button>

      </template>
    </el-table-column>

    </el-table>
<!-- 分页器 -->
    <el-pagination
      background
      @size-change="changePageSize"
      :current-page="current"
      :total="total"
      @current-change="changePage"
      :page-sizes="pages"
      :page-size="limit"
      layout="total, sizes, prev, pager, next, jumper"
      style="padding: 30px 0; text-align: center;">
    </el-pagination>

  </div>
</template>
<style>
.el-input--suffix .el-input__inner {
    padding-right: 10px;
}
</style>

<script>
// 引入接口哦定义文件
import scheduleSet from '@/api/schedule'

export default {
  // 送一变量和初始值
  data() {
    return {
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '170px',
      current: 1, //当前页
      limit: 5, //每页显示记录数
      pages: [5, 10, 15, 20],
      select: [],
      schedule:{},
      hospital:{},
      depList:[],
      docList:[],
      doctors:[],
      // hoscode: "",//医院编号
      list: [], //每页数据集合   
      total: 0, //数据总数
      isUpdate: 0 ,//是否修改操作
      searchDate:"",
      timeList:[{value: '上午',label: 0}, {value: '下午',label: 1}],
      statusList:[{value: '停诊',label: -1},{value: '停约',label: 0}, {value: '可约',label: 1}]

    }
  },
  created() {
    this.hospital.hoscode = localStorage.hoscode
    this.schedule.hoscode = localStorage.hoscode
    this.getRequest()
    this.getHopDocAll()
    this.getHopDepAll()
  },
  methods: {
    //返回医院列表
    back() {
      this.$router.push({ path: '/hospital/data' })
    },
    // 改变当前页
    changePage(page = 1){//添加当前页参数
      this.current = page
      this.getRequest()
    },
    // 改变页面显示数据数量
    changePageSize(pageSize = 3){//添加当前页参数
      this.limit = pageSize
      this.getRequest()
    },
    // 获取复选框的值
    handleSelectionChange(selection){
      this.select = selection
    },
    // 添加排班信息时，根据科室获取医生信息列表
    depChanged(){
      scheduleSet.getHospDocByDep(this.schedule.depcode)
      .then((response) => {
        console.log(response.data)
        this.doctors = response.data
        console.log("doctors=" , this.doctors)
      })

    },
    // 批量删除
    removeRows(){
      this.$confirm('此操作将永久删除所选的排班信息, 是否继续?', '提示', {
          cancelButtonText: '取消',
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          var selectIds = []
          for(var i = 0 ; i < this.select.length;i++){
            var id = this.select[i].id
            selectIds.push(id)
          }
          scheduleSet.batchRemoveSchedule(selectIds)
          .then((response) => {
            //提示 
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
            // 刷新页面
            this.changePage(1);
          })
        })
    },
    // id删除
    removeDataById(id){
      this.$confirm('此操作将永久删除该条排班信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          scheduleSet.removeScheById(id)
          .then((response) => {
            //提示 
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
            // 刷新页面
            this.changePage(1);
          })
        })
    },
    clear(){
      this.schedule = {}
      this.searchDate= ""
    },
    // 查询信息
    getRequest(){
      scheduleSet.getHospScheList(this.current, this.limit, this.schedule)
      .then((response) => { //请求成功response是接口返回数据
          console.log(response.data)
          this.list = response.data.records
          this.total = response.data.total
      })
      .catch((error) => { //请求失败
        console.log(error)
      })
    },
    // 根据id查询排班信息
    GetScheduleById(id){
      this.dialogFormVisible = true
      this.isUpdate = 1
      scheduleSet.GetScheduleById(id)
      .then((response) => {
            this.schedule = response.data
          })
    },
  //  修改医院排班信息
    updateDataById(id){
      this.isUpdate = 1
      this.dialogFormVisible = true
      scheduleSet.GetScheduleById(id)
      .then((response) => {
        console.log(response.data)
        this.schedule = response.data
        this.depChanged()
      })
    },

    getHopDocAll(){
      scheduleSet.getHospDoc(this.schedule.hoscode)
      .then((response) => {
        console.log(response.data)
        this.docList = response.data
      })
    },

    getHopDepAll(){
      scheduleSet.getHospDep(this.hospital)
      .then((response) => {
        console.log(response.data)
        this.depList = response.data
      })
    },

    // 添加医院排班信息
    addSchedule(){
      this.isUpdate = 0
      this.schedule = {}
      this.dialogFormVisible = true
    },
    // 保存医院排班信息
    saveAndUpdateSchedule(){
      this.dialogFormVisible = false
      this.schedule.hoscode = localStorage.hoscode
      console.log(this.schedule.hoscode)
      if(this.isUpdate){
        scheduleSet.updateSchedule(this.schedule)
        .then((response) => {
              console.log(this.schedule)
              //提示 
                this.$message({
                type: 'success',
                message: '修改成功!'
                });
              // 刷新页面
              window.location.reload()
            })
          .catch((error) => { //请求失败
            this.$message({
                type: 'success',
                message: '修改失败!'
              });
            console.log(error)
        })
      }else{
          scheduleSet.saveSchedule(this.schedule)
          .then((response) => {
            console.log(this.schedule)
              //提示 
                this.$message({
                type: 'success',
                message: '添加成功!'
                });
              // 刷新页面
              window.location.reload()
            })
          .catch((error) => { //请求失败
            this.$message({
                type: 'success',
                message: '添加失败!'
              });
            console.log(error)
        })
      }
      this.schedule = {}
    }
  }
}
</script>
