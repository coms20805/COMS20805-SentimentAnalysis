import React, { Component } from "react";

class RatingBox extends Component {
    render() {
        return(
            <div id="rating-box">
                <p>Rating: {parseFloat(Math.round(this.props.rating * 100) / 100).toFixed(2)}</p>
            </div>
        );
    }
}
export default RatingBox;
