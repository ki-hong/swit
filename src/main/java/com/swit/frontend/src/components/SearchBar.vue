<template>
  <div id="search-bar">
    <form action="">
      <div id="radio-search">
        <input type="radio" id="title" name="search-standard" value="title" v-model="picked">
        <label for="title">Title</label>
        <input type="radio" id="contents" name="search-standard" value="contents" v-model="picked">
        <label for="contents">Contents</label>
        <input type="radio" id="titleNcontents" name="search-standard" value="titleNcontents" v-model="picked">
        <label for="titleNcontents">Title + Contents</label>
        <input type="radio" id="writer" name="search-standard" value="writer" v-model="picked">
        <label for="writer">Writer</label>
      </div>
      <input class="search" type="search"
      placeholder="검색할 내용을 입력해 보세요. 무엇이든지요 :)">
      <input v-on:click="search" class="search-btn" type="button" value="find">
    </form>
  </div>
</template>

<script>

export default{
  name: 'search-bar-component',
  watch: {
    picked() {
      console.log('watch : ', this.picked)
    }
  },
  data() {
    return{
      picked: 'title'
    }
  },
  methods: {
    search: function (event) {
      // alert("찾기");
      alert(this.picked);
    }
  }
}

</script>

<style>
.search{
  font-size: 20px;
  height: 40%;
  width: 60%;
  border-radius: 10px;
  border: 0px;
  background-color: whitesmoke;
  padding: 10px;
}

.search-btn{
  height: 40px;
  width: 70px;
  border: 0px;
  padding: 10px;
  font-size: 20px;
  border-radius: 10px;
}

.search-btn:hover{
  background-color: blue;
}
</style>
