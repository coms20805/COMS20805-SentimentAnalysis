import React, { Component } from "react";
import Plot from "react-plotly.js";
import SearchService from "../api/SearchService";

class PlotLayout extends Component {

    state = {
        isLoading: true,
        dates: undefined,
        scores: undefined
    };

    async componentDidMount() {
        this.loadTimeSeries(this.props.query);
    }

    async loadTimeSeries(query) {
        const data = await SearchService.getTimeSeries(query);
        this.setState({isLoading: false, dates: Object.keys(data), scores: Object.values(data)});
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
                        layout={{ width: 800, height: 400, title: 'Historical Sentiment' }}
                    />
                }
            </div>
        );
    }
}
export default PlotLayout;
