<template>
<div id="login">
  <button v-on:click="getData">로그인 부</button>
  <div v-model:value="result"></div>
</div>
</template>
<script>
import axios from 'axios'

export default{
  var result;

  methods: {
    getData: function(){
      axios.get('https://raw.githubusercontent.com/joshua1988/doit-vuejs/master/data/demo.json').then(function(response){
        console.log(response);
        result = response;
      });
    }
  }
}
</script>

<style>

</style>
