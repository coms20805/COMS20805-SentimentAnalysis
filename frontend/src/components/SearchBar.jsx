import React, { Component } from "react";
import "../styles/searchBar.css";
import {Button, FormControl, FormGroup, Glyphicon, InputGroup} from "react-bootstrap";
import {Redirect} from "react-router-dom";

class SearchBar extends Component {

    state = {
        redirect: false,
        query: null
    };

    handleSubmit(e) {
        e.preventDefault();
        this.setState({redirect: true, query: e.target.query.value});
    }

    render() {
        return (
            <div id="search-bar">
                <form onSubmit={this.handleSubmit.bind(this)}>
                    <FormGroup>
                        <InputGroup>
                            <FormControl
                                type="text"
                                name="query"
                                // value={this.state.value}
                                placeholder={this.props.default || "Search"}
                            />
                            <InputGroup.Button>
                                <Button type="submit">
                                    <Glyphicon glyph="search" />
                                </Button>
                            </InputGroup.Button>
                        </InputGroup>
                    </FormGroup>
                </form>
                {this.state.redirect && (
                    <Redirect to={"/results?query=" + this.state.query}/>
                )}
            </div>
        );
    }
}

export default SearchBar;