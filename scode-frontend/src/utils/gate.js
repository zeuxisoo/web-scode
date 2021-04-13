import { isString, isEmpty, isNull } from 'lodash';
import token from './token';
import { userApi } from '../api';
import notifier from '../utils/notifier';

const gate = {

    async activate(authToken, defaultContext) {
        token.write(authToken);

        return await this.createUserContext(defaultContext);
    },

    deactivate(defaultContext) {
        token.remove();

        defaultContext.isSignedOut();
        defaultContext.setUser(null);
    },

    async reactivate(defaultContext) {
        const authToken = token.read();

        if (!isString(authToken.token) || isEmpty(authToken.token)) {
            return;
        }

        if (new Date().getTime() >= authToken.expiredAt) {
            return;
        }

        return await this.createUserContext(defaultContext);
    },

    async createUserContext(defaultContext) {
        const user = await this.fetchUserProfile();

        if (!isNull(user)) {
            defaultContext.isSignedIn();
            defaultContext.setUser(user);

            return true;
        }

        this.deactivate(defaultContext);

        return false;
    },

    async fetchUserProfile() {
        try {
            const response = await userApi.profile();
            const body = response.data;

            if (body.ok) {
                return body.data;
            }else{
                return null;
            }
        }catch(e) {
            notifier.error("Authentication failed, Please login again");

            return null;
        }
    },

}

export default gate;
