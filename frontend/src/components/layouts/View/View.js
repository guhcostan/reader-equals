import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Aside, Breadcrumb, Container, Footer, Header, Button, Main, Menu } from 'cria-ui-react';

import './View.sass';
import logo from '../../../assets/logo_equals.png';
import UsuarioService from '../../../services/UsuarioService';

export default class View extends Component {

    logout() {
        UsuarioService.logout().then( () =>
            {
                this.props.history.push('/');
            }
        )
    }
    static minorThan(width) {
        return width >= window.innerWidth;
    }

    render() {
        const { children, title, breadcrumb, menu } = this.props;
        return (
            <div id="view">
                <Container>
                    <Header height="70px">
                        <div className="cd-header--content">
                            <img src={logo} alt="Logo Equals"/>
                            <div id="user">
                                <Button onClick={this.logout.bind(this)}>Sair</Button>
                            </div>
                        </div>
                    </Header>
                    <Container>
                        <Aside width="fit-content">
                            {
                                menu && Array.isArray(menu) && menu.length > 0
                                && <Menu
                                    collapsed={View.minorThan(1200)}
                                    titleDisabled={View.minorThan(768)}
                                    defaultActive={menu.findIndex(itemMenu => itemMenu.active ===
                                                                              true).toString()}>
                                    {
                                        menu.map(itemMenu => {
                                            if (Array.isArray(itemMenu)) {
                                                return (
                                                    <Menu.SubMenu>
                                                        {
                                                            itemMenu.length > 0 &&
                                                            itemMenu.map(item => (
                                                                <Menu.Item
                                                                    key={itemMenu.indexOf(item)
                                                                                 .toString()}
                                                                    index={itemMenu.indexOf(item)
                                                                                   .toString()}
                                                                    onClick={() => console.log("click")}
                                                                >
                                                                    <span>{item.title}</span>
                                                                </Menu.Item>
                                                            ))
                                                        }
                                                    </Menu.SubMenu>
                                                )
                                            } else {
                                                return (
                                                    <Menu.Item
                                                        key={menu.indexOf(itemMenu).toString()}
                                                        index={menu.indexOf(itemMenu).toString()}
                                                    >
                                                        <div onClick={itemMenu.onClick.bind()}>
                                                        <i className={itemMenu.icon}/>
                                                        <span>{itemMenu.title}</span>
                                                        </div>
                                                    </Menu.Item>
                                                )
                                            }
                                        })
                                    }
                                </Menu>
                            }
                        </Aside>
                        <Container direction="vertical">
                            <div className="subheader">
                                <div className="subheader--content">
                                    {
                                        Array.isArray(breadcrumb) && breadcrumb.length > 0
                                        && <Breadcrumb>
                                            {
                                                breadcrumb.map(item => (
                                                    <Breadcrumb.Item key={breadcrumb.indexOf(item)}>
                                                        <a href={item.route}>{item.name}</a>
                                                    </Breadcrumb.Item>
                                                ))
                                            }
                                        </Breadcrumb>
                                    }
                                    <h2>{title}</h2>
                                </div>
                            </div>
                            <Main>
                                <div className="cd-main--content">
                                    {
                                        children
                                    }
                                </div>
                                <Footer>© {new Date().getFullYear()} - Reader Equals</Footer>
                            </Main>
                        </Container>
                    </Container>
                </Container>
            </div>
        );
    }
}

View.propTypes = {
    menu: PropTypes.array,
    title: PropTypes.string,
    breadcrumb: PropTypes.array,
    history: PropTypes.object
};
