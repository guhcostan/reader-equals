import React, { Component } from 'react';
import { Button, Card, Input, Layout } from 'cria-ui-react';
import { Notification } from 'element-react';
import { withRouter } from 'react-router-dom';
import logo from '../../../assets/logo_equals.png'
import './Registro.sass';
import UsuarioService from '../../../services/UsuarioService';

class Registro extends Component {

    constructor(props) {
        super(props);

        this.state = {
            login: null,
            senha: null,
            confirmacaoSenha: null
        }
    }

    sair() {
        this.props.history.push('/');
    }

    atualizaSenha(senha) {
        this.setState(
            {
                senha
            }
        )
    }

    atualizaConfirmacao(confirmacaoSenha) {
        this.setState(
            {
                confirmacaoSenha
            }
        )
    }

    atualizaLogin(login) {
        this.setState(
            {
                login
            }
        )
    }

    cadastrar() {
        const { login, senha, confirmacaoSenha } = this.state;
        UsuarioService.cadastrar(login, senha, confirmacaoSenha).then(() => {
            this.props.history.push('/dashboard');
        }).catch(() => {
            Notification(
                {
                    title: 'Usuario ou senha incorretos.',
                    message: 'Confira se digitou corretamente os campos.',
                    type: 'error'
                }
            );
        })
    }

    render() {
        return (
            <Layout.Row className="view" align="middle" justify="center" type="flex">
                <Layout.Col span={6}>
                    <Card>
                        <img alt="Logo Equals" src={logo}/>
                        <Layout.Row>
                            <Layout.Col>
                                <Input
                                    onChange={this.atualizaLogin.bind(this)}
                                    placeholder="Digite seu login">
                                </Input>
                            </Layout.Col>
                        </Layout.Row>
                        <Layout.Row>
                            <Layout.Col>
                                <Input placeholder="Digite sua senha"
                                       onChange={this.atualizaSenha.bind(this)}
                                       suffixIcon="mdi mdi-lock" type="password">
                                </Input>
                            </Layout.Col>
                        </Layout.Row>
                        <Layout.Row>
                            <Layout.Col>
                                <Input placeholder="Digite sua senha novamente"
                                       onChange={this.atualizaConfirmacao.bind(this)}
                                       suffixIcon="mdi mdi-lock" type="password">
                                </Input>
                            </Layout.Col>
                        </Layout.Row>
                        <Layout.Row>
                            <Layout.Col span={12}>
                                <Button onClick={this.cadastrar.bind(this)}>
                                    Cadastrar
                                </Button>
                            </Layout.Col>
                            <Layout.Col span={12}>
                                <Button onClick={this.sair.bind(this)}>
                                    Sair
                                </Button>
                            </Layout.Col>
                        </Layout.Row>
                    </Card>
                </Layout.Col>
            </Layout.Row>
        )
    }
}

export default withRouter(Registro);
