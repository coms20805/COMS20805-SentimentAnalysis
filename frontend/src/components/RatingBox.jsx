import React, { Component } from "react";

class RatingBox extends Component {
    render() {
        const ratingNumber = parseFloat(Math.round(this.props.rating * 100) / 100).toFixed(2);
        let ratingLiteral = "neutral";
        if (ratingNumber < 0) {
            ratingLiteral = "negative";
        }
        else if (ratingNumber > 0) {
            ratingLiteral = "positive";
        }
        if (Math.abs(ratingNumber) > 0.5) {
            ratingLiteral = "very " + ratingLiteral;
        }
        return(
            <div className="rating-box">
                <div className="rating-description">The sentiment towards this piece of tech is <strong>{ratingLiteral}</strong></div>
                <div className="rating">
                    <div className="rating-text">Rating</div>
                    <div className="rating-number">{ratingNumber}</div>
                </div>
            </div>
        );
    }
}
export default RatingBox;
