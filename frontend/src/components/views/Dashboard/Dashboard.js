import React, { Component } from 'react';
import { Button, Card, Layout } from 'cria-ui-react';
import { withRouter } from 'react-router-dom';
import { Pagination, Table } from 'element-react';
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
        },
        {
            icon: 'cd-icon-menu mdi mdi-page-previous', title: 'Cadastro card', active: false,
            onClick: () => {
                this.props.history.push('/dashboard/cadastro');
            }
        }
    ];

    buscarTabela(pagina) {
        const { tabela } = this.state;

        this.setState(
            {
                pagina
            }
        )
        CardService.getCardsPage(pagina - 1).then(response => {
            tabela.total = response.data.totalElements;
            tabela.data = response.data.content;
            this.setState(
                {
                    ...tabela
                }
            );
        })

    }

    baixarArquivo({ id }) {
        CardService.baixaArquivoCard(id);
    }

    deletaCard({ id }) {
        CardService.deletaCard(id).then(() => {
            this.buscarTabela(this.state.pagina)
            this.atualizaGraficos()
        });
    }

    constructor(props) {
        super(props);

        var that = this;
        this.state = {
            pagina: 0,
            tabela: {
                total: 0,
                columns: [
                    {
                        label: 'Data de Processamento',
                        prop: 'dataProcessamento'
                    },
                    {
                        label: 'Codigo estabelecimento',
                        prop: 'estabelecimento'
                    },
                    {
                        label: 'Tipo',
                        prop: 'tipoRegistro'
                    },
                    {
                        label: 'Recepcionado',
                        prop: 'recepcionado',
                        render: function (data) {
                            return (
                                <span>
                                    { data.recepcionado && <i className="mdi mdi-check icon-sucess"/>}
                                    { !data.recepcionado && <i className="mdi mdi-circle icon-warning"/>}
                                </span>
                            )
                        }
                    },
                    {
                        label: 'Operações',
                        render: function (data, column) {
                            return (
                                <span style={{
                                    'display': 'flex'
                                }}>
                                 <Button plain={true} disabled={!data.recepcionado} onClick={that.baixarArquivo.bind(that, data)}
                                         size="small">Baixar</Button>
                                 <Button type="danger" onClick={that.deletaCard.bind(that, data)}
                                         size="small">Delete</Button>
                                </span>
                            )
                        }
                    }
                ],
                data: []
            },
            grafico1: {
                options: {
                    chart: {
                        id: 'basic-bar'
                    },
                    xaxis: {},
                    plotOptions: {
                        bar: {
                            columnWidth: '20%'
                        }
                    }
                },
                series: [
                    {
                        name: 'nº de cards',
                        data: []
                    }
                ]
            },
            grafico2: {
                options: {
                    chart: {
                        id: 'basic-bar'
                    },
                    xaxis: {},
                    stroke: {
                        show: true,
                        curve: 'smooth',
                        lineCap: 'butt',
                        colors: undefined,
                        width: 2,
                        dashArray: 0
                    }

                },
                series: [
                    {
                        name: 'nº de cards',
                        data: []
                    }
                ]
            },
            grafico3: {
                options: {
                    chart: {
                        id: 'basic-bar'
                    },
                    xaxis: {},
                    plotOptions: {
                        bar: {
                            columnWidth: '20%'
                        }
                    }
                },
                series: [
                    {
                        name: 'nº de cards',
                        data: []
                    }
                ]
            },
        };
    }

    atualizaGraficos(){

        const { grafico1, grafico2, grafico3 } = this.state;

        CardService.contarCards().then((response) => {
            var r = response.data;

            grafico1.options.xaxis.categories = r.map(card => card.tipoCard);
            grafico1.series[0].data = r.map(card => card.contador);

            this.setState(
                {
                    grafico1
                }
            )

            this.refs.grafico1.chart.updateOptions(grafico1.options)

        })

        CardService.contabilizarCardsPorData().then((response) => {
            var r2 = response.data;

            grafico2.options.xaxis.categories = r2.map(card => card.dataInicio);
            console.log(r2.map(card => card.dataInicio))
            grafico2.series[0].data = r2.map(card => card.contador);

            this.setState(
                {
                    grafico2
                }
            )

            this.refs.grafico2.chart.updateOptions(grafico2.options)

        })

        CardService.contabilizarCardsRecepcionados().then((response) => {
            var r3 = response.data;

            grafico3.options.xaxis.categories = r3.map(card => card.recepcionado);
            console.log(r3.map(card => card.recepcionado))
            grafico3.series[0].data = r3.map(card => card.contador);

            this.setState(
                {
                    grafico3
                }
            )

            this.refs.grafico3.chart.updateOptions(grafico3.options)

        })
    }
    async componentDidMount() {

        this.atualizaGraficos();
        this.buscarTabela(1)

    }

    render() {
        return (
            <View menu={this.menu} history={this.props.history} title="Dashboard"
                  breadcrumb={this.breadcrumb}>
                <Layout.Row className="full-height" justify="center" align="middle" type="flex">
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
                    <Layout.Col span={12}>
                        <Card>
                            <h4>Numero de cartões ultimos 2 meses</h4>
                            <Chart
                                ref="grafico2"
                                options={this.state.grafico2.options}
                                series={this.state.grafico2.series}
                                type="line"
                            />
                        </Card>
                    </Layout.Col>
                </Layout.Row>
                <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                    <Layout.Col className="full-height" span={12}>
                        <Card className="card-tabela">
                            <h4>Numero de cartões</h4>
                            <Chart
                                ref="grafico3"
                                options={this.state.grafico3.options}
                                series={this.state.grafico3.series}
                                type="bar"
                            />
                        </Card>
                    </Layout.Col>
                </Layout.Row>
                <Layout.Row className="full-height" justify="center" align="middle" type="flex">
                    <Layout.Col className="full-height" span={24}>
                        <Card className="card-tabela">
                            <Table
                                style={{ width: '100%' }}
                                columns={this.state.tabela.columns}
                                data={this.state.tabela.data}
                                emptyText="Nenhum card registrado"
                                border={true}
                            />
                            <Pagination layout="prev, pager, next"
                                        onCurrentChange={this.buscarTabela.bind(this)}
                                        total={this.state.tabela.total}
                                        small={true}/>
                        </Card>
                    </Layout.Col>
                </Layout.Row>
            </View>
        )
    }
}

export default withRouter(Dashboard);
