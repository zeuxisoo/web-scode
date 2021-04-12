import Api from './api';
import UserApi from './user';
import AuthApi from './auth';

const api = new Api();
const userApi = new UserApi();
const authApi = new AuthApi();

export default api;
export {
    userApi,
    authApi,
}
