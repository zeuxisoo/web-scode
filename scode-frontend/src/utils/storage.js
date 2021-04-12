const storage = {

    driver: window.localStorage,

    write(key, value) {
        this.driver.setItem(key, value);
    },

    read(key) {
        return this.driver.getItem(key);
    },

    remove(key) {
        return this.driver.removeItem(key);
    }

}

export default storage;
