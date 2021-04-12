<script>
import Router from 'svelte-spa-router';
import routes from './routes';
import { createDefaultContext, useDefaultContext } from './context/default.js';

// Inject the default context into global application first
createDefaultContext();

// Get the default context
const defaultContext = useDefaultContext();
</script>

<style lang="postcss" global>
@import "../node_modules/bootstrap/dist/css/bootstrap.css";
@import "../node_modules/toastr/build/toastr.css";

body {
    padding-top: 15px;
}

.header, .menu {
    border-bottom: 1px solid #e5e5e5;
}

.nav-scroller {
    position: relative;
    z-index: 2;
    height: 44px;
    overflow-y: hidden;
}
</style>

<div id="app">
    <div class="container">
        <div class="header d-flex justify-content-center">
            <h1>SCode</h1>
        </div>
        <div class="menu py-1 mb-2">
            <div class="nav-scroller">
                <div class="nav d-flex justify-content-between">
                    <a class="p-2 link-secondary" href="#/">Home</a>
                    {#if !$defaultContext.isAuthenticated}
                        <a class="p-2 link-secondary" href="#/register">Register</a>
                        <a class="p-2 link-secondary" href="#/login">Login</a>
                    {/if}
                </div>
            </div>
        </div>
        <Router routes={routes} />
    </div>
</div>
