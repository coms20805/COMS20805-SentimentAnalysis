import React, { Component } from "react";

class RatingBox extends Component {

    state = {
        ratingLiteral: "neutral",
        ratingNumber: 0,
        ratingClasses: "rating badge badge-light"
    }

    getRoundedRating() {
        const ratingNumber = parseFloat(Math.round(this.props.rating * 100) / 100).toFixed(2);
        
        return ratingNumber;
    }

    parseRating() {
        const ratingNumber = this.getRoundedRating();

        let ratingLiteral = "neutral";
        let ratingClasses = "rating badge badge-";
        if (ratingNumber < 0) {
            ratingLiteral = "negative";
            ratingClasses += "danger";
        }
        else if (ratingNumber > 0) {
            ratingLiteral = "positive";
            ratingClasses += "success";
        }
        else {
            ratingClasses += "light";
        }
        if (Math.abs(ratingNumber) > 0.5) {
            ratingLiteral = "very " + ratingLiteral;
        }

        this.setState({ratingLiteral, ratingNumber, ratingClasses})
    }

    componentDidMount() {
        this.parseRating();
    }

    render() {
        
        return(
            <div className="rating-box">
                <div className="rating-description"><span>The sentiment towards this piece of tech is <strong>{this.state.ratingLiteral}</strong></span></div>
                <div className={this.state.ratingClasses}>
                    <div className="rating-text">Rating</div>
                    <div className="rating-number">{this.state.ratingNumber}</div>
                </div>
            </div>
        );
    }
}
export default RatingBox;
