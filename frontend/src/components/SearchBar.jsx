import React, { Component } from "react";
import "../styles/searchBar.css";
import {Button, FormControl, FormGroup, Glyphicon, InputGroup} from "react-bootstrap";



class SearchBar extends Component {
    state = {
        value: ""
    };

    componentDidMount() {
        this.setState({ value: this.props.value });
    }

    handleChange(e) {
        this.setState({ value: e.target.value });
    }

    componentWillReceiveProps(props) {
        this.setState({value: props.value});
    }

    render() {

        return (
            <div className="search-bar">
                <form onSubmit={this.props.handleSubmit}>
                    <FormGroup>
                        <InputGroup>
                            <FormControl
                                type="text"
                                name="query"
                                value={this.state.value}
                                default=""
                                placeholder="Search"
                                onChange={this.handleChange.bind(this)}
                            />
                            <InputGroup.Button>
                                <Button type="submit">
                                    <Glyphicon glyph="search" />
                                </Button>
                            </InputGroup.Button>
                        </InputGroup>
                    </FormGroup>
                </form>
            </div>
        );
    }
}

export default SearchBar;