import React, { Component } from "react";

class PostBox extends Component {
    render() {
        return(
            <tr className="post-box">
                <td>{this.props.post.score}</td>
                <td>{this.props.post.content}</td>
                <td>{this.props.post.url}</td>
            </tr>
        );
    }
}
export default PostBox;