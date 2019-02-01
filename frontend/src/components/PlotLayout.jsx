import React, { Component } from "react";
import Plot from 'react-plotly.js';

class PlotLayout extends Component {
    render() {
        return(
            <div id="plot">
                <Plot
                    data={[
                        {
                            x: ['1 Feb', '2 Feb', '3 Feb'],
                            y: [0.5, 0.7, 0.4],
                            type: 'graph',
                            mode: 'lines+points',
                            marker: { color: 'red' },
                        },
                    ]}
                    layout={{ width: 800, height: 400, title: 'A Plot' }}
                />
            </div>
        );
    }
}
export default PlotLayout;
