import React, { Component } from "react";
import { Table } from "react-bootstrap";
import PostBox from "./PostBox";

class PostList extends Component {
    render() {
        return(
            <div id="post-list">
                <Table>
                    <thead>
                        <tr>
                            <th>Score</th>
                            <th>Content</th>
                            <th>URL</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.props.posts.map((post, i) => <PostBox post={post} key={i} />)}
                    </tbody>
                </Table>
            </div>
        );
    }
}
export default PostList;
