import React, { Component } from "react";
import {Link} from "react-router-dom";
import "../styles/header.css";

export default class Header extends Component {
    render() {
        return(
            <div className="header">
                <h1><Link to="/">Tech Sentiment</Link></h1>
            </div>
        );
    }
}
