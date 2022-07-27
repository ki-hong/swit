<template>
<div id = "user">
  <router-link to="/login"><button id="login-button">로그인</button></router-link>
</div>
</template>

<script>
export default {
  name: 'user',
}
</script>

<style>
#user {
  background-color: royalblue;
  color: black;
  padding: 20px;
  margin: 0 5px;
  height: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
}

#login-button {
  margin: 0 auto;
  width: 120px;
  height: 50px;
  border: 0px;
  border-radius: 8px;
}

#login-button:hover {
  background-color: gold;
}

</style>
