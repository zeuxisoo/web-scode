import axios from 'axios';
import { isString } from 'lodash';

const baseApiURL = "/api";

class Api {

    constructor() {
        this.baseURL = baseApiURL;

        this.client = axios.create({
            baseURL: baseApiURL,
            timeout: 3000,
        });
    }

    fetch(method, uri, data) {
        const token = window.localStorage.getItem('_token');

        delete this.client.defaults.headers['Authorization'];

        if (isString(token)) {
            this.client.defaults.headers['Authorization'] = `Bearer ${token}`;
        }

        return this.client[method](uri, data);
    }

    get(uri, query) {
        return this.fetch('get', uri, query);
    }

    post(uri, data) {
        return this.fetch('post', uri, data);
    }

}

class UserApi extends Api {

    create(data) {
        return this.post("/user/create", data);
    }

}

const api = new Api();
const userApi = new UserApi();

export default api;
export {
    userApi,
}
