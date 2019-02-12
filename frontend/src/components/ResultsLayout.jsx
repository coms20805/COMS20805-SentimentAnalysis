import React, { Component } from "react";
import SearchBar from "./SearchBar";
import {Col, Grid, Row, Button} from "react-bootstrap";
import SearchService from "../api/SearchService";
import * as qs from "query-string";
import RatingBox from "./RatingBox";
import PostList from "./PostList";
import {withRouter} from "react-router-dom";
import PlotLayout from "./PlotLayout";
import Header from "./Header";


class ResultsLayout extends Component {

    state = {
        isLoading: true,
        showPlot: false,
        query: "",
        rating: undefined,
        posts: []
    };

    async componentDidMount() {
        const parsedQuery = qs.parse(this.props.location.search).query;
        if (parsedQuery && parsedQuery.length > 0) {
            this.loadPosts(parsedQuery);
        }
        else {
            this.props.history.push("/search");
        }
    }

    async loadPosts(query) {
        const data = await SearchService.getPosts(query);
        this.setState({isLoading: false, query: query, rating: data.rating, posts: data.posts});
    }

    handleSubmit(e) {
        e.preventDefault();
        if (e.target.query.value.length > 0) {
            this.props.history.push("/search?query=" + e.target.query.value);
            this.loadPosts(e.target.query.value);
        }
    }

    handleTogglePlot() {
        if (this.state.showPlot) {
            this.setState({showPlot: false});
        }
        else {
            this.setState({showPlot: true});
        }
    }

    render() {
        const plot = this.state.showPlot ? <div id="plot-container"><Button onClick={this.handleTogglePlot.bind(this)}>Hide plot</Button><PlotLayout query={this.state.query}/></div>
                    :
                    <div id="plot-container"><Button onClick={this.handleTogglePlot.bind(this)}>Show plot</Button></div>;
        return(
            <Grid>
                <Header/>
                <div id="results">
                    <Row className="show-grid" xs={8} xsOffset={4}>
                        <Col xs={8} xsOffset={2}>
                            {this.state.isLoading ?
                                <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query} />
                                :
                                [this.state.posts && this.state.posts.length === 0 ?
                                    <div>
                                        <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query} />
                                        <p>No results</p>
                                    </div>
                                    :
                                    <div>
                                        <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query} />
                                        {plot}
                                        <RatingBox rating={this.state.rating}/>
                                        <PostList posts={this.state.posts}/>
                                    </div>
                                ]
                            }
                        </Col>
                    </Row>
                </div>
            </Grid>
        );
    }
}
export default withRouter(ResultsLayout);
