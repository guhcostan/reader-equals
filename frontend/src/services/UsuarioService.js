import api from './api'

export default {

    async login(username, password) {
        return api.post('/auth/login', {
            username,
            password
        });
    },

    async cadastrar(username, password, passwordConfirm) {
        return api.post('/auth/register', {
            username,
            password,
            passwordConfirm
        });
    },

    async isLogged() {
        return api.get('/auth/isLogged'
        );
    },

    async logout() {
        return api.post('/auth/logout'
        );
    }


};
