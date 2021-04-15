import Api from './api';
import UserApi from './user';
import AuthApi from './auth';
import ArticleApi from './article';

const api = new Api();
const userApi = new UserApi();
const authApi = new AuthApi();
const articleApi = new ArticleApi();

export default api;
export {
    userApi,
    authApi,
    articleApi,
}
