<template>
    <div class="app-container">
  
      <!-- 条件搜索 -->
      <el-form :inline="true"  class="demo-form-inline">
        <el-form :inline="true"  class="demo-form-inline" :model="doctor">
        <el-form-item label="医生编号">
          <el-input v-model="doctor.id" placeholder="医生编号"></el-input>
        </el-form-item>
        <el-form-item label="科室名称" :label-width="formLabelWidth">
        <el-select v-model="doctor.depcode"  filterable placeholder="科室名称">
            <el-option v-for="item in depList"
            :key="item.id"
            :label="item.depname"
            :value="item.depcode">
            </el-option>
        </el-select>
      </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="getRequest">查询</el-button>
          <el-button type="danger" @click="clear">清空</el-button>
          <el-button @click="back" style="margin-left: 150px;">返回</el-button>
        </el-form-item>
      </el-form>
      </el-form>
  
      <!-- 工具条 -->
  <div>
    <!-- @click="AddHospitalSet" -->
     <el-button type="danger" style="margin-bottom: 5px;" size="mini" @click="removeRows()">批量删除</el-button>
     <el-button type="success" style="margin-bottom: 5px;" size="mini" @click="addDoctor()">添加</el-button>
  
  
  
  
    <!-- 添加表单 -->
     <el-dialog  title="添加医生信息" :visible.sync="dialogFormVisible" style="text-align: center; " >
      <el-form :model="doctor">
        <el-form-item label="医生姓名" :label-width="formLabelWidth" >
          <el-input v-model="doctor.name" placeholder="医生姓名" style="width: 200px; margin-left: 60px;float: left;"></el-input>
        </el-form-item>

        <el-form-item label="所属科室" :label-width="formLabelWidth">
          <el-select v-model="doctor.depcode"  filterable placeholder="所属科室" style="margin-left: 60px;float: left;">
              <el-option v-for="item in depList"
              :key="item.id"
              :label="item.depname"
              :value="item.depcode">
              </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="职称" :label-width="formLabelWidth">
          <el-select v-model="doctor.title"  filterable placeholder="职称" style="margin-left: 60px;float: left;">
              <el-option v-for="item in titleList"
              :key="item.value"
              :label="item.value"
              :value="item.value">
              </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="擅长技能" :label-width="formLabelWidth">
          <el-input v-model="doctor.skill" style="width: 300px;" type="textarea" :rows="2" placeholder="请输入医生擅长技能"/>
        </el-form-item>
        <el-form-item label="挂号费" :label-width="formLabelWidth">
          <el-input v-model="doctor.amount" style="width: 150px;margin-left: 60px;float: left;" placeholder="请输入挂号费"/>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth">
          <el-input v-model="doctor.phone" style="width: 250px;margin-left: 60px;float: left;" placeholder="联系电话"  maxLength='11'/>
        </el-form-item>
        <el-form-item label="医生简介" :label-width="formLabelWidth">
          <el-input v-model="doctor.intro"  style="width: 300px" type="textarea" :rows="3" placeholder="请输入医生简介"/>
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="dialogFormVisible = false;doctor = {}">取 消</el-button>
        <el-button type="primary" @click="saveAndUpdateDoc">确 定</el-button>
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
      <el-table-column prop="id" align="center" label="医生id" width="120"/>
      <el-table-column prop="param.department.depname" align="center" label="所属科室" width="120"/>
      <el-table-column prop="name" align="center" label="医生名称" width="120"/>
      <el-table-column prop="title" align="center" label="职称" width="100"/>
      <el-table-column prop="skill" align="center" label="擅长技能" width="180"/>
      <el-table-column prop="amount" align="center" label="挂号费" width="70"/>
      <el-table-column prop="phone" align="center" label="联系电话" width="150"/>
      <el-table-column prop="intro" align="center" label="医生简介" width="100">
      <template slot-scope="scope" >
        <el-button type="primary" size="mini" 
            icon="el-icon" @click="checkIntro(scope.row.intro)">查看</el-button>
      </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="150">
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
  import doctorSet from '@/api/doctor'
  
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
        doctor:{},
        hospital:{},
        depList:[],
        // hoscode: "",//医院编号
        list: [], //每页数据集合   
        total: 0, //数据总数
        isUpdate: 0 ,//是否修改操作
        titleList:[{value:"普通医师",label: 1},{value:"住院医师",label: 1},{value:"主治医师",label: 2},{value:"副主任医师",label: 3},{value:"主任医师",label: 4}]
        
  
      }
    },
    created() {
      this.getRequest()
      this.getHopDepAll()
    },
    methods: {
    //返回医院列表
    back() {
      this.$router.push({ path: '/hospital/data' })
    },
  // 查看医生介绍
    checkIntro(intro) {
      var info = intro
      if(info == null || info.length == 0) info = '无'
      this.$alert(info, '医生介绍', {
      confirmButtonText: '确定',
      });
    },

      // 获取科室信息
      getHopDepAll(){
      this.hospital.hoscode = localStorage.hoscode
      doctorSet.getHospDep(this.hospital)
      .then((response) => {
        console.log(response.data)
        this.depList = response.data
      })
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
    
    //   // 批量删除
      removeRows(){
        this.$confirm('此操作将永久删除所选的医生信息, 是否继续?', '提示', {
            cancelButtonText: '取消',
            confirmButtonText: '确定',
            type: 'warning'
          }).then(() => {
            var selectIds = []
            for(var i = 0 ; i < this.select.length;i++){
              var id = this.select[i].id
              selectIds.push(id)
            }
            doctorSet.batchRemoveDoc(selectIds)
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
    //   // id删除
      removeDataById(id){
        this.$confirm('此操作将永久删除该条医生信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            doctorSet.removeDocById(id)
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
        this.doctor = {}
      },
      // 查询信息
      getRequest(){
        this.doctor.hoscode = localStorage.hoscode
        doctorSet.getDocList(this.current, this.limit, this.doctor)
        .then((response) => { //请求成功response是接口返回数据
            console.log(response.data)
            this.list = response.data.records
            this.total = response.data.total
        })
        .catch((error) => { //请求失败
          console.log(error)
        })
      },

    //  修改医院医生信息
      updateDataById(id){
        this.isUpdate = 1
        this.dialogFormVisible = true
        doctorSet.getDocById(id)
        .then((response) => {
          console.log(response.data)
          this.doctor = response.data
        })
      },
  
      // 添加医院医生信息
      addDoctor(){
        this.isUpdate = 0
        this.doctor = {}
        this.dialogFormVisible = true
      },
    //   // 保存医院医生信息
    saveAndUpdateDoc(){
        this.dialogFormVisible = false
        this.doctor.hoscode = localStorage.hoscode
        if(this.isUpdate){
          doctorSet.updateDoc(this.doctor)
          .then((response) => {
                //提示 
                  this.$message({
                  type: 'success',
                  message: '修改成功!'
                  });
                // 刷新页面
                window.location.reload()
                this.doctor = {}
              })
            .catch((error) => { //请求失败
              this.$message({
                  type: 'success',
                  message: '修改失败!'
                });
              console.log(error)
          })
        }else{
            doctorSet.saveDoc(this.doctor)
            .then((response) => {
                console.log(this.doctor)
                //提示 
                  this.$message({
                  type: 'success',
                  message: '添加成功!'
                  });
                // 刷新页面
                window.location.reload()
                this.doctor = {}
              })
            .catch((error) => { //请求失败
              this.$message({
                  type: 'success',
                  message: '添加失败!'
                });
              console.log(error)
          })
        }
        this.doctor = {}
      }
    }
  }
  </script>
  