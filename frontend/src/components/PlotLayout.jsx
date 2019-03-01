import React, { Component } from "react";
import Plot from "react-plotly.js";
import SearchService from "../api/SearchService";
import Error from "./Error";

class PlotLayout extends Component {

    state = {
        isLoading: true,
        dates: undefined,
        scores: undefined,
        error: false,
        errorCode: undefined
    };

    async componentDidMount() {
        this.loadTimeSeries(this.props.query);
    }

    async loadTimeSeries(query) {
        SearchService.getTimeSeries(query)
            .then(data => {
                this.setState({isLoading: false, dates: data.timestamps, scores: data.medians});
            })
            .catch(error => {
                this.setState({error: true, errorCode: error.message});
            });
    }

    render() {
        const plot = this.state.isLoading ?
                        <p>Loading...</p>
                        :
                        <Plot
                            data={[
                                {
                                    x: this.state.dates,
                                    y: this.state.scores,
                                    type: 'graph',
                                    mode: 'lines+points',
                                    marker: { color: 'red' },
                                },
                            ]}
                            layout={{ width: 800, height: 400, title: 'Historical Sentiment' }}
                        />;
        return(
            <div id="plot">
                {this.state.error ? <Error code={this.state.errorCode} /> : plot}
            </div>
        );
    }
}
export default PlotLayout;
