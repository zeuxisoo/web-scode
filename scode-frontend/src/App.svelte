<script>
import Router from 'svelte-spa-router';
import routes from './routes';
import { createDefaultContext, useDefaultContext } from './context/default.js';
import gate from './utils/gate';
import notifier from './utils/notifier';

// Inject the default context into global application first
createDefaultContext();

// Get the default context
const defaultContext = useDefaultContext();

//
const handleSignOut = () => {
    gate.deactivate(defaultContext);

    notifier.ok('Logout success, See you next time :)');
}
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

.link-logout {
    text-decoration: underline;
    cursor: pointer;
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
                    {:else}
                        <span class="p-2 link-secondary link-logout" on:click={handleSignOut}>Logout</span>
                    {/if}
                </div>
            </div>
        </div>
        <Router routes={routes} />
    </div>
</div>
