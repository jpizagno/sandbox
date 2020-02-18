import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './index.css'
import App from './App.js';

class StartPage extends Component {


  constructor() {
    super();
    this.state = { 
      data: [
        {"source":  "cnn", "link": "http://www.cnn.com", "linkname": "title1", "topic": "topic1"}
        , {"source":  "fox", "link": "http://www.foxnews.com", "linkname": "title2", "topic": "topic1"}
        , {"source":  "fox", "link": "http://www.foxnews.com", "linkname": "title3", "topic": "topic2"}
        , {"source":  "huffpo", "link": "http://www.huggingtonpost.com", "linkname": "title4", "topic": "topic2"}
      ]
    };


  }

  render() {
    return (
      <div classname="container">
        <h1>Hello World!</h1>
        <p>I just created my first React App</p>
        <App data={this.state.data}></App>
      
      </div>
    );
  }
}

  ReactDOM.render(
    <StartPage />,
    document.getElementById('app')
  );