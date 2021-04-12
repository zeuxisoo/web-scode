import { setContext, getContext } from 'svelte';
import defaultStore from '../store/default';

const name = "defaultContext";

function createDefaultContext() {
    setContext(name, defaultStore);
}

function useDefaultContext() {
    return getContext(name);
}

export {
    createDefaultContext,
    useDefaultContext,
}
