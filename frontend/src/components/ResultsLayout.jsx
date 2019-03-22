import React, { Component } from "react";
import SearchBar from "./SearchBar";
import SearchService from "../api/SearchService";
import * as qs from "query-string";
import RatingBox from "./RatingBox";
import PostList from "./Post/PostList";
import {withRouter} from "react-router-dom";
import PlotLayout from "./PlotLayout";
import Header from "./Header";
import Error from "./Error";
import {PropagateLoader} from "react-spinners";

class ResultsLayout extends Component {

    state = {
        isLoading: true,
        showPlot: false,
        query: "",
        rating: undefined,
        posts: [],
        error: false,
        errorCode: undefined
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
        SearchService.getPosts(query)
            .then(data => {
                this.setState({isLoading: false, query: query, rating: data.rating, posts: data.posts, showPlot: false});
            })
            .catch(error => {
                this.setState({error: true, errorCode: error.message, showPlot: false});
            });
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
        const plot = this.state.showPlot ? <div className="plot-container"><button type="button" className="btn btn-secondary" onClick={this.handleTogglePlot.bind(this)}>Hide plot</button><PlotLayout query={this.state.query} /></div>
            :
            <div className="plot-container"><button type="button" className="btn btn-primary" onClick={this.handleTogglePlot.bind(this)}>Show plot</button></div>;
        const results = <div id="results">
                            {this.state.isLoading ?
                                <div>
                                    <SearchBar handleSubmit={this.handleSubmit.bind(this)} value={this.state.query} />
                                    <div className="loader">
                                        <PropagateLoader
                                            color={"rgb(8, 104, 194)"}
                                            margin="10px"
                                        />
                                    </div>
                                </div>
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
                                        <RatingBox rating={this.state.rating} />
                                        <PostList posts={this.state.posts} />
                                    </div>
                                ]
                            }
                        </div>;
        return(
            <div className="grid-container">
                <Header/>
                <div className="main">
                    {this.state.error ? <Error code={this.state.errorCode} /> : results}
                </div>
            </div>
        );
    }
}
export default withRouter(ResultsLayout);
