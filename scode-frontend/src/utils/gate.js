import token from './token';

const gate = {

    activate(authToken, defaultContext) {
        token.write(authToken);

        defaultContext.isSignedIn();
    },

    deactivate(defaultContext) {
        token.remove();

        defaultContext.isSignedOut();
    }

}

export default gate;
