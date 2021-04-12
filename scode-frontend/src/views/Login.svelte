<script>
import { push } from 'svelte-spa-router';
import { trimData } from '../utils';
import validator from '../utils/validator';
import { authApi } from '../api';
import notifier from '../utils/notifier';

let data = {
    username: { value: "", ref: null },
    password: { value: "", ref: null },
}

const handleSignIn = async () => {
    data = trimData(data);

    const rules = {
        'username': 'isNotEmpty',
        'password': 'isNotEmpty',
    }

    if (validator.passes(data, rules)) {
        try {
            const response = await authApi.signIn({
                username: data.username.value,
                password: data.password.value,
            });

            const body = response.data;

            if (!body.ok) {
                notifier.error(body.message);
                return;
            }else{
                notifier.ok("Login success, Welcome back!");
                push("/");
            }
        }catch(e) {
            notifier.warn('Unknown Error when login user');

            console.log(e);
        }
    }
}
</script>

<div id="login">
    <div class="card">
        <div class="card-body">
            <div class="row g-3">
                <div class="col-12">
                    <label for="username" class="form-label">Username</label>
                    <!-- svelte-ignore a11y-autofocus -->
                    <input type="text" class="form-control" id="username" placeholder="your username" autofocus bind:value={data.username.value} bind:this={data.username.ref}>
                </div>
                <div class="col-12">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="your password" bind:value={data.password.value} bind:this={data.password.ref}>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary" on:click={handleSignIn}>Sign in</button>
                </div>
            </div>
        </div>
    </div>
</div>
