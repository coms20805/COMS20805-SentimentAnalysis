import React, { Component } from "react";

class PostBox extends Component {
    render() {
        return(
            <tr className="post-box">
                <td>{this.props.post.rating}</td>
                <td>{this.props.post.content}</td>
                <td>{this.props.post.url}</td>
                <td>{this.props.post.timestamp}</td>
            </tr>
        );
    }
}
export default PostBox;