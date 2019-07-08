import React, { Component } from 'react';
import { Card, Layout } from 'cria-ui-react';
import { withRouter } from 'react-router-dom';
import { Notification } from 'element-react';
import Chart from 'react-apexcharts';
import './Dashboard.sass';
import View from '../../layouts/View';
import CardService from '../../../services/CardService';

class Dashboard extends Component {

    breadcrumb = [
        { route: '/', name: <span className="mdi mdi-home"/> },
        { route: '/dashboard', name: 'Dashboard' }
    ];

    menu = [
        {
            icon: 'cd-icon-menu mdi mdi-view-dashboard', title: 'Dashboard', active: true,
            onClick: () => {
                this.props.history.push('/dashboard');
            }
        },
        {
            icon: 'cd-icon-menu mdi mdi-upload', title: 'Upload Arquivos', active: false,
            onClick: () => {
                this.props.history.push('/dashboard/upload');
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

    constructor(props) {
        super(props);

        this.state = {
            grafico1: {
                options: {
                    chart: {
                        id: 'basic-bar'
                    },
                    xaxis: {
                        categories: ["Ufla_Card", "Fgammon_Card"]
                    }
                },
                series: [
                    {
                        name: 'nº de cards',
                        data: []
                    }
                ]
            }
        };
    }

    async componentDidMount() {

        const { grafico1 } = this.state;

        var r;

        CardService.contarCards().then((response) => {
            r = response.data;

            grafico1.options.xaxis.categories = r.map(card => card.tipoCard);
            grafico1.series[0].data = r.map(card => card.contador);

            this.setState(
                {
                    grafico1
                }
            )

        })

    }

    render() {
        return (
            <View menu={this.menu} history={this.props.history} title="Dados Gerais"
                  breadcrumb={this.breadcrumb}>
                <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                    <Layout.Col span={12}>
                        <Card>
                            <h4>Numero de cartões</h4>
                            <Chart
                                options={this.state.grafico1.options}
                                series={this.state.grafico1.series}
                                type="bar"
                            />
                        </Card>
                    </Layout.Col>
                    <Layout.Col span={12}>
                        <Card>
                            <h4>Numero de cartões</h4>
                            <Chart
                                ref="grafico1"
                                options={this.state.grafico1.options}
                                series={this.state.grafico1.series}
                                type="bar"
                            />
                        </Card>
                    </Layout.Col>
                </Layout.Row>
                <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                    <Layout.Col span={12}>
                        <Card>
                            <h4>Numero de cartões</h4>
                            <Chart
                                options={this.state.grafico1.options}
                                series={this.state.grafico1.series}
                                type="bar"
                            />
                        </Card>
                    </Layout.Col>
                    <Layout.Col span={12}>
                        <Card>
                            <h4>Numero de cartões</h4>
                            <Chart
                                options={this.state.grafico1.options}
                                series={this.state.grafico1.series}
                                type="bar"
                            />
                        </Card>
                    </Layout.Col>
                </Layout.Row>
            </View>
        )
    }
}

export default withRouter(Dashboard);
