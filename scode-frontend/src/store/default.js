import { writable } from 'svelte/store';

function createDefaultStore() {
    const { subscribe, set, update } = writable({
        isAuthenticated: false,
    });

    return {
        subscribe, update, set,

        isSignedIn: () => update(state => ({
            ...state,
            isAuthenticated: true,
        })),

        isSignedOut: () => update(state => ({
            ...state,
            isAuthenticated: false,
        })),
    };
}

const defaultStore = createDefaultStore();

export default defaultStore;
