import axios from 'axios';

class Agent {

    constructor(baseApiURL = "/api") {
        this.baseURL = baseApiURL;

        this.client = axios.create({
            baseURL: baseApiURL,
            timeout: 3000,
        });
    }

    appendBaseURL(url) {
        this.client.defaults.baseURL += url;
    }

    get(uri, data) {
        return this.client.get(uri, data);
    }

    post(uri, data) {
        return this.client.post(uri, data);
    }

}

class UserAgent extends Agent {

    constructor() {
        super();

        this.appendBaseURL("/user");
    }

}

const agent = new Agent();
const userAgent = new UserAgent();

export default agent;
export {
    userAgent,
}
