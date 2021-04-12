import Api from './api';

class UserApi extends Api {

    create(data) {
        return this.post("/user/create", data);
    }

}

export default UserApi;
