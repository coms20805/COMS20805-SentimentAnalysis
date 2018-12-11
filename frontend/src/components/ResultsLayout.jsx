import React, { Component } from "react";
import SearchBar from "./SearchBar";
import {Col, Grid, Row} from "react-bootstrap";
import {SearchService} from "../api/SearchService";
import * as qs from "query-string";
import RatingBox from "./RatingBox";
import PostList from "./PostList";
import {withRouter} from "react-router-dom";


class ResultsLayout extends Component {

    state = {
        isLoading: true,
        query: "",
        rating: undefined,
        posts: []
    };

    async componentDidMount() {
        const parsedQuery = qs.parse(this.props.location.search).query;
        if (parsedQuery && parsedQuery.length > 0) {
            this.loadResults(parsedQuery);
        }
        else {
            this.props.history.push("/search");
        }
    }

    async loadResults(query) {
        const data = await SearchService.getResults(query);
        this.setState({isLoading: false, query: query, rating: data.rating, posts: data.posts});
    }

    handleSubmit(e) {
        e.preventDefault();
        if (e.target.query.value.length > 0) {
            this.props.history.push("/search?query=" + e.target.query.value);
            this.loadResults(e.target.query.value);
        }
    }

    render() {
        return(
            <div id="results">
                <Grid>
                    <Row className="show-grid" xs={8} xsOffset={4}>
                        <Col xs={8} xsOffset={2}>
                            { this.state.isLoading ?
                                <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query}/>
                                :
                                    [ this.state.posts && this.state.posts.length === 0 ?
                                        <div>
                                            <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query}/>
                                            <p>No results</p>
                                        </div>
                                        :
                                        <div>
                                            <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query}/>
                                            <p>There are results!</p>
                                            <RatingBox rating={this.state.rating}/>
                                            <PostList posts={this.state.posts}/>
                                        </div>
                                    ]
                            }
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}
export default withRouter(ResultsLayout);
