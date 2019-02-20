import React, { Component } from "react";
import {Grid} from "react-bootstrap";
import Header from "./Header";

export default class Error extends Component {
    render() {
        return(
            <div id="error">
                <h1>{this.props.code}</h1>
                {/* <p>{this.props.error.message}</p> */}
            </div>
        );
    }
}