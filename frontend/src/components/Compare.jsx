import React, { Component } from "react";
import SearchService from "../api/SearchService";
import "../styles/searchBar.css";
import {Button, FormControl, FormGroup, InputGroup} from "react-bootstrap";



class Compare extends Component {
    state = {
        value2: ""
    };

    componentDidMount() {
        this.setState({ value2: this.props.value2 });
    }

    handleChange2(e) {
        this.setState({ value2: e.target.value2 });
    }

    componentWillReceiveProps(props) {
        this.setState({value2: props.value2});
    }

    render() {

        return (
            <div className="compare">
                <form onSubmit={this.props.handleSubmit}>
                    <FormGroup>
                        <InputGroup>
                            <FormControl
                                type="text"
                                name="query"
                                value={this.state.value2}
                                default=""
                                placeholder="Type a query you want to compare"
                                onChange={this.handleChange2.bind(this)}
                            />
                            <InputGroup.Button>
                                <Button type="compare">
                                Compare
                                </Button>
                            </InputGroup.Button>

                        </InputGroup>
                    </FormGroup>
                </form>
            </div>
        );
    }
}

export default Compare;
