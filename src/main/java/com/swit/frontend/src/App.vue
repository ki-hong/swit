<template>
  <div id="app">
    <SwitHeader></SwitHeader>
    <SwitTopBar></SwitTopBar>
    <SwitLoginForm></SwitLoginForm>
    <SwitFooter></SwitFooter>
  </div>
</template>

<script>
import SwitHeader from './components/SwitHeader.vue'
import SwitTopBar from './components/SwitTopBar.vue'
import SwitLoginForm from './components/login/SwitLoginForm.vue'
import SwitFooter from './components/SwitFooter.vue'

export default {
  components: {
    'SwitHeader': SwitHeader,
    'SwitTopBar': SwitTopBar,
    'SwitLoginForm': SwitLoginForm,
    'SwitFooter': SwitFooter
  }
}
</script>

<style>

</style>
