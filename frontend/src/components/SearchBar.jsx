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
        suggestions: []
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

    componentWillReceiveProps(props) {
        this.setState({value: props.value});
    }

    suggestionSelected(value){
        this.setState({value, suggestions: []});
    }

    renderSuggestions() {
        const suggestions = this.state.suggestions;
        if (suggestions.length === 0) {
          return null;
        }
        return (
          <ul>
              {suggestions.map((item) => <li onClick={() => this.suggestionSelected(item)}>{item}</li>)}
          </ul>
        );
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
                        {this.renderSuggestions()}
                    </FormGroup>
                </form>
            </div>
        );
    }
}