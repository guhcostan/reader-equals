import api from './api'

export default {

    async contarCards() {
        return api.get('/api/contarCards');
    },

};
