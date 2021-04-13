import { writable } from 'svelte/store';

function createDefaultStore() {
    const store = writable({
        isAuthenticated: false,
    });

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
    };
}

const defaultStore = createDefaultStore();

export default defaultStore;
