import api from './api'

export default {

    async login(username, password) {
        return api.post('/api/auth/login', {
            username,
            password
        });
    },

    async cadastrar(username, password, passwordConfirm) {
        return api.post('/api/auth/register', {
            username,
            password,
            passwordConfirm
        });
    },

    async isLogged() {
        return api.get('/api/auth/isLogged'
        );
    },

    async logout() {
        return api.post('/api/auth/logout'
        );
    }


};
