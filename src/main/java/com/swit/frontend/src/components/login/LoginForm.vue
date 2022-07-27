<template>
  <form v-on:submit.prevent="signIn">
    <div><strong>아이디 </strong><input v-model="id" type="text" placeholder="아이디"></div>
    <div><strong>비밀번호 </strong><input v-model="password" type="password" placeholder="비밀번호"></div>
    <div><input type="submit" id="login" value="로그인"></div>
    <div><strong><router-link to="/find-password">혹시 비밀번호를 잊으셨나요?</router-link></strong></div>
    <div><strong><router-link to="/sign-in">아직 SWIT의 회원이 아니신가요?</router-link></strong></div>
  </form>
</template>
<script>
import axios from 'axios'
const login = "로그인 URL 경로";  //로그인 시 URL 경로 

let id;
let password;

export default{
  data() {
    return {
      id: '',
      password: ''
    };
  },
  methods: {
    signIn() {
      var data = {
        id: this.id,
        password: this.password
      };
      var router = this.$router;
      if(!data.id || !data.password){
        alert("Your id or password is empty.");
      }
      console.log(`아이디는 ${id} 비밀번호는 ${password}`);
      axios
        .post(login, data)
        .then((res) => {
          if(res.status === 200){
            this.result = res.data;
            console.log(this.result);
            this.token = this.result.data.token;
            console.log(`token: ${this.token}`);

            this.$cookie.set("accesstoken", res.data.data.token, 1);

            axios.defaults.headers.common["x-access-token"] = res.data.data.token;
            alert("success!");
            router.push("/");
          }else{
            alert("error occurred");
          }
        })
        .catch((err) =>{
          alert(`[로그인 오류] 오류 코드: ${err}`);
          console.log(`아이디는 ${data.id} 비밀번호는 ${data.password}`);
      });
    },
  },
};
</script>

<style>

</style>
