import { push } from 'svelte-spa-router';
import { wrap } from 'svelte-spa-router/wrap';
import Home from './views/Home.svelte';
import Register from './views/Register.svelte';
import Login from './views/Login.svelte';
import ArticleCreate from './views/article/Create.svelte';
import NotFound from './views/NotFound.svelte';
import gate from './utils/gate';

function createRoutes() {
    const ArticleCreateWrap = wrap({
        component : ArticleCreate,
        conditions: [
            async () => {
                const user = await gate.fetchUserProfile();

                if (user === null) {
                    push('/');

                    return false;
                }

                return true;
            }
        ]
    });

    const routes = {
        '/'              : Home,
        '/register'      : Register,
        '/login'         : Login,
        '/article'       : ArticleCreateWrap,
        '/article/*'     : ArticleCreateWrap,
        '/article/create': ArticleCreateWrap,
        '*'              : NotFound,
    };

    return routes;
}

export {
    createRoutes,
}
