import React, { Component } from "react";
import "../styles/searchBar.css";
import {Button, FormControl, FormGroup, Glyphicon, InputGroup} from "react-bootstrap";

class SearchBar extends Component {
        render() {
        return (
            <div id="search-bar">
                <form onSubmit={this.props.handleSubmit}>
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
            </div>
        );
    }
}

export default SearchBar;