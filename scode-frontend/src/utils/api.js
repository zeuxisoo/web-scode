import axios from 'axios';

const baseURL = "/api";

const agent = axios.create({
    baseURL: baseURL,
    timeout: 3000,
});

const api = {

    get: (uri, params) => agent.get(uri, params),
    post: (uri, data) => agent.post(uri, data),

}

const userApi = {

    create: (data) => api.post('/user/create', data),

}

export default api;
export {
    agent,
    userApi,
}
