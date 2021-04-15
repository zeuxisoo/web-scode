import { replace } from 'svelte-spa-router';
import { wrap } from 'svelte-spa-router/wrap';
import { useDefaultContext } from './context/default';
import Home from './views/Home.svelte';
import Register from './views/Register.svelte';
import Login from './views/Login.svelte';
import ArticleCreate from './views/article/Create.svelte';
import NotFound from './views/NotFound.svelte';

function createRoutes() {
    const defaultContext = useDefaultContext();

    const ArticleCreateWrap = wrap({
        component: ArticleCreate,
        conditions: [
            detail => {
                return new Promise((resolve, reject) => {
                    defaultContext.subscribe(state => {
                        const isAuthenticated = state.isAuthenticated;

                        if (isAuthenticated) {
                            resolve(true);
                        }else{
                            replace('/');
                            reject(false);
                        }
                    });
                });
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
