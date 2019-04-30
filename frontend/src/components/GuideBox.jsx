import React, { Component } from "react";
import {Glyphicon} from "react-bootstrap";

export default class GuideBox extends Component {

    state = {
        ratingLiteral: "neutral",
        ratingNumber: 0,
        ratingClasses: "rating badge badge-light"
    }
    render() {
        
        return(
            <div className="guide-box">
                <div className="step">
                    <p>Search for open source libraries, programming languages, etc.</p>
                    <Glyphicon glyph="search" />
                </div>
                <div className="step">
                    <p>Receive detailed data about the sentiment towards the piece of tech</p>
                    <Glyphicon glyph="signal" />
                </div>
                <div className="step">
                    <p>Make educated decisions when choosing the tech stack for your project</p>
                    <Glyphicon glyph="ok" />
                </div>
            </div>
        );
    }
}
