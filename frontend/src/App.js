import React from 'react';
import Router from './router';
import { Provider } from 'react-redux';
import { Store } from './store';

import 'element-theme-default';
import './styles/main.scss';

export default class App extends React.Component {
  render() {
    return (
        <Provider store={Store}>
          <Router/>
        </Provider>
    )
  }
}
