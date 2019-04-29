import React, { Component } from "react";
import "./App.css";
import "./styles/style.css";
import {BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";
import ResultsLayout from "./components/ResultsLayout";
import ErrorBoundary from "./components/ErrorBoundary";
import Particles from 'react-particles-js';

const particleOpt = {
  particles: {
    number:{
      value: 150,
      density: {
        enable: true,
        value_area: 800,
      }
    }
  }
}



class App extends Component {
  render() {
    return (
        <div>

            <Router>
                <Switch>
                    <Route exact path="/" render={() => (<Redirect to="/search" />)} />
                    <Route name="search" exact path="/search" render={() => (
                                                                              <ErrorBoundary>

                                                                                <ResultsLayout/>
                                                                                <Particles
                                                                                params = {particleOpt}
                                                                                />  
                                                                              </ErrorBoundary>

                    )} />

                </Switch>
            </Router>
        </div>
    );
  }
}

export default App;
