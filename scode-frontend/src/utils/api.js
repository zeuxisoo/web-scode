import axios from 'axios';

const baseURL = "/api";

const agent = axios.create({
    baseURL: baseURL,
    timeout: 3000,
});

const api = {

    create(uri) {
        agent.defaults.baseURL += uri;

        return this;
    },

    get(uri, params) {
        return agent.get(uri, params);
    },

    post(uri, data) {
        return agent.post(uri, data);
    }

}

const userApi = api.create('/user');

export default api;
export {
    userApi,
}
