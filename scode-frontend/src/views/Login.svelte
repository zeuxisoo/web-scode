<script>
import { trimData } from '../utils';
import validator from '../utils/validator';

let data = {
    username: { value: "", ref: null },
    password: { value: "", ref: null },
}

function handleSignIn() {
    data = trimData(data);

    const rules = {
        'username': 'isNotEmpty',
        'password': 'isNotEmpty',
    }

    if (validator.passes(data, rules)) {
        console.log(data);
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
