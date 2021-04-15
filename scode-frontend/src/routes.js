import Home from './views/Home.svelte';
import Register from './views/Register.svelte';
import Login from './views/Login.svelte';
import ArticleCreate from './views/article/Create.svelte';
import NotFound from './views/NotFound.svelte';

const routes = {
    '/'              : Home,
    '/register'      : Register,
    '/login'         : Login,
    '/article/create': ArticleCreate,
    '*'              : NotFound,
};

export default routes;
