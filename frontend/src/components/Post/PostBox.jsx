import React, { Component } from "react";
import Moment from "react-moment";

class PostBox extends Component {

    state = {
        ratingNumber: 0,
        badgeClasses: "badge badge-light"
    }

    parseRating() {
        const ratingNumber = parseFloat(Math.round(this.props.post.score * 100) / 100).toFixed(2);

        let badgeClasses = "badge badge-";
        if (ratingNumber < 0) {
            badgeClasses += "danger";
        }
        else if (ratingNumber > 0) {
            badgeClasses += "success";
        }
        else {
            badgeClasses += "light";
        }

        this.setState({ratingNumber, badgeClasses});
    }

    componentDidMount() {
        this.parseRating();
    }

    render() {
        return(
            <tr className="post-box">
                <td data-label="Score"><span className={this.state.badgeClasses}>{this.state.ratingNumber}</span></td>
                <td data-label="Date"><Moment format="D MMM YYYY">{this.props.post.timestamp}</Moment></td>
                <td data-label="Content">{this.props.post.content}</td>
                <td data-label="URL"><a href={this.props.post.url} target="_blank" rel="noopener noreferrer">See post</a></td>
            </tr>
        );
    }
}
export default PostBox;