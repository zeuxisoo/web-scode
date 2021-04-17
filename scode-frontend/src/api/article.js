import Api from './api';

class AuthApi extends Api {

    create(data) {
        return this.post("/article/create", data);
    }

    list(params) {
        return this.get("/article/list", params);
    }

    show(id) {
        return this.get(`/article/show/${id}`);
    }

    update(id, data) {
        return this.post(`/article/update/${id}`, data);
    }

}

export default AuthApi;
