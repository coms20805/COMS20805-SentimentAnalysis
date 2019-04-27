import React, { Component } from "react";
import "../styles/searchBar.css";
import {Button, FormControl, FormGroup, Glyphicon, InputGroup} from "react-bootstrap";

export default class SearchBar extends Component {
    availableQueries = [
        "Java",
        "Python",
        "JavaScript",
        "JSON",
        "MATLAB",
        "JQuery",
        "CSharp",
        "Opal",
        "Ruby",
        "SQL",
        "Swift",
      ];

    state = {
        value: "",
        suggestions: [],
        inputActive: false
    };
    
    componentDidMount() {
        this.setState({value: this.props.value});
    }

    handleChange(e) {
        let value = e.target.value;
        let suggestions = [];
        if (value.length > 0) {
            const regex = new RegExp(`^${value}`, 'i');
            suggestions = this.availableQueries.sort().filter(v => regex.test(v));
        }

        this.setState({suggestions, value});
    }

    handleFocus() {
        if (!this.state.inputActive) {
            this.setState({inputActive: true});
        }
    }

    handleBlur() {
        if (this.state.inputActive) {
            this.setState({suggestions: [], inputActive: false});
        }
    }

    componentWillReceiveProps(props) {
        this.setState({value: props.value});
    }

    suggestionSelected(value) {
        this.setState({value, suggestions: []});
    }

    renderSuggestions() {
        const suggestions = this.state.suggestions;
        if (suggestions.length === 0) {
          return null;
        }
        return (
          <div className="autocomplete-items">
              {suggestions.map((item, key) => <div key={key} onMouseDown={() => this.suggestionSelected(item)}>{item}</div>)}
          </div>
        );
    }

    render() {

        return (
            <div className="search-bar">
                <form onSubmit={this.props.handleSubmit} autocomplete="off">
                    <FormGroup>
                        <div className="autocomplete">
                            <InputGroup>
                                <FormControl
                                    type="text"
                                    name="query"
                                    value={this.state.value}
                                    default=""
                                    placeholder="Search"
                                    onChange={this.handleChange.bind(this)}
                                    onFocus={this.handleFocus.bind(this)}
                                    onBlur={this.handleBlur.bind(this)}
                                />
                                {this.renderSuggestions()}
                                <InputGroup.Button>
                                    <Button type="submit">
                                        <Glyphicon glyph="search" />
                                    </Button>
                                </InputGroup.Button>
                            </InputGroup>
                        </div>
                    </FormGroup>
                </form>
            </div>
        );
    }
}