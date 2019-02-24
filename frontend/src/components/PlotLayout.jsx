import React, { Component } from "react";
import Plot from "react-plotly.js";
import SearchService from "../api/SearchService";
import Compare from "../api/Compare";


class PlotLayout extends Component {

    state = {
        isLoading: true,
        dates: undefined,
        scores: undefined
    };

    async componentDidMount() {
        this.loadTimeSeries(this.props.query);
        this.loadTimeSeries2(this.props.query2);
    }

    async loadTimeSeries(query) {
        const data = await SearchService.getTimeSeries(query);
        this.setState({isLoading: false, dates: Object.keys(data), scores: Object.values(data)});
    }

    async loadTimeSeries2(query2) {
        const data2 = await Compare.getTimeSeries(query2);
        this.setState({isLoading: false, dates: Object.keys(data2), scores: Object.values(data2)});
    }

    render() {
        return(
            <div id="plot">
                {this.state.isLoading ?
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
                        data2={[
                            {
                                x: this.state.dates,
                                y: this.state.scores,
                                type: 'graph',
                                mode: 'lines+points',
                                marker: { color: 'blue'},
                            },
                          ]}
                        layout={{ width: 800, height: 400, title: 'Historical Sentiment' }}
                    />
                }
            </div>
        );
    }
}
export default PlotLayout;
