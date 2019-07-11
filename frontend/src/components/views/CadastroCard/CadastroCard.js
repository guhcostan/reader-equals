import React, { Component } from 'react';
import { Button, Card, Input, Layout } from 'cria-ui-react';
import { withRouter } from 'react-router-dom';
import { Form, Select } from 'element-react';
import './CadastroCard.sass';
import View from '../../layouts/View';
import CardService from '../../../services/CardService';
import notification from '../../../helpers/notification';

class CadastroCard extends Component {

    breadcrumb = [
        { route: '/', name: <span className="mdi mdi-home"/> },
        { route: '/dashboard', name: 'UploadArquivo' }
    ];

    menu = [
        {
            icon: 'cd-icon-menu mdi mdi-view-dashboard', title: 'Dashboard', active: false,
            onClick: () => {
                this.props.history.push('/dashboard');
            }
        },
        {
            icon: 'cd-icon-menu mdi mdi-upload', title: 'Upload Arquivos', active: false,
            onClick: () => {
                this.props.history.push('/dashboard/upload');
            }
        },
        {
            icon: 'cd-icon-menu mdi mdi-page-previous', title: 'Cadastro card', active: true,
            onClick: () => {
                this.props.history.push('/dashboard/cadastro');
            }
        }
    ];

    cadastrar() {
        const { form } = this.state;
        CardService.cadastraCard(form).then(response => {
            notification.emitirMensagemSucesso('Card cadastrado com sucesso');
        }).catch(response => {
            notification.emitirMensagemErro('Erro ao salvar Card')
        })
    }

    onChange(key, value) {
        this.setState(
            {
                form: Object.assign(this.state.form, { [key]: value })
            }
        );
    }

    constructor(props) {
        super(props);

        this.state = {
            form: {
                tipoRegistro: '',
                estabelecimento: '',
                empresaAdiquirente: '',
                pathArquivo:''
            }
        }
    }

    render() {
        return (
            <View menu={this.menu} history={this.props.history} title="Cadastro card"
                  breadcrumb={this.breadcrumb}>
                <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                    <Layout.Col className="formulario-cadastro" span={20}>
                        <Card>
                            <Form ref="form" labelPosition="right" labelWidth={200}
                                  model={this.state.form}>
                                <Form.Item label="Tipo de Registro" prop="tipoRegistro">
                                    <Select value={this.state.form.tipoRegistro} placeholder="Tipo de Registro" onChange={this.onChange.bind(this, 'tipoRegistro')}>
                                        <Select.Option label="UflaCard" value="UFLA_CARD"></Select.Option>
                                        <Select.Option label="Fgammon_Card" value="FGAMMON_CARD"></Select.Option>
                                    </Select>
                                </Form.Item>
                                <Form.Item label="Codigo do Estabelicimento" prop="estabelecimento">
                                    <Input onChange={this.onChange.bind(this, 'estabelecimento')}
                                           value={this.state.form.estabelecimento}>

                                    </Input>
                                </Form.Item>
                                <Form.Item label="Empresa adquirente" prop="empresaAdiquirente">
                                    <Input onChange={this.onChange.bind(this, 'empresaAdiquirente')}
                                           value={this.state.form.empresaAdiquirente}>

                                    </Input>
                                </Form.Item>

                                <Form.Item label="Nome arquivo esperado" prop="pathArquivo">
                                    <Input onChange={this.onChange.bind(this, 'pathArquivo')}
                                           value={this.state.form.pathArquivo}>

                                    </Input>
                                </Form.Item>
                                <Form.Item>
                                    <Button type="primary"
                                            onClick={this.cadastrar.bind(this)}>Cadastrar</Button>
                                </Form.Item>
                            </Form>
                        </Card>

                    </Layout.Col>
                </Layout.Row>
            </View>
        )
    }
}

export default withRouter(CadastroCard);
