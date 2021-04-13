import Api from './api';

class UserApi extends Api {

    create(data) {
        return this.post("/user/create", data);
    }

    profile() {
        return this.get("/user/profile");
    }

}

export default UserApi;
