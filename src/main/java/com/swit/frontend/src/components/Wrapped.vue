<template>
<div id = "wrapped">
    <main-section-component></main-section-component>
    <aside-component></aside-component>
</div>
</template>

<script>
import MainSectionComponent from './MainSection.vue'
import AsideComponent from './Aside.vue'

export default {
  name: 'wrapped',
    components: {
    'main-section-component': MainSectionComponent,
    'aside-component': AsideComponent
  }
}
</script>

<style>

#wrapped{
    display: flex;
}
</style>
