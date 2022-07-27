<template>
<div id = "board-list">
    <li class="boards"><router-link to="/free">자유</router-link></li>
    <li class="boards"><router-link to="/ano">익명</router-link></li>
    <li class="boards"><router-link to="/find-study">스터디 모집</router-link></li>
    <li class="boards"><router-link to="/qna">질문과 답변</router-link></li>
    <!-- 소속 게시판 -->
    <li class="boards"><router-link to="/codestates">코드스테이츠</router-link></li>
    <li class="boards"><router-link to="/hanghae99">항해99</router-link></li>
    <li class="boards"><router-link to="/project-lion">멋쟁이사자처럼</router-link></li>
    <!-- IT 게시판 -->
    <li class="boards"><router-link to="algorithmn-question">알고리즘 질문</router-link></li>
    <li class="boards"><router-link to="code-review">코드 리뷰</router-link></li>
    <li class="boards"><router-link to="share-IT-info">IT 정보 공유</router-link></li>
    <li class="boards"><router-link to="share-job-info">취준 정보 공유</router-link></li>
</div>
</template>

<script>

export default {
  name: 'board-list',
}
</script>

<style>
#board-list {
  background-color: rgb(170, 231, 79);
  color: black;
  height: 100%;
  text-align: left;
  padding: 20px;
  margin: 0 5px;
}

.boards{
  margin: 10px;
  list-style: none;
}
</style>
