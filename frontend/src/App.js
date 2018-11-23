import React, { Component } from "react";
import "./App.css";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import Main from "./components/Main";
import ResultsLayout from "./components/ResultsLayout";

class App extends Component {
  render() {
    return (
        <div>
            <Router>
                <Switch>
                    <Route name="results" exact path="/results" component={ResultsLayout}/>
                    <Route name="index" exact path="/" component={Main}/>
                </Switch>
            </Router>
        </div>
    );
  }
}

export default App;
