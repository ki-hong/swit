<template>
  <div id = "header">
    <div id = "logo">SW <span id="plus">+</span> IT</div>
    <searchbar-component></searchbar-component>
  </div>
</template>

<script>
import SearchBarComponent from './SearchBar.vue'


export default {
  name: 'header-component',
  components: {
    'searchbar-component': SearchBarComponent
  }
}

</script>

<style>
#header {
  background-color: white;
  color: #062453;
  text-align: center;
  padding: 10px;
}

#logo {
  font-size: 40px;
  font-weight: bold;
  padding: 20px 0px;
}

#plus{
  font-size: 30px;
}
</style>
