import React, { Component } from "react";
import "./App.css";
import {BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";
import ResultsLayout from "./components/ResultsLayout";
import ErrorBoundary from "./components/ErrorBoundary";

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
                                                                              </ErrorBoundary>
                    )} />
                </Switch>
            </Router>
        </div>
    );
  }
}

export default App;
