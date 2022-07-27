import Vue from 'vue'
import App from './App.vue'
import VueCookie from 'vue-cookie'
import router from "./router/router"
Vue.use(VueCookie)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
