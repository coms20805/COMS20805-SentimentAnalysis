import React, { Component } from "react";

class RatingBox extends Component {
    render() {
        return(
            <div id="rating-box">
                <p>Rating: {this.props.rating}</p>
            </div>
        );
    }
}
export default RatingBox;
