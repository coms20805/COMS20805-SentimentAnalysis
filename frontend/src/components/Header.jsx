import React, { Component } from "react";
import {Col, Row} from "react-bootstrap";
//import "../styles/header.css";

class Header extends Component {
    render() {
        return(
            <div id="header">
                <Row className="show-grid" xs={12} xsOffset={5}>
                    <Col xs={8} xsOffset={5} className="header-bar">
                        <h1 data-testid="h1tag" className="Header">Sentiment</h1>
                    </Col>
                </Row>
            </div>
        );
    }
}
export default Header;
