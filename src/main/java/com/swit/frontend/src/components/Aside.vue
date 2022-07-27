<template>
<div id = "aside">
    <user-info></user-info>
    <board-list></board-list>
</div>
</template>

<script>
import BoardListVue from './BoardList.vue'
import UserInfoVue from './UserInfo.vue'

export default {
  name: 'aside-component',
  components: {
    "board-list": BoardListVue,
    "user-info": UserInfoVue
  }
}
</script>

<style>
#aside {
  color: black;
  text-align: center;
  height: auto;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}
</style>
