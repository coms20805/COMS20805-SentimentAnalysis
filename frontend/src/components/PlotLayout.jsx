import React, { Component } from "react";
import {Line as LineChart} from "react-chartjs";
import SearchService from "../api/SearchService";
import Error from "./Error";
import moment from "moment";

class PlotLayout extends Component {

    state = {
        isLoading: true,
        dates: undefined,
        scores: undefined,
        error: false,
        errorCode: undefined
    };

    roundScores(scores) {
        let scoresRounded = [];
        const n = scores.length;
        for (var i = 0; i < n; i++) {
            scoresRounded.push(parseFloat(Math.round(scores[i] * 100) / 100).toFixed(2));
        }
        return scoresRounded;
    }

    formatDates(dates) {
        let formattedDates = [];
        const n = dates.length;
        for (var i = 0; i < n; i++) {
            formattedDates.push(moment(dates[i]).format("D MMM YYYY"));
        }
        return formattedDates;
    }

    async componentDidMount() {
        this.loadTimeSeries(this.props.query);
    }

    async loadTimeSeries(query) {
        SearchService.getTimeSeries(query)
            .then(data => {
                this.setState({isLoading: false, dates: this.formatDates(data.timestamps), scores: this.roundScores(data.medians)});
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
                                bezierCurve: false,
                                fill: false,
                                responsive: true
                            }}
                            width="600"
                            height="300"
                        />;
        return(
            <div id="plot">
                {this.state.error ? <Error code={this.state.errorCode} /> : plot}
            </div>
        );
    }
}
export default PlotLayout;
