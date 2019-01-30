import React, { Component } from "react";

class PostBox extends Component {
    render() {
        return(
            <tr className="post-box">
                <td>{parseFloat(Math.round(this.props.post.score * 100) / 100).toFixed(2)}</td>
                <td>{this.props.post.timestamp}</td>
                <td>{this.props.post.content}</td>
                <td>{this.props.post.url}</td>
            </tr>
        );
    }
}
export default PostBox;