import { Notification } from "element-react";

export default {

    emitirMensagemSucesso(mensagem) {
        Notification({
                         title: 'Sucesso',
                         message: mensagem ? mensagem : 'Mensagem de sucesso',
                         type: 'success',
                         duration: 0
                     });
    },

    emitirMensagemAlerta(mensagem) {
        Notification({
                         title: 'Alerta',
                         message: mensagem ? mensagem : 'Mensagem de advertência',
                         type: 'warning'
                     });
    },

    emitirMensagemErro(mensagem) {
        Notification({
                         title: 'Erro',
                         message: mensagem ? mensagem : 'Mensagem de erro',
                         type: 'error'
                     });
    }
};
