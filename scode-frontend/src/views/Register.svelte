<script>
import { trim } from 'ramda';
import { checkIsEmpty, checkIsEmail, checkIsLengthBigger, checkIsValidate } from '../utils';

let username = { value: "", ref: null };
let email    = { value: "", ref: null };
let password = { value: "", ref: null };

function signUp() {
    username.value = trim(username.value);
    email.value    = trim(email.value);
    password.value = trim(password.value);

    const fields = [
        {
            control: username,
            rules  : [
                { name: checkIsEmpty, args: [], message: "Please enter username" },
                { name: checkIsLengthBigger, args: [4], message: "Username must more than 4 letters" },
            ],
        },
        {
            control: email,
            rules : [
                { name: checkIsEmpty, args: [], message: "Please enter email" },
                { name: checkIsEmail, args: [], message: "Invalid email format" }
            ],
        },
        {
            control: password,
            rules : [
                { name: checkIsEmpty, args: [], message: "Please enter password" },
                { name: checkIsLengthBigger, args: [8], message: "Password must more than 8 letters" },
            ],
        },
    ];

    if (checkIsValidate(fields)) {
        console.log(username.value, email.value, password.value);
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
                    <input type="text" class="form-control" id="username" placeholder="your username" autofocus bind:value={username.value} bind:this={username.ref}>
                </div>
                <div class="col-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="username@example.com" bind:value={email.value} bind:this={email.ref}>
                </div>
                <div class="col-12">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="your password" bind:value={password.value} bind:this={password.ref}>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-secondary" on:click={signUp}>Sign up</button>
                </div>
            </div>
        </div>
    </div>
</div>
