import { push } from 'svelte-spa-router';
import { wrap } from 'svelte-spa-router/wrap';
import { useDefaultContext } from './context/default';
import Home from './views/Home.svelte';
import Register from './views/Register.svelte';
import Login from './views/Login.svelte';
import ArticleCreate from './views/article/Create.svelte';
import NotFound from './views/NotFound.svelte';

function createRoutes() {
    const defaultContext = useDefaultContext();

    let isAuthenticated = false;

    defaultContext.subscribe(state => {
        isAuthenticated = state.isAuthenticated;
        console.log(`changed: ${isAuthenticated}`);
    })

    const ArticleCreateWrap = wrap({
        component: ArticleCreate,
        conditions: [
            detail => {
                if (!isAuthenticated) {
                    push('/');
                }

                return isAuthenticated;
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
