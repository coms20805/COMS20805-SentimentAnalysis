import React, { Component } from "react";
import PostBox from "./PostBox";

class PostList extends Component {
    render() {
        return(
            <div id="post-list">
                <table>
                    <thead>
                        <tr>
                            <th>Score</th>
                            <th>Date</th>
                            <th>Content</th>
                            <th>URL</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.props.posts.map((post, i) => <PostBox post={post} key={i} />)}
                    </tbody>
                </table>
            </div>
        );
    }
}
export default PostList;
