import React, { Component } from 'react';
import { Layout } from 'cria-ui-react';
import { withRouter } from 'react-router-dom';
import { Upload } from 'element-react';
import './UploadArquivo.sass';
import View from '../../layouts/View';
import notification from '../../../helpers/notification';

class UploadArquivo extends Component {

    breadcrumb = [
        { route: '/', name: <span className="mdi mdi-home"/> },
        { route: '/dashboard', name: 'UploadArquivo'}
    ];

    menu = [
        {
            icon: 'cd-icon-menu mdi mdi-view-dashboard', title: 'Dashboard', active: false,
            onClick: () => {
                this.props.history.push('/dashboard');
            }
        },
        {
            icon: 'cd-icon-menu mdi mdi-upload', title: 'Upload Arquivos', active: true,
            onClick: () => {
                this.props.history.push('/dashboard/upload');
            }
        },
        {
            icon: 'cd-icon-menu mdi mdi-page-previous', title: 'Cadastro card', active: false,
            onClick: () => {
                this.props.history.push('/dashboard/cadastro');
            }
        }
    ];


    render() {
        return (
          <View menu={this.menu} history={this.props.history} title="Upload Arquivo" breadcrumb={this.breadcrumb}>
            <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                <Layout.Col span={20}>
                    <Upload
                        className="upload"
                        drag
                        multiple
                        showFileList
                        onSuccess={notification.emitirMensagemSucesso.bind()}
                        onError={notification.emitirMensagemErro.bind()}
                        action="/enviarCard"
                    >
                        <i className="el-icon-upload"/>
                        <div className="el-upload__text">Arraste o arquivo ou<em> click para upload</em></div>
                    </Upload>
                </Layout.Col>
            </Layout.Row>
          </View>
        )
    }
}

export default withRouter(UploadArquivo);
