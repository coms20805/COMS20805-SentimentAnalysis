import React, { Component } from "react";
import "../styles/footer.css";
import {Glyphicon} from "react-bootstrap";

export default class Footer extends Component {
    render() {
        return(
            <div className="footer">
                <p>Made with <Glyphicon glyph="heart" /> at the University of Bristol</p>
            </div>
        );
    }
}
