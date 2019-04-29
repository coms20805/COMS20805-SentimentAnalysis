import React, { Component } from "react";
import {Line as LineChart} from "react-chartjs";
import SearchService from "../api/SearchService";
import Error from "./Error";
import moment from "moment";
import {PropagateLoader} from "react-spinners";

class PlotLayout extends Component {

    state = {
        isLoading: true,
        dates: undefined,
        scores: undefined,
        dates2: undefined,
        scores2: undefined,
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
    roundScores2(scores2) {
        let scoresRounded2 = [];
        const n = scores2.length;
        for (var i = 0; i < n; i++) {
            scoresRounded2.push(parseFloat(Math.round(scores2[i] * 100) / 100).toFixed(2));
        }
        return scoresRounded2;
    }

    formatDates(dates) {
        let formattedDates = [];
        const n = dates.length;
        for (var i = 0; i < n; i++) {
            formattedDates.push(moment(dates[i]).format("D MMM YYYY"));
        }
        return formattedDates;
    }
    formatDates2(dates2) {
        let formattedDates2 = [];
        const n = dates2.length;
        for (var i = 0; i < n; i++) {
            formattedDates2.push(moment(dates2[i]).format("D MMM YYYY"));
        }
        return formattedDates2;
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

    async loadTimeSeries2(query2) {
        SearchService.getTimeSeries2(query2)
            .then(data => {
                this.setState({isLoading: false, dates2: this.formatDates2(data.timestamps), scores2: this.roundScores2(data.medians)});
            })
            .catch(error => {
                this.setState({error: true, errorCode: error.message});
            });
    }

    render() {
        const plot = this.state.isLoading ?
                        <div className="loader">
                            <PropagateLoader
                                color={"rgb(8, 104, 194)"}
                                margin="10px"
                            />
                        </div>
                        :
                        <LineChart
                            data={{
                                labels: this.state.dates,
                                datasets: [{
                                    label: "Historical Sentiment",
                                    data: this.state.scores,
                                    fillColor: "rgba(0,0,0,0)",
                                    strokeColor: "white"
                                }]
                            }}

                            data2={{
                                labels: this.state.dates2,
                                datasets: [{
                                    label: "Historical Sentiment 2",
                                    data2: this.state.scores2,
                                    fillColor: "rgba(0,0,0,0)",
                                    strokeColor: "black"
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
