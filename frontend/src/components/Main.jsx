import React, { Component } from "react";
import SearchBar from "./SearchBar";
import {Col, Grid, Row} from "react-bootstrap";

class Main extends Component {
    render() {
        return(
            <div id="index">
                <Grid>
                    <Row className="show-grid" xs={8} xsOffset={4}>
                        <Col xs={8} xsOffset={2}>
                            <SearchBar/>
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}
export default Main;
