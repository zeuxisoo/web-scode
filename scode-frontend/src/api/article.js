import Api from './api';

class AuthApi extends Api {

    create(data) {
        return this.post("/article/create", data);
    }

    list(params) {
        return this.get("/article/list", params);
    }

}

export default AuthApi;
