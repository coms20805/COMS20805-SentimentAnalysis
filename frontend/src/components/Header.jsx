import React, { Component } from "react";
import {Col, Row} from "react-bootstrap";
import "../styles/header.css";

class Header extends Component {
    render() {
        return(
            <div id="header">
                <Row className="show-grid" xs={12} xsOffset={4}>
                    <Col xs={12} xsOffset={2} className="header-bar">
                        <h1>Sentiment</h1>
                    </Col>
                </Row>
            </div>
        );
    }
}
export default Header;
