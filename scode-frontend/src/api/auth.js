import Api from './api';

class AuthApi extends Api {

    signIn(data) {
        return this.post("/auth/signin", data);
    }

}

export default AuthApi;
