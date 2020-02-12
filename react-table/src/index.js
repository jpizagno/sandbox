import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './index.css'
import App from './App.js';

class StartPage extends Component {
  render() {
    return (
      <div classname="container">
        <h1>Hello World!</h1>
        <p>I just created my first React App</p>
        <App></App>
      </div>
    );
  }
}

  ReactDOM.render(
    <StartPage />,
    document.getElementById('app')
  );