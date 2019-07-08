import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Login from './components/views/Login';
import Dashboard from './components/views/Dashboard';
import UploadArquivo from './components/views/UploadArquivo/UploadArquivo';
import Registro from './components/views/Registro/Registro';

const Router = () => (
    <BrowserRouter>
        <Switch>
            <Route path="/" exact={true}    component={Login}/>
            <Route path="/cadastrar" exact={true}  component={Registro}/>
            <Route path="/dashboard" exact={true} component={Dashboard}/>
            <Route path="/dashboard/upload" component={UploadArquivo}/>
        </Switch>
    </BrowserRouter>
);

export default Router;
