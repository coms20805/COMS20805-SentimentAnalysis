import React, { Component } from "react";
import {Line as LineChart} from "react-chartjs";
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
                        <LineChart
                            data={{
                                labels: this.state.dates,
                                datasets: [{
                                    label: "Historical Sentiment",
                                    data: this.state.scores,
                                    fillColor: "rgba(0,0,0,0)",
                                    strokeColor: "red"
                                }]
                            }}
                            options={{
                                fill: false,
                                responsive: true
                            }}
                            width="600"
                            height="400"
                        />;
        return(
            <div id="plot">
                {this.state.error ? <Error code={this.state.errorCode} /> : plot}
            </div>
        );
    }
}
export default PlotLayout;
