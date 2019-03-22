import React, { Component } from "react";
import Moment from "react-moment";

class PostBox extends Component {
    render() {
        return(
            <tr className="post-box">
                <td data-label="Score">{parseFloat(Math.round(this.props.post.score * 100) / 100).toFixed(2)}</td>
                <td data-label="Date"><Moment format="D MMM YYYY">{this.props.post.timestamp}</Moment></td>
                <td data-label="Content">{this.props.post.content}</td>
                <td data-label="URL"><a href={this.props.post.url} target="_blank" rel="noopener noreferrer">See post</a></td>
            </tr>
        );
    }
}
export default PostBox;