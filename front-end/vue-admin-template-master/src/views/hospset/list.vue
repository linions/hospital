<template>
    <div class="app-container">
  
      
      <!-- 条件搜索 -->
    <el-form :inline="true" class="demo-form-inline">
    <el-form-item>
        <el-select
            v-model="hosp.provinceCode"
            placeholder="请选择省"
            @change="provinceChanged">
            <el-option
                v-for="item in provinceList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"/>
        </el-select>
    </el-form-item>

    <el-form-item>
        <el-select
        v-model="hosp.cityCode"
        placeholder="请选择市"
        @change="cityChanged">
            <el-option
            v-for="item in cityList"
            :key="item.id"
            :label="item.name"
            :value="item.id"/>
        </el-select>
    </el-form-item>

    <el-form-item>
        <el-input v-model="hosp.hosname" placeholder="医院名称"/>
    </el-form-item>

    <el-button type="primary" icon="el-icon-search" @click="getRequest">查询</el-button>
    <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>



    <!-- 医院列表 v-loading="listLoading" -->
    <el-table 
      :data="list"
      border
      fit
      highlight-current-row>

    <el-table-column label="id"  prop="id" width="60" align="center"></el-table-column>

    <el-table-column label="医院logo" align="center" width="80">
        <template slot-scope="scope">
        <img :src="'../../../build/hospLogo/'+ scope.row.logoData" width="50">
        </template>
    </el-table-column>

    <el-table-column prop="hosname"  align="center" label="医院名称"/>
    <el-table-column prop="param.hostypeName"  align="center" label="等级" width="90"/>
    <el-table-column prop="param.fullAddress" align="center" label="详情地址" width="180"/>
    <el-table-column label="状态" align="center" width="66">
        <template slot-scope="scope">
                {{ scope.row.status === 0 ? '未上线' : '已上线' }}
        </template>
    </el-table-column>
    <el-table-column prop="createTime" align="center" label="创建时间"/>

    <el-table-column label="操作" align="center" width="245">
        <template slot-scope="scope">
            <router-link :to="'/hospSet/hospital/show/' + scope.row.hoscode">
              <el-button type="primary" size="mini"  icon="el-icon" >查看</el-button>
            </router-link>
            <router-link :to="'/hospSet/hospital/schedule/' + scope.row.hoscode">
              <el-button type="warning" size="mini"  icon="el-icon" >排班计划</el-button>
            </router-link>
            <!-- <el-button type="warning" size="mini" 
            icon="el-icon" >排班计划</el-button> -->
            <el-button v-if="scope.row.status==1" type="danger" size="mini" style="margin:0px"
            icon="el-icon" @click="updateStatus(scope.row.id,0)">下架</el-button>
            <el-button v-if="scope.row.status==0" type="success" size="mini" style="margin:0px"
            icon="el-icon" @click="updateStatus(scope.row.id,1)">上线</el-button>
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
    .el-card{
      border:none; 
      background-color: #1f2d3d; 
      border-radius: 5px; margin:0px 20px 5px; 
      border: 1px solid #EBEEF5;
      color: white;
    }
  </style>
  <script>
  // 引入接口哦定义文件
  import hospital from '@/api/list'
  
  export default {
    // 送一变量和初始值
    data() {
      return {
        listLoading:true,//数据是否正在被加载
        list: [],
        current: 1, //当前页
        limit: 5, //每页显示记录数
        pages: [5, 10, 12, 15],
        select: [],
        hosp:{},//封装医院对象
        list: [], //每页数据集合
        provinceList:[], //所有的省集合
        cityList:[], //所有的市集合   
        total: 0, //数据总数
        img:[]
      }
    },
    created() {
      this.getRequest()
      this.findAllProvince()
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
       // 获取列表信息
       getRequest(){
        hospital.getHospitalList(this.current, this.limit, this.hosp)
        .then((response) => { //请求成功response是接口返回数据
            this.list = response.data.records
            console.log(this.list)
            for(var i = 0 ; i < this.list.length; i++){
              if(this.list[i].logoData){
                this.list[i].logoData = this.list[i].logoData.split('\\')
                this.list[i].logoData = this.list[i].logoData[this.list[i].logoData.length - 1]
              }else this.list[i].logoData = ''
            }
            this.total = response.data.total
            // 加载图标不显示
            // this.listLoading = false
        })
        .catch((error) => { //请求失败
          console.log(error)
        })
      },
    //   重置查询条件
      resetData(){
        this.hospital = {}
      },


    // 查询所有的省
    findAllProvince(){
        hospital.findByDictCode('Province')
        .then((response) => { //请求成功response是接口返回数据
            this.provinceList = response.data
            console.log(this.provinceList)
        })
        .catch((error) => { //请求失败
          console.log(error)
        })
    },
    // 点击某个省，显示市
    provinceChanged(){
        // 初始化值
        this.cityList = []
        this.hosp.cityCode = null
        // 调用方法，根据省id，查询下面的子节点
        hospital.findChildData(this.hosp.provinceCode)
        .then(response =>{
            this.cityList = response.data
            console.log(this.hosp.provinceCode)
            
        })
    },
    cityChanged(){

    },
    updateStatus(id,status){      
        hospital.updateHospStatus(id,status)
        .then((response) => {
            //提示 
            if(this.hosp.status == 1){
              this.$message({
              type: 'success',
              message: '成功上线!'
              });
            }else{
              this.$message({
              type: 'success',
              message: '下架成功!'
              });
            }
            // 刷新页面
            this.changePage(1);
          })
        .catch((error) => { //请求失败
        console.log(error)
      })
    }
  }
}
  </script>
  