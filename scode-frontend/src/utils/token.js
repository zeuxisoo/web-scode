import storage from './storage';

class Token {
    constructor(token, username, expiredAt) {
        this.token     = token;
        this.username  = username;
        this.expiredAt = expiredAt;
    }
}

const token = {

    tokenToken    : '_token',
    tokenUsername : '_username',
    tokenExpiredAt: '_expired_at',

    read() {
        return new Token(
            storage.read(this.tokenToken),
            storage.read(this.tokenUsername),
            storage.read(this.tokenExpiredAt),
        );
    },

    write(authToken) {
        storage.write(this.tokenToken, authToken.token);
        storage.write(this.tokenUsername, authToken.username);
        storage.write(this.tokenExpiredAt, authToken.expired_at);
    },

    remove() {
        storage.remove(this.tokenToken);
        storage.remove(this.tokenUsername);
        storage.remove(this.tokenExpiredAt);
    }

}

export default token;
