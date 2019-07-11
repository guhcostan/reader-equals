import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Login from './components/views/Login';
import Dashboard from './components/views/Dashboard';
import UploadArquivo from './components/views/UploadArquivo/UploadArquivo';
import CadastroCard from './components/views/CadastroCard/CadastroCard';
import Registro from './components/views/Registro/Registro';
import UsuarioService from './services/UsuarioService';

function requireAuth(nextState, replace, next) {
    console.log("Teste")
    var usuarioLoggado;
    UsuarioService.isLogged().then(response => {
        usuarioLoggado = response.data;
    })

    if (!usuarioLoggado) {
        replace({
                    pathname: "/",
                    state: {nextPathname: nextState.location.pathname}
                });
    }
    next();
}

const Router = () => (
    <BrowserRouter>
        <Switch>
            <Route path="/" exact={true} component={Login}/>
            <Route path="/cadastrar" exact={true} component={Registro}/>
            <Route path="/dashboard" exact={true} component={Dashboard}/>
            <Route path="/dashboard/upload" component={UploadArquivo}/>
            <Route path="/dashboard/cadastro" component={CadastroCard}/>
        </Switch>
    </BrowserRouter>
);

export default Router;
