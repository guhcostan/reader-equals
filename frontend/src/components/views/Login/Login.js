import React, { Component } from 'react';
import { Button, Card, Input, Layout } from 'cria-ui-react';
import { withRouter } from 'react-router-dom';
import logo from '../../../assets/logo_equals.png'
import './Login.sass';
import UsuarioService from '../../../services/UsuarioService';
import notification from '../../../helpers/notification';

class Login extends Component {

    constructor(props) {
        super(props);

        this.state = {
            login: null,
            senha: null
        }
    }

    atualizaSenha(senha) {
        this.setState(
            {
                senha
            }
        )
    }

    irParaCadastrar() {
        this.props.history.push('/cadastrar');
    }

    atualizaLogin(login) {
        this.setState(
            {
                login
            }
        )
    }

    login() {
        const { login, senha } = this.state;
        UsuarioService.login(login, senha).then(() => {
            this.props.history.push('/dashboard');
        }).catch((response) => {
            notification.emitirMensagemAlerta('Confira se digitou corretamente os campos.')
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
                                    onKeyDown={event => {
                                        if (event.key === 'Enter') {
                                            this.login()
                                        }
                                    }}
                                    placeholder="Email ou login">
                                </Input>
                            </Layout.Col>
                        </Layout.Row>
                        <Layout.Row>
                            <Layout.Col>
                                <Input placeholder="Senha"
                                       onKeyDown={event => {
                                           if (event.key === 'Enter') {
                                               this.login()
                                           }
                                       }}
                                       onChange={this.atualizaSenha.bind(this)}
                                       suffixIcon="mdi mdi-lock" type="password">
                                </Input>
                            </Layout.Col>
                        </Layout.Row>
                        <Layout.Row>
                            <Layout.Col span={12}>
                                <Button onClick={this.login.bind(this)}>
                                    Login
                                </Button>
                            </Layout.Col>
                            <Layout.Col span={12}>
                                <Button onClick={this.irParaCadastrar.bind(this)}>
                                    Registrar
                                </Button>
                            </Layout.Col>
                        </Layout.Row>
                    </Card>
                </Layout.Col>
            </Layout.Row>
        )
    }
}

export default withRouter(Login);
