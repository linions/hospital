<template>
  <div class="app-container">

    <!-- 条件搜索 -->
    <el-form :inline="true"  class="demo-form-inline" :model="hospDep">
      <el-form-item label="科室名称">
        <el-input v-model="hospDep.depname" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item label="科室编号">
        <el-input v-model="hospDep.depcode" placeholder="编号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getRequest">查询</el-button>
        <el-button type="danger" @click="clear">清空</el-button>
        <el-button @click="back" style="margin-left: 250px;">返回</el-button>
      </el-form-item>
    </el-form>

    <!-- 工具条 -->
<div>
   <el-button type="danger" style="margin-bottom: 5px;" size="mini" @click="removeRows()">批量删除</el-button>
   <el-button type="success" style="margin-bottom: 5px;" size="mini" @click="AddHospitalDep">添加</el-button> 

  <!-- 添加表单 -->
   <el-dialog  title="科室信息" :visible.sync="dialogFormVisible" style="text-align: center; " >
    <el-form :model="hospDep">
      <el-form-item label="科室编号" :label-width="formLabelWidth">
        <el-input v-model="hospDep.depcode" style="width: 300px;"/>
      </el-form-item>
      <el-form-item label="科室名称" :label-width="formLabelWidth">
        <el-input v-model="hospDep.depname" style="width: 300px;"/>
      </el-form-item>
      <el-form-item label="隶属科室" :label-width="formLabelWidth">
        <el-select @change="changeValue(hospDep.bigcode)" v-model="hospDep.bigcode" style="margin-left: 60px;float: left;" filterable placeholder="请选择">
          <el-option  v-for="item in depList" :key="item.id" :label="item.depname" :value="item.depcode"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="科室简介" :label-width="formLabelWidth">
        <el-input v-model="hospDep.intro" style="width: 300px;"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" >
      <el-button @click="cancel">取 消</el-button>
      <el-button type="primary" @click="saveAndUpdateHospDep()">确 定</el-button>
    </div>
  </el-dialog>
</div>




    <el-table
      :data="list"
      style="width: 100%"
      row-key="id"
      center
      border
      lazy
      :load="getChildren"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      :row-class-name="tableRowClassName" @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center" width="40"/>
      <el-table-column label="id" width="200" prop="id" align="center"></el-table-column>
      <el-table-column label="名称" width="200"  prop="depname" align="center"></el-table-column>
      <el-table-column label="编号" prop="depcode" width="200" align="center"></el-table-column>
      <el-table-column label="科室简介" align="center" width="100">
      <template slot-scope="scope" >
        <el-button type="primary" size="mini" 
            icon="el-icon" @click="checkIntro(scope.row.depcode)">查看</el-button>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
      <template slot-scope="scope">
        <el-button type="primary" size="mini" 
            icon="el-icon" @click="getHospDepByCode(scope.row.depcode)">修改</el-button>
        <el-button type="danger" size="mini" 
            icon="el-icon" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
      </el-table>


  </div>
</template>
<style>
.el-table .child .cell .el-checkbox__inner{
      background:  rgb(48 65 86);;
      display:none;
}
</style>

<script>
// 引入接口哦定义文件
import department from '@/api/department'

export default {
  // 送一变量和初始值
  data() {
    return {
      // dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '170px',
      select: [],
      hospital:{},
      hospDep:{},
      list: [], //每页数据集合   
      isUpdate: 0, //是否修改操作
      depList:[],
      dep:{},
    }
  },
  created() {
    this.hospital.hoscode = localStorage.hoscode
    this.hospital.hosname = localStorage.hosname
    this.getHospDepAll()
    
  },
  methods: {
    clear(){
      this.hospDep = {}
    },
    //返回医院列表
    back() {
      this.$router.push({ path: '/hospital/data' })
    },

    changeValue(bigcode){
      // console.log(bigcode)
      department.getHospitalDepByCode(bigcode)
      .then((response) => {
        this.hospDep.bigname = response.data.depname
      })
    },
    // 保存医院信息
    saveAndUpdateHospDep(){
      this.dialogFormVisible = false
      this.hospDep.hoscode = this.hospital.hoscode 
      this.hospDep.hosname = this.hospital.hosname   
      // console.log(this.hospDep)    
      if(this.isUpdate){
        department.updateHospDep(this.hospDep)
        .then((response) => {
              //提示 
                this.$message({
                type: 'success',
                message: '修改成功!'
                });
              // 刷新页面
              this.getHospDepAll()
              this.hospDep={}
            })
          .catch((error) => { //请求失败
            this.$message({
                type: 'error',
                message: '修改失败!'
              });
            console.log(error)
        })
      }else{
        console.log(this.hospDep)
        department.saveHospitalDep(this.hospDep)
          .then((response) => {
              //提示 
                this.$message({
                type: 'success',
                message: '添加成功!'
                });
              // 刷新页面
              this.getHospDepAll()
              this.hospDep={}
              
            })
          .catch((error) => { //请求失败
            this.$message({
                type: 'error',
                message: '添加失败!'
              });
            console.log(error)
        })
      }
    },

    // 设置表格父子科室显示样式
    tableRowClassName({row, rowIndex}){
      if(row.bigname == "全部类别" ){
        return "parent"
      }else{
        return "child"
      }
    },
    //获取该医院科室的所有信息
    getHospDepAll(){
      department.getHospMainDep(this.hospital)
      .then(response => {
          this.depList.push(response.data)
          this.getDepData(response.data.depcode)
        })
        .catch(error =>{
            console.log(error)
        })
    },
    // 跟据科室编号获取子科室信息
    getDepData(depCode){
        department.getHospDep(depCode)
        .then(response => {
            this.list = response.data
            for(var i = 0;i < this.list.length ;i++){
              this.depList.push(this.list[i])
            }
        })
        .catch(error =>{
            console.log(error)
        })
    },
    // 树形获取子科室信息
    getChildren(tree, treeNode, resolve) {
      console.log(tree)
      console.log(treeNode)
      department.getHospDep(tree.depcode)
      .then(response => {
          resolve(response.data)
      })
    },
    // 查看科室介绍
    checkIntro(depcode) {
        department.getHospitalDepByCode(depcode)
        .then((response) => { //请求成功response是接口返回数据
          var info = response.data.intro
          if(info == null || info.length == 0) info = '无'
          this.$alert(info, '科室介绍', {
          confirmButtonText: '确定',
        });
        })
        .catch((error) => { //请求失败
          console.log(error)
        })
      },

    // 获取复选框的值
    handleSelectionChange(selection){
      this.select = selection
    },

    // 批量删除
    removeRows(){
      this.$confirm('此操作将永久删除所选的信息, 是否继续?', '提示', {
          cancelButtonText: '取消',
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          var selectIds = []
          for(var i = 0 ; i < this.select.length;i++){
            var id = this.select[i].id
            selectIds.push(id)
          }
          department.batchRemoveHospDep(selectIds)
          .then((response) => {
            //提示 
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
            // 刷新页面
            this.getDepData(0);
          })
        })
    },
    // 通过数据删除
    removeDataById(id){
      this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          department.removeHospDepById(id)
          .then((response) => {
            //提示 
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
            // 刷新页面
            window.location.reload()
          })
        })
    },
    // 查询信息
    getRequest(){
      department.getHospDepList(this.hospDep)
      .then((response) => { //请求成功response是接口返回数据
          this.list = response.data

      })
      .catch((error) => { //请求失败
        console.log(error)
      })
    },
    // // 根据depcode查询医院信息
    getHospDepByCode(depcode){
      this.dialogFormVisible = true
      this.isUpdate = 1
      department.getHospitalDepByCode(depcode)
      .then((response) => {
            this.hospDep = response.data
      })
    },
    // 添加医院信息
    AddHospitalDep(){
      this.isUpdate = 0
      this.hospDep = {}
      this.dialogFormVisible = true
      this.hospDep.bigcode = this.depList[0].depcode
      this.hospDep.bigname = this.depList[0].depname
    },

    // 取消保存数据
    cancel(){
      this.dialogFormVisible = false
      this.hospDep={}
    },
  }
}
</script>
