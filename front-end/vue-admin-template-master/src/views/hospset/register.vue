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

    <!-- 工具条 -->
<div>
   <el-button type="danger" style="margin-bottom: 5px;" size="mini" @click="removeRows()">批量删除</el-button>
   <el-button type="success" style="margin-bottom: 5px;" size="mini" @click="AddHospitalSet">添加</el-button>

  <!-- 添加表单 -->
   <el-dialog  title="医院信息" :visible.sync="dialogFormVisible" style="text-align: center; " >
    <el-form :model="hospSet">
      <el-form-item label="医院名称" :label-width="formLabelWidth">
        <el-input v-model="hospSet.hosname" style="width: 300px;"/>
      </el-form-item>
      <el-form-item label="医院编号" :label-width="formLabelWidth">
        <el-input v-model="hospSet.hoscode" style="width: 300px;"/>
      </el-form-item>
      <el-form-item label="api基础路径" :label-width="formLabelWidth">
        <el-input v-model="hospSet.apiUrl" style="width: 300px;"/>
      </el-form-item>
      <el-form-item label="联系人姓名" :label-width="formLabelWidth">
        <el-input v-model="hospSet.contactsName" style="width: 300px;"/>
      </el-form-item>
      <el-form-item label="联系人手机" :label-width="formLabelWidth">
        <el-input v-model="hospSet.contactsPhone" style="width: 300px;"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" >
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveAndUpdateHospSet">确 定</el-button>
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
    <el-table-column prop="hosname" align="center" label="医院名称" width="170"/>
    <el-table-column prop="hoscode" align="center" label="医院编号" width="100"/>
    <el-table-column prop="apiUrl" align="center" label="api基础路径" width="170"/>
    <el-table-column prop="contactsName" align="center" label="联系人姓名" width="100"/>
    <el-table-column prop="contactsPhone" align="center" label="联系人电话" width="120"/>
    <el-table-column label="状态" align="center" width="65">
    <template slot-scope="scope">
              {{ scope.row.status === 1 ? '可用' : '不可用' }}
    </template>
    </el-table-column>
    <el-table-column label="操作" align="center">
    <template slot-scope="scope">
      <el-button type="primary" size="mini" 
         icon="el-icon" @click="GetHospitalById(scope.row.id)">修改</el-button>
      <el-button type="danger" size="mini" 
         icon="el-icon" @click="removeDataById(scope.row.id)">删除</el-button>
      <el-button v-if="scope.row.status==1" type="danger" size="mini" 
         icon="el-icon" @click="lockHostSet(scope.row.id,0)">锁定</el-button>
      <el-button v-if="scope.row.status==0" type="success" size="mini" 
         icon="el-icon" @click="lockHostSet(scope.row.id,1)">解锁</el-button>

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

 <script>
// 引入接口哦定义文件
import register from '@/api/register'

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
      hospSet:{},//封装hospSet对象
      list: [], //每页数据集合   
      total: 0, //数据总数
      isUpdate: 0 //是否修改操作
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
    // 获取复选框的值
    handleSelectionChange(selection){
      this.select = selection
    },
    // 锁定与解锁
    lockHostSet(id,status){
      register.lockHostSet(id,status)
        .then((response) => {
            //提示 
            if(status == 1){
              this.$message({
              type: 'success',
              message: '解锁成功!'
              });
            }else{
              this.$message({
              type: 'success',
              message: '锁定成功!'
              });
            }
            // 刷新页面
            this.changePage(1);
          })
        .catch((error) => { //请求失败
        console.log(error)
      })
    },
    // 批量删除
    removeRows(){
      this.$confirm('此操作将永久删除所选的医院信息, 是否继续?', '提示', {
          cancelButtonText: '取消',
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          var selectIds = []
          for(var i = 0 ; i < this.select.length;i++){
            var id = this.select[i].id
            selectIds.push(id)
          }
          register.batchRemoveHospSet(selectIds)
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
      this.$confirm('此操作将永久删除该条医院信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          register.removeDataById(id)
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
    // 查询信息
    getRequest(){
      register.getHospSetList(this.current, this.limit, this.hospSet)
      .then((response) => { //请求成功response是接口返回数据
          console.log(response.data)
          this.list = response.data.records
          this.total = response.data.total
      })
      .catch((error) => { //请求失败
        console.log(error)
      })
    },
    // 根据id查询医院信息
    GetHospitalById(id){
      this.dialogFormVisible = true
      this.isUpdate = 1
      register.GetHospitalById(id)
      .then((response) => {
            this.hospSet = response.data
          })
    },
    // 添加医院信息
    AddHospitalSet(){
      this.isUpdate = 0
      this.hospSet = {}
      this.dialogFormVisible = true
    },
    // 保存医院信息
    saveAndUpdateHospSet(){
      this.dialogFormVisible = false
      if(this.isUpdate){
        register.updateHospSet(this.hospSet)
        .then((response) => {
              //提示 
                this.$message({
                type: 'success',
                message: '修改成功!'
                });
              // 刷新页面
              this.hospSet = {}
              this.changePage(this.current)
            })
          .catch((error) => { //请求失败
            this.$message({
                type: 'success',
                message: '修改失败!'
              });
            console.log(error)
        })
      }else{
        register.saveHospitalSet(this.hospSet)
          .then((response) => {
              //提示 
                this.$message({
                type: 'success',
                message: '添加成功!'
                });
              // 刷新页面
              this.hospSet = {}
              this.changePage(1)
              
            })
          .catch((error) => { //请求失败
            this.$message({
                type: 'success',
                message: '添加失败!'
              });
            console.log(error)
        })
      }

    }
  }
}
</script>
