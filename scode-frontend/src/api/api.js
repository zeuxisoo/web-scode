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

        // Clean the client header authorization first to ensure the token is not cached
        delete this.client.defaults.headers['Authorization'];

        if (isString(token)) {
            this.client.defaults.headers['Authorization'] = `Bearer ${token}`;
        }

        return this.client[method](uri, data);
    }

    get(uri, params) {
        return this.fetch('get', uri, { params });
    }

    post(uri, data) {
        return this.fetch('post', uri, data);
    }

}

export default Api;
