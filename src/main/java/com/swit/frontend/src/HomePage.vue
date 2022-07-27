<template>
  <div id="wrap">
    <header-component></header-component>
    <wrapped-component></wrapped-component>
    <footer-component></footer-component>
  </div>
</template>

<script>
import Vue from 'vue'
import HeaderComponent from './components/Header.vue'
import FooterComponent from './components/Footer.vue'
import WrappedComponent from './components/wrapped.vue'

export default{
  name: 'HomePage',
  components: {
    HeaderComponent,
    FooterComponent,
    WrappedComponent
  },
  data() {
    return {};
  }
}

</script>

<style>
.wrap{
  background-color: aqua;
}
</style>
