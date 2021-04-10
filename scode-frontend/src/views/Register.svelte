<script>
import { trim } from 'ramda';
import Validator from '../utils/validator';

let data = {
    username: { value: "", ref: null },
    email   : { value: "", ref: null },
    password: { value: "", ref: null },
}

function signUp() {
    const rules = {
        'username': 'isEmpty|isLengthSmallerThan:4',
        'email'   : 'isEmpty|isNotEmail',
        'password': 'isEmpty|isLengthSmallerThan:8',
    };

    if (Validator.passes(data, rules)) {
        console.log(data);
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
                    <button type="submit" class="btn btn-secondary" on:click={signUp}>Sign up</button>
                </div>
            </div>
        </div>
    </div>
</div>
