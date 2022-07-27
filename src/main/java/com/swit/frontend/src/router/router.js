import Vue from "vue";
import VueRouter from "vue-router";

import LoginForm from '../components/login/LoginForm.vue'
import HomePage from '../HomePage.vue'
import ErrorPage from '../ErrorPage.vue'


Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes : [ // path별 component를 추가한다.
        { path : "/", component : HomePage },
        {
            path : "/login",
            name : "login",
            component : LoginForm
        },
        {
            path : "/:pathMatch(.*)",
            name : "not-found",
            component : ErrorPage
        }
    ]
});

export default router;