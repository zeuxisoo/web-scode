import Home from './views/Home.svelte';
import Register from './views/Register.svelte';
import Login from './views/Login.svelte';
import NotFound from './views/NotFound.svelte';

const routes = {
    '/'        : Home,
    '/register': Register,
    '/login'   : Login,
    '*'        : NotFound,
};

export default routes;
