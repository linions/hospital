<template>
  <div class="app-container">

    
    <!-- 条件搜索 -->
    <el-form :inline="true"  class="demo-form-inline">
      <el-form-item label="医院名称">
        <el-input v-model="hospSet.hosname" placeholder="医院名称"></el-input>
      </el-form-item>
      <el-form-item label="医院编号">
        <el-input v-model="hospSet.hoscode" placeholder="医院编号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getRequest">查询</el-button>
      </el-form-item>
    </el-form>

    <el-main >
      <!-- 医院列表 -->
    <el-col :span="20" v-for="(item) in list" :key="item.id">
      <el-card shadow="never">
        <span style="margin-left: 25px ; display: inline-block; font-size: large; color: whitesmoke;">
          {{item.hoscode}}
        </span>
        <span style="margin-left: 125px ; display: inline-block;">
          {{item.hosname}}
        </span>
        <span style="float:right;">
          <el-button type="primary" size="mini" 
          icon="el-icon" @click="infoSet(item.hoscode,item.hosname)">信息设置</el-button>
          <el-button type="success" size="mini" 
          icon="el-icon" @click="depSet(item.hoscode,item.hosname)">科室管理</el-button>
          <el-button type="warning" size="mini" 
          icon="el-icon" @click="scheduleSet(item.hoscode)">排班计划</el-button>
          <el-button type="danger" size="mini" 
          icon="el-icon" @click="docSet(item.hoscode)">医生管理</el-button>
        </span>
      </el-card>
    </el-col>
    </el-main>


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
  .el-card{
    border:none; 
    background-color: #1f2d3d; 
    border-radius: 5px; 
    margin:0px 5px 5px; 
    border: 1px solid #EBEEF5;
    color: white;
  }
</style>
<script>
// 引入接口哦定义文件
import hospData from '@/api/data'

export default {
  // 送一变量和初始值
  data() {
    return {
      list: [],
      current: 1, //当前页
      limit: 8, //每页显示记录数
      pages: [8, 16, 24, 32],
      select: [],
      hospSet:{},//封装医院对象
      list: [], //每页数据集合   
      total: 0, //数据总数
    }
  },
  created() {
    this.getRequest()
  },
  methods: {
    // 改变当前页
    changePage(page = 1){//添加当前页参数
      this.current = page
      this.getRequest()
    },
    // 改变页面显示数据数量
    changePageSize(pageSize){//添加当前页参数
      this.limit = pageSize
      this.changePage(1)
    },
     // 查询信息
     getRequest(){
      hospData.getHospSetList(this.current, this.limit, this.hospSet)
      .then((response) => { //请求成功response是接口返回数据
          console.log(response.data)
          this.list = response.data.records
          this.total = response.data.total
      })
      .catch((error) => { //请求失败
        console.log(error)
      })
    },
    //进入医院信息设置
    infoSet(hoscode,hosname){
      localStorage.hoscode = hoscode
      localStorage.hosname = hosname
      this.$router.push({path: '/hospital/info'})
    },
    //进入科室设置
    depSet(hoscode,hosname){
      localStorage.hoscode = hoscode
      localStorage.hosname = hosname
      this.$router.push({path: '/hospital/department'})
    },
    //进入排班计划管理
    scheduleSet(hoscode){
      localStorage.hoscode = hoscode
      this.$router.push({path: '/hospital/schedule'})
    },
    //进入医院医生管理
    docSet(hoscode){
      localStorage.hoscode = hoscode
      this.$router.push({path: '/hospital/doctor'})
    }


  }
}
</script>
