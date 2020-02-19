import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './index.css'
import App from './App.js';

class StartPage extends Component {


  constructor() {
    super();
    this.state = { 
      data: [
        {"source":  "cnn", "url": "http://www.cnn.com", "title": "title1", "topic": "topic1"}
        , {"source":  "fox", "url": "http://www.foxnews.com", "title": "title2", "topic": "topic1"}
        , {"source":  "fox", "url": "http://www.foxnews.com", "title": "title3", "topic": "topic2"}
        , {"source":  "huffpo", "url": "http://www.huggingtonpost.com", "title": "title4", "topic": "topic2"}
      ]
    };
  }

  componentWillMount() {
    this.loadTopicMatrix();
  }

  loadTopicMatrix() {

    let url = 'http://127.0.0.1:8093/mediamatrix';
    fetch(url, {
      credentials: 'same-origin',
      method:'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      }})
    .then(response => response.json())
    .then(mediamatrix => {
      // Mediamatrix will have the same format as the data in the constructor()
      this.setState({data: mediamatrix});

      // have to create a new object for the change of state to register in React-Table
      // https://github.com/tannerlinsley/react-table/issues/175
      let newData = [...mediamatrix];

      this.setState({data: newData });

    });
  }

  render() {
    return (
      <div className="container">
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