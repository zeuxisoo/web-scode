import { push } from 'svelte-spa-router';
import { wrap } from 'svelte-spa-router/wrap';
import Home from './views/Home.svelte';
import Register from './views/Register.svelte';
import Login from './views/Login.svelte';
import ArticleCreate from './views/article/Create.svelte';
import NotFound from './views/NotFound.svelte';
import gate from './utils/gate';

const protectedView = viewComponent => (wrap({
    component : viewComponent,
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
}));

const routes = {
    '/'              : Home,
    '/register'      : Register,
    '/login'         : Login,
    '/article'       : protectedView(ArticleCreate),
    '/article/*'     : protectedView(ArticleCreate),
    '/article/create': protectedView(ArticleCreate),
    '*'              : NotFound,
};

export default routes;
