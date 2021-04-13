import { isString, isEmpty, isNull } from 'lodash';
import token from './token';
import { userApi } from '../api';

const gate = {

    async activate(authToken, defaultContext) {
        token.write(authToken);

        defaultContext.isSignedIn();
    },

    deactivate(defaultContext) {
        token.remove();

        defaultContext.isSignedOut();
    },

    async reactivate(defaultContext) {
        const authToken = token.read();

        if (!isString(authToken.token) || isEmpty(authToken.token)) {
            return;
        }

        if (new Date().getTime() >= authToken.expiredAt) {
            return;
        }

        const user = await this.fetchUserProfile();

        if (!isNull(user)) {
            defaultContext.isSignedIn();
            defaultContext.setUser(user);
        }
    },

    async fetchUserProfile() {
        try {
            const response = await userApi.profile();
            const body = response.data;

            if (body.ok) {
                return body.data;
            }else{
                this.deactivate();

                return null;
            }
        }catch(e) {
            this.deactivate();
        }
    }

}

export default gate;
