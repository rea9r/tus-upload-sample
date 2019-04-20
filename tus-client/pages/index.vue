<template>
  <section class="container">
    <div>
      <logo />
      <h1 class="title">tus-client</h1>
      <h2 class="subtitle">Tus.io File Upload Sample</h2>
      <el-upload
        action
        :auto-upload="false"
        :on-change="handleChange"
        :on-remove="handleRemove"
        multiple
        :file-list="fileList"
      >
        <el-button slot="trigger" class="button--green">
          Select Files
        </el-button>
        <el-button class="button--grey" @click="upload">
          Upload
        </el-button>
      </el-upload>
    </div>
  </section>
</template>

<script>
import Logo from '~/components/Logo.vue'

export default {
  components: {
    Logo
  },
  data() {
    return {
      fileList: [],
      params: {
        message: 'this is debug message.'
      }
    }
  },
  methods: {
    handleChange: function(file, fileList) {
      this.fileList = fileList
    },
    handleRemove: function(file, fileList) {
      this.fileList = fileList
    },
    upload: async function() {
      if (this.fileList.length === 0) {
        this.showMessage('File has not been selected', 'warning')
        return
      }
      await Promise.all(
        this.fileList.map(file => {
          return this.$store.dispatch('upload', {
            file: file.raw,
            params: this.params
          })
        })
      )
      this.showMessage('File uploaded successfully', 'success')
      this.fileList = []
    },
    showMessage: function(message, type) {
      this.$message({
        message: message,
        type: type
      })
    }
  }
}
</script>

<style>
.container {
  margin: 0 auto;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
    'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 60px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 30px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}
</style>
