<template>
    <div class="app-container">
        <!-- 导入、导出按钮 -->
        <el-button type="primary" round size="small" style="margin-bottom: 5px ;" @click="importData">导入</el-button>  
        <a href="http://localhost:8202/admin/cmn/dict/exportData" target="_blank">
            <el-button type="success" round size="small" style="margin-bottom: 5px ;">导出</el-button>  
        </a>

        <!-- 导入弹框 -->
        <el-dialog title="导入数据" :visible.sync="dialogImportVisible" width="480px">
            <el-form label-position="right" label-width="170px">
            <el-form-item label="文件" style="margin: 0px; font-size: 18px;">
            <el-upload
            :multiple="false"
            :on-success="onUploadSuccess"
            :action="'http://localhost:8202/admin/cmn/dict/importData'"
            class="upload-demo">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip" style="font-size: 15px;">请上传xls文件，且不超过500kb</div>
            </el-upload>
            </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
            <el-button @click="dialogImportVisible = false">
                取消
            </el-button>
            </div>
        </el-dialog>


        <!-- 数据展示列表 -->
        <el-table
            :data="list"
            style="width: 100%"
            row-key="id"
            border
            center
            lazy
            :load="getChildren"
            :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column label="id" width="200" prop="id" align="center"></el-table-column>
            <el-table-column label="名称" width="200"  prop="name" align="center"></el-table-column>
            <el-table-column label="编码" prop="dictCode" width="200" align="center"></el-table-column>
            <el-table-column label="值" width="200" prop="value" align="center"></el-table-column>
            <el-table-column label="创建时间" prop="createTime" align="center"></el-table-column>
        </el-table>

    
    </div>
</template>


<script>
import dict from '@/api/dict'

export default {
  // 送一变量和初始值
  data() {
    return {
      list : [],
      dialogImportVisible: false
    }
  },
  created() {
    this.getDictData(1)
  },
  methods: {
    getDictData(id){
        dict.getDictList(id)
        .then(response => {
            this.list = response.data
            console.log(this.list)
        })
        .catch(error =>{
            console.log(error)
        })
    },
    getChildren(tree, treeNode, resolve) {
        dict.getDictList(tree.id).then(response => {
            resolve(response.data)
        })
    },
    // 文件上传成功
    onUploadSuccess(){
        this.dialogImportVisible = false;
        //提示 
        this.$message({
            type: 'success',
            message: '导入成功!'
            });
        this.getDictData(1)
    },
    // 导入数据
    importData(){
        this.dialogImportVisible = true;
    }


  },
}
</script>
