import axios from 'axios';

const baseApiURL = "/api";

const client = axios.create({
    baseURL: baseApiURL,
    timeout: 3000,
});

const doFetch = (method, uri, data) => {
    return client[method](uri, data);
}

const agent = {};

['get', 'post'].forEach(method => {
    agent[method] = (uri, data) => doFetch(method, uri, data);
});

export default agent;
export {
    client,
    doFetch,
}
