import token from './token';

const gate = {

    activate(authToken, defaultContext) {
        token.write(authToken);

        defaultContext.update(state => {
            return {
                ...state,
                isAuthenticated: true,
            }
        });
    },

    deactivate(defaultContext) {
        token.remove();

        defaultContext.update(state => {
            return {
                ...state,
                isAuthenticated: false,
            }
        })
    }

}

export default gate;
