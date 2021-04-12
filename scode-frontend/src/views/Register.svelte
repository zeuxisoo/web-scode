<script>
import { push } from 'svelte-spa-router';
import { trimData } from '../utils';
import validator from '../utils/validator';
import { userApi } from '../api';
import notifier from '../utils/notifier';

let data = {
    username: { value: "", ref: null },
    email   : { value: "", ref: null },
    password: { value: "", ref: null },
}

const handleSignUp = async () => {
    data = trimData(data);

    const rules = {
        'username': 'isNotEmpty|isLengthBiggerThan:4',
        'email'   : 'isNotEmpty|isEmail',
        'password': 'isNotEmpty|isLengthBiggerThan:8',
    }

    if (validator.passes(data, rules)) {
        try {
            const response = await userApi.create({
                username: data.username.value,
                email   : data.email.value,
                password: data.password.value,
            });

            const body = response.data;

            if (!body.ok) {
                notifier.error(body.message);
                return;
            }else{
                notifier.ok("Thank you for your registation, You can login now.");
                push("/login");
            }
        }catch(e) {
            notifier.warn('Unknown Error when register user');

            console.log(e);
        }
    }
}
</script>

<div id="register">
    <div class="card">
        <div class="card-body">
            <div class="row g-3">
                <div class="col-12">
                    <label for="username" class="form-label">Username</label>
                    <!-- svelte-ignore a11y-autofocus -->
                    <input type="text" class="form-control" id="username" placeholder="your username" autofocus bind:value={data.username.value} bind:this={data.username.ref}>
                </div>
                <div class="col-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="username@example.com" bind:value={data.email.value} bind:this={data.email.ref}>
                </div>
                <div class="col-12">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="your password" bind:value={data.password.value} bind:this={data.password.ref}>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-secondary" on:click={handleSignUp}>Sign up</button>
                </div>
            </div>
        </div>
    </div>
</div>
