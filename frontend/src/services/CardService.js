import api from './api'
import FileDownload from 'js-file-download';

export default {

    async contarCards() {
        return api.get('/contarCards');
    },

    async contabilizarCardsPorData() {
        return api.get('/contabilizarCardsPorData');
    },

    async contabilizarCardsRecepcionados() {
        return api.get('/contabilizarCardsRecepcionados');
    },

    async getCardsPage(pagina) {
        return api.get('/cardsByPage?page=' + pagina);
    },

    async deletaCard(id) {
        return api.delete('/deletarCard?id=' + id);
    },

    async cadastraCard(card) {
        return api.post('/cadastrarCard',
                            card
        )
    },

    baixaArquivoCard(id) {
        api.get('/baixaArquivoCard?id=' + id).then((response) => {
            console.log(response)
            return FileDownload(response.data, response.headers.filename)
        });
    }
};
