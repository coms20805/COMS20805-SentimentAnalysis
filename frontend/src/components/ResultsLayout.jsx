import React, { Component } from "react";
import SearchBar from "./SearchBar";
import Compare from "../api/Compare";
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
        posts: [],
        count: 0
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
        this.setState({isLoading: false, query: query, rating: 0.0, posts: data.result, count: 1});
    }

    async loadPosts(query2) {
      const data = await Compare.getPosts(query2);
      this.setState({isLoading: false, query: query2, rating: 0.0, posts: data.result, count: 0});
    }

    handleSubmit(e) {
        e.preventDefault();
        if (e.target.query.value.length > 0) {
            this.props.history.push("/search?query=" + e.target.query.value);
            this.loadPosts(e.target.query.value);
        }
        // if (e.target.query2.value.length > 0) {
        //     this.props.history.push("/search?query=" + e.target.query2.value);
        //     this.loadPosts(e.target.query2.value);
        // }
    }

    handleTogglePlot() {
        if (this.state.showPlot) {
            this.setState({showPlot: false});
        }
        else {
            this.setState({showPlot: true});
        }
    }

    comparef(query2,e){
      e.preventDefault();
      if(this.state.count == 1){
        this.loadPosts(e.target.query2.value)
      }
    }
    getBadgeClasses(){
      let classes = "badge m-2 badge-";
      classes += this.state.rating >= 0 ? "success" : "danger";
      return classes;
    }

    render() {
        const plot = this.state.showPlot ? <div id="plot-container"><Button onClick={this.handleTogglePlot.bind(this)}>Hide plot</Button><PlotLayout query={this.state.query} query2={this.state.query2}/></div>
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
                                        <button onclick="comparef(query2,e)"> Compare </button>
                                        {plot}
                                        // <button id="button1" style="display:block;" onclick="document.getElementById('button2').style.display = 'block'; this.style.display = 'none';">Button 1</button>
                                        // <button id="Compare" style="display:none;" onclick="document.getElementById('search').style.display = 'block' ; this.style.display = 'none';">Compare</button>
                                        // <SearchBar compare handleSubmit={this.handleSubmit.bind(this)} value={this.state.query2} />
                                        // {plot}
                                      //  <RatingBox rating={this.state.rating}/>
                                      //  <SearchBar handleSubmit={this.handleSubnmit.bind(this)} value={this.state.query2} />

                                        <RatingBox className={this.getBadgeClasses()} rating={this.state.rating}/>
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
