<template>
  <div id="app">
    <b-websocket
        dev
        @onReceiveMessage="handleReceiveMessage"
        url="ws://localhost:8080/lottery-server/userWebsocket/wqk">
    </b-websocket>
    <ul>
      <li v-for="item in list" :key="item.id">{{item}}</li>
    </ul>
    <h2>{{totalSize}}</h2>
  </div>
</template>

<script>

import BWebsocket from "@/components/BWebsocket";

import {
  getUserList
} from "@/api/user";

export default {
  created() {
    this.fetchList()
  },
  methods: {
    fetchList() {
      getUserList().then(res=>{
        console.log(res)
        this.list = res.data.items
        this.totalSize = res.data.totalSize
      })
    },
    handleReceiveMessage(e){
      console.log(e)
    }
  },
  name: 'app',
  components: {
    BWebsocket
  },
  data() {
    return {
      totalSize: undefined,
      list:[]
    }
  },
}
</script>

<style>
#app {
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
