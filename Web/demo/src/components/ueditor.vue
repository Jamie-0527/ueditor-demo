<template>
  <div>
    <script :id="editorId" type="text/plain"></script>
  </div>
</template>
<script>
import '../../static/ueditor/ueditor.config.js'
import '../../static/ueditor/ueditor.all.js'
import '../../static/ueditor/ueditor.parse.js'
import '../../static/ueditor/lang/zh-cn/zh-cn.js'
import '../../static/ueditor/themes/default/css/ueditor.css'
export default {
  name: 'UEditor',
  props: {
    /* 编辑器Id */
    editorId: {
      type: String,
      default: '',
    },
    /* 编辑器的内容 */
    value: {
      type: String,
      default: "",
    },
    /* 高度 */
    height: {
      type: Number,
      default: 300,
    }
  },
  data () {
    return {
      editor: null,
      config: {
        autoHeightEnabled: false,
        initialFrameHeight: this.height,
        initialFrameWidth: '100%',
        UEDITOR_HOME_URL: '/static/ueditor/',
        toolbars: [
          [
            'source', //源代码
            'undo', //撤销
            'redo', //重做
            'bold', //加粗
            'italic', //斜体
            'underline', //下划线
            'strikethrough', //删除线
            'subscript', //下标
            'superscript', //上标
            'fontborder', //字符边框
            'blockquote', //引用
            'pasteplain', //纯文本粘贴模式
            'preview', //预览
            'horizontal', //分隔线
            'removeformat', //清除格式
            'time', //时间
            'date', //日期
            'cleardoc', //清空文档
            'insertcode', //代码语言
            'fontfamily', //字体
            'fontsize', //字号
            'paragraph', //段落格式
            'simpleupload',
            'insertimage', //多图上传
            'edittd', //单元格属性
            'inserttable', //插入表格
            'deletetable', //删除表格
            'link', //超链接
            'emotion', //表情
            'spechars', //特殊字符
            'searchreplace', //查询替换
            'justifyleft', //居左对齐
            'justifyright', //居右对齐
            'justifycenter', //居中对齐
            'justifyjustify', //两端对齐
            'forecolor', //字体颜色
            'backcolor', //背景色
            'insertorderedlist', //有序列表
            'insertunorderedlist', //无序列表
            'fullscreen', //全屏
            'imagenone', //默认
            'imageleft', //左浮动
            'imageright', //右浮动
            'attachment', //附件
            'imagecenter', //居中
            'wordimage', //图片转存
            'lineheight', //行间距
            'autotypeset', //自动排版
            'touppercase', //字母大写
            'tolowercase', //字母小写
          ]
        ]
      }
    }
  },
  mounted () {
    /**
     * 初始化编辑器，并设置值
     */
    this.editor = UE.getEditor(this.editorId, this.config);
    this.editor.addListener('ready',  () => {
      this.editor.setContent(this.value)
    })
    /**
     * 监听编辑器，当编辑器中输入内容时与父组件进行同步
     */
    this.editor.addListener("contentChange",  () => {
      this.$emit('input',this.editor.getContent())
    })
  },
  methods: {
    getUEContent () {
      return this.editor.getContent()
    },
    setUEContent (value) {
      return this.editor.setContent(value)
    }
  },
  destroyed () {
    this.editor.destroy()
  }
}
</script>
