import Api from './api';

class AuthApi extends Api {

    create(data) {
        return this.post("/article/create", data);
    }

}

export default AuthApi;
