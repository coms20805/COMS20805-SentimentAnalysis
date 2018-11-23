import React, { Component } from "react";
import SearchBar from "./SearchBar";
import {Col, Grid, Row} from "react-bootstrap";
import {SearchService} from "../api/SearchService";
import * as qs from "query-string";
import RatingBox from "./RatingBox";
import PostList from "./PostList";


class ResultsLayout extends Component {

    state = {
        isLoading: true,
        query: undefined,
        rating: undefined,
        posts: undefined
    }

    searchService = new SearchService();

    async componentDidMount() {
        const parsedQuery = qs.parse(this.props.location.search).query;
        const data = await this.searchService.getResults(parsedQuery);
        // await new Promise(resolve => setTimeout(resolve, 1000));
        this.setState({isLoading: false, query: parsedQuery, rating: data.rating, posts: data.posts});
    }

    render() {
        return(
            <div id="results">
                <Grid>
                    <Row className="show-grid" xs={8} xsOffset={4}>
                        <Col xs={8} xsOffset={2}>
                            <SearchBar default={this.state.query}/>
                            { this.state.isLoading ?
                                ""
                                :
                                <div>
                                    <p>There are results!</p>
                                    <RatingBox rating={this.state.rating}/>
                                    <PostList posts={this.state.posts}/>
                                </div>
                            }
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}
export default ResultsLayout;
