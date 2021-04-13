import { writable, get } from 'svelte/store';

function createDefaultStore() {
    const state = {
        user: null,
        isAuthenticated: false,
    };

    const store = writable(state);

    return {
        ...store,

        isSignedIn: () => store.update(state => ({
            ...state,
            isAuthenticated: true,
        })),

        isSignedOut: () => store.update(state => ({
            ...state,
            isAuthenticated: false,
        })),

        setUser: user => store.update(state => ({
            ...state,
            user: user,
        })),
    };
}

const defaultStore = createDefaultStore();

export default defaultStore;
