import Api from './api';

class AuthApi extends Api {

    create(data) {
        return this.post("/article/create", data);
    }

    list(query) {
        return this.get("/article/list", query);
    }

}

export default AuthApi;
