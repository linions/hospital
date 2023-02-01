<template>
    <div class="app-container">
        <!-- 添加表单 -->
        <div>
            <el-form :model="hospital" style="color:black">
            <el-form-item style="display:inline-block" label="医院名称:" :label-width="formLabelWidth">
                <el-input v-model="hospital.hosname"  :disabled="true" style="width: 300px;"/>
            </el-form-item>
            <el-form-item label="医院编号:" style="display:inline-block" :label-width="formLabelWidth">
                <el-input v-model="hospital.hoscode" :disabled="true" style="width: 300px;"/>
            </el-form-item>

            <el-form-item label="医院等级:" style="display:inline-block" :label-width="formLabelWidth">
                <el-select v-model="hospital.hostype" filterable placeholder="请选择" :disabled="isUpdate">
                    <el-option v-for="item in levelList"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                    </el-option>
                </el-select>
                
            </el-form-item>
            <el-form-item label="医院标志:"  :label-width="formLabelWidth">
                <el-upload action="http://localhost:9001/admin/hospital/hospData/uploadLogo" 
                    list-type="picture-card"  :class="{hide:hideUpload}" 
                    :file-list="fileList" 
                     name="logo" :on-success="handleSuccess"  :disabled="isUpdate">
                    
                    <el-image v-if="img" class="table-td-thumb" :src="require('../../../build/hospLogo/' + img)"/>
                    <i v-else slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{file}">
                    <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" >
                    <span class="el-upload-list__item-actions">
                        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                            <i class="el-icon-zoom-in"></i>
                        </span>
                        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                            <i class="el-icon-delete"></i>
                        </span>
                    </span>
                    </div>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
                   
                
                
            </el-form-item>
            
            <el-form-item label="医院位置:" style="display:inline-block" :label-width="formLabelWidth">
                <el-select v-model="hospital.provinceCode" @change="provinceChanged" filterable placeholder="请选择省" :disabled="isUpdate">
                    <el-option v-for="item in provinceList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                    </el-option>
                </el-select>
                <el-select v-model="hospital.cityCode" @change="cityChanged" filterable placeholder="请选择市" :disabled="isUpdate">
                    <el-option v-for="item in cityList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                    </el-option>
                </el-select>
                <el-select v-model="hospital.districtCode"  filterable placeholder="请选择区" :disabled="isUpdate">
                    <el-option v-for="item in districtList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="具体地址:" style="display:inline-block" :label-width="formLabelWidth">
                <el-input v-model="hospital.address" style="width: 600px;" :disabled="isUpdate"/>
            </el-form-item>
            <el-form-item label="医院介绍:" :label-width="formLabelWidth">
                <el-input autosize style="width: 600px" type="textarea" :rows="8" placeholder="请输入医院介绍" v-model="hospital.intro" :disabled="isUpdate"/>
            </el-form-item>
            <el-form-item label="坐车路线:" :label-width="formLabelWidth">
                <el-input autosize style="width: 600px" type="textarea" :rows="4" placeholder="请输入坐车路线" v-model="hospital.route" :disabled="isUpdate"/>
            </el-form-item>
            <el-form-item label="预约规则:"  :label-width="formLabelWidth">
                <el-form ref="form"  :model="bookingRule" label-width="80px">
                    <el-form-item label="预约周期" >
                        <el-select v-model="bookingRule.cycle" filterable placeholder="请选择" :disabled="isUpdate">
                            <el-option v-for="item,index in cycleList"
                            :key="item"
                            :label="item"
                            :value="index+1">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="预约时间" >
                        <el-time-picker
                            :disabled="isUpdate"
                            v-model="bookingRule.releaseTime"
                            format = 'HH:mm' value-format = 'HH:mm'
                            placeholder="开始时间">
                        </el-time-picker>
                        至
                        <el-time-picker
                            :disabled="isUpdate"
                            v-model="bookingRule.stopTime"
                            format = 'HH:mm' value-format = 'HH:mm'
                            placeholder="结束时间">
                        </el-time-picker>
                    </el-form-item>
                    <el-form-item label="退号截止天数" label-width="100px" >
                        <el-select v-model="bookingRule.quitDay" filterable placeholder="请选择" :disabled="isUpdate">
                            <el-option v-for="item,index in backDayList"
                                :key="item"
                                :label="item"
                                :value="index-1">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="退号时间" >
                        <el-time-picker
                            :disabled="isUpdate"
                            v-model="bookingRule.quitTime"
                            format = 'HH:mm' value-format = 'HH:mm'
                            placeholder="任意时间点">
                        </el-time-picker>
                    </el-form-item>
                    <el-form-item label="预约方式" >
                        <div v-for="(item,index) in bookingRule.rule" :key="index">
                            <el-input autosize style="width: 600px" :value="(index+1)+'、'+ item" type="textarea" :rows="1"  :disabled="isUpdate"/>
                        </div>
                        <span style="margin-left: 470px;">
                            <el-button :disabled="isUpdate" type="primary" size="mini" @click="addRule">添加</el-button>
                            <el-button :disabled="isUpdate || canDel" type="danger"  size="mini" @click="deleteRule">删除</el-button>
                        </span>
                        
                    </el-form-item>
                </el-form>
            </el-form-item>
               
            </el-form>
            <div slot="footer" style="float:right" class="dialog-footer" >
                <el-button @click="back">返回</el-button>
                <el-button v-if="isUpdate" type="primary" @click="updateInfo">修 改</el-button>
                <el-button v-else type="primary" @click="saveUpDate">确 定</el-button>
            </div>
        </div>

    </div>
  </template>
<style>
    .el-input__inner{
        color: rgb(23, 22, 22);
    }
    .el-input.is-disabled .el-input__inner{
        color: #4a4d53;
    }

    .hide .el-upload--picture-card {
        display: none;
    }
    .el-form-item .el-form-item{
        margin-bottom:5px ;
    }


</style>
    
<script>
  
  // 引入接口哦定义文件
  import infoSet from '@/api/info'
  
  export default {
    // 送一变量和初始值
    data() {
      return {
        isUpdate: true,
        canDel:false ,
        levelList:[],
        provinceList:[],
        cityList:[],
        districtList:[],
        cycleList:["1","2","3","4","5","6","7","8","9","10"],
        backDayList:["前一天","当天"],
        hospital:{},
        bookingRule:{},
        formLabelWidth: '170px',
        //上传图片数据
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,    
        hideUpload: false,
        fileList:[],
        img:'',
      }
    },
    created() {
        this.getHospitalInfo(localStorage.hoscode)
        this.findAllProvince()
    },
    methods: {
        //返回医院列表
        back() {
            this.$router.push({ path: '/hospital/data' })
        },

        //  图片上传按钮
        handleSuccess(response, file, fileList){
            this.fileList = fileList
            this.hideUpload = true;
            this.hospital.logoData = response.data +"\\"+ file.name
            console.log(this.hospital.logoData)

        },

    //   删除图片
        handleRemove(file) {
            this.fileList.pop()
            this.hideUpload = false;
            infoSet.uninstallLogo(file.name)
            this.hospital.logoData = null
      },
    // 放大查看图片
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        console.log(file.url)
        this.dialogVisible = true;
        },

        //添加预约方式
        addRule(){
            this.canDel = false;
            var len = this.bookingRule.rule.length
            this.bookingRule.rule[len] = " "
            this.hospital.bookingRule = this.bookingRule
            this.isUpdate = true
            this.isUpdate = false
        },
        //删除预约方式
        deleteRule(){
            var len = this.bookingRule.rule.length
            this.$delete(this.bookingRule.rule,len-1)
            if(len - 1 <= 0) this.canDel = true
            this.hospital.bookingRule = this.bookingRule
            this.isUpdate = true
            this.isUpdate = false
        },
        //修改按钮时间
        updateInfo(){
            this.isUpdate = false
        },
        // 保存修改
        saveUpDate(){
            // this.hospital.bookingRule=this.bookingRule
            this.hospital.bookingRule = JSON.stringify(this.bookingRule)
            console.log(this.hospital)
            this.isUpdate = true
            infoSet.UpdateHospInfo(this.hospital)
            .then((response) => { //请求成功response是接口返回数据
                this.$message({
                type: 'success',
                message: '修改成功!'
                });
            })
            .catch((error) => { //请求失败
                console.log(error)
                this.$message({
                type: 'error',
                message: '修改失败!'
                });
            })
        },

    //   获取医院信息
    getHospitalInfo(hoscode){
        infoSet.getHospInfo(hoscode)
        .then((response) => { //请求成功response是接口返回数据
          console.log(response.data)
          this.hospital = response.data
          this.bookingRule = this.hospital.bookingRule
          if(this.hospital.logoData){
            this.img = this.hospital.logoData.split('\\')
            this.img = this.img[this.img.length - 1]
            console.log(this.img)
            console.log(typeof(this.img))
          }else this.img = ''
          console.log(this.hospital.logoData)

      })
      .catch((error) => { //请求失败
        console.log(error)
      })
    },
    // 获取所有的信息所有的省、市、区、医院等级等信息
    findAllProvince(){
        infoSet.findByDictCode('Province')
        .then((response) => { //请求成功response是接口返回数据
            this.provinceList = response.data
            // console.log(this.provinceList)
            var codeCity = this.hospital.cityCode
            this.provinceChanged()
            this.hospital.cityCode = codeCity
            var codeDistrict = this.hospital.districtCode
            this.cityChanged()
            this.hospital.districtCode = codeDistrict
            this.getHospLevel()
        })
        .catch((error) => { //请求失败
          console.log(error)
        })
    },
    // 点击某个省，显示市
    provinceChanged(){
        // 初始化值
        this.cityList = []
        this.hospital.cityCode = null
        // 调用方法，根据省id，查询下面的子节点
        infoSet.findChildData(this.hospital.provinceCode)
        .then(response =>{
            this.cityList = response.data 
            // console.log(this.cityList)        
        })
    },
    // 点击某个市，显示区
    cityChanged(){
        // 初始化值
        this.districtList = []
        this.hospital.districtCode = null
        // 调用方法，根据市id，查询下面的子节点
        infoSet.findChildData(this.hospital.cityCode)
        .then(response =>{
            this.districtList = response.data 
            // console.log(this.districtList)        

        })
    },
    // 获取医院等级
    getHospLevel(){
        // 初始化值
        this.levelList = []
        // 调用方法，根据省id，查询下面的子节点
        infoSet.findByDictCode('Hostype')
        .then(response =>{
            this.levelList = response.data 
            // console.log(this.levelList)        

        })
    },
      
  }
}
</script>
  