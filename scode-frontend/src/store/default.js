import { writable } from 'svelte/store';

function createDefaultStore() {
    const store = writable({
        isAuthenticated: false,
    });

    return store;
}

const defaultStore = createDefaultStore();

export default defaultStore;
