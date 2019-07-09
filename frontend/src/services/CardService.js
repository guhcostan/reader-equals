import api from './api'

export default {

    async contarCards() {
        return api.get('/contarCards');
    },

};
