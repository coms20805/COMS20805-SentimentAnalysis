import React, { Component } from "react";

class PostBox extends Component {
    render() {
        return(
            <tr className="post-box">
                <td data-label="Score">{parseFloat(Math.round(this.props.post.score * 100) / 100).toFixed(2)}</td>
                <td data-label="Date">{this.props.post.timestamp}</td>
                <td data-label="Content">{this.props.post.content}</td>
                <td data-label="URL">{this.props.post.url}</td>
            </tr>
        );
    }
}
export default PostBox;