import axios from 'axios';

const baseURL = "/api";

const client = axios.create({
    baseURL: baseURL,
    timeout: 3000,
});

const agent = {

    create(uri) {
        client.defaults.baseURL += uri;

        return this;
    },

    get(uri, params) {
        return client.get(uri, params);
    },

    post(uri, data) {
        return client.post(uri, data);
    }

}

const userAgent = agent.create('/user');

export default agent;
export {
    userAgent,
}
