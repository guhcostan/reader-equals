import React, { Component } from 'react';
import { Button, Card, Input, Layout } from 'cria-ui-react';
import { withRouter } from "react-router-dom";
import { Upload, Notification } from 'element-react';
import logo from '../../../assets/logo_equals.png'
import './UploadArquivo.sass';
import View from '../../layouts/View';

class UploadArquivo extends Component {

    breadcrumb = [
        { route: '/', name: <span className="mdi mdi-home"/> },
        { route: '/dashboard', name: 'UploadArquivo'}
    ];

    menu = [
        {
            icon: 'cd-icon-menu mdi mdi-view-dashboard', title: 'Dashboard', active: false,
            onClick: () => {
                this.props.history.push("/dashboard");
            }
        },
        {
            icon: 'cd-icon-menu mdi mdi-upload', title: 'Upload Arquivos', active: true,
            onClick: () => {
                this.props.history.push("/dashboard/upload");
            }
        }
    ];

    emitirMensagemSucesso() {
        Notification({
                         title: 'Success',
                         message: 'Mensagem de sucesso',
                         type: 'success',
                         duration: 0
                     });
    }

    emitirMensagemErro() {
        Notification({
                         title: 'Warning',
                         message: 'Mensagem de advertência',
                         type: 'warning'
                     });
    }

    render() {
        return (
          <View menu={this.menu} history={this.props.history} title="Dados Gerais" breadcrumb={this.breadcrumb}>
            <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                <Layout.Col span={20}>
                    <Upload
                        className="upload"
                        drag
                        multiple
                        showFileList
                        onSuccess={this.emitirMensagemSucesso.bind()}
                        onError={this.emitirMensagemErro.bind()}
                        action="/api/enviarCard"
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
