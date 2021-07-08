<template>

</template>
<script>
export default {
  name: "BWebsocket",
  created() { //组件创建时同时初始化websocket对象
    this.websocketObjInit()
  },
  beforeDestroy() { //组件销毁时关闭websocket对象连接
    this.webSocketObject.close()
  },
  methods: {
    //初始化websocket对象
    websocketObjInit() {
      const webSocketUrl = this.url                       //从props中获取url
      this.webSocketObject = new WebSocket(webSocketUrl); //绑定websocket的url
      this.webSocketObject.onopen = this.onOpen           //绑定onopen事件
      this.webSocketObject.onclose = this.onClose         //绑定onclose事件
      this.webSocketObject.onerror = this.onError         //绑定onerror事件
      this.webSocketObject.onmessage = this.onMessage     //绑定onmessage事件
    },
    onOpen(e){
      if (this.dev){
        console.log('与websocket与服务器连接打开->',e)
      }
    },
    onClose(e){
      if (this.dev){
        console.log('与websocket与服务器连接断开->',e)
      }
    },
    onError(e){
      if (this.dev){
        console.log('与websocket与服务器连接异常->',e)
      }
    },
    onMessage(e){
      if (this.dev){
        console.log('来自websocket服务器的消息->',e)
      }
      this.$emit('onReceiveMessage',e.data)
    }
  },
  props: {
    url: {
      type: String
    },
    dev: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      webSocketObject: null,
    }
  },
}
</script>

<style scoped>

</style>
