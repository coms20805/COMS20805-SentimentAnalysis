""""
example usage:
hn = HnScraper()
posts = hn.fetch_posts(limit=30, seach_query="foobar")
"""

import requests
from post import Post
from scraper_interface import Scraper


class HnScraper(Scraper):
    def __init__(self):
        self.BASE_URL = "https://hn.algolia.com/api/v1/search"

    def fetch(self, search_keyword):
        params = {"query": search_keyword}
        req = requests.get(self.BASE_URL, params=params)

        #more memory efficient than a list comprehension
        for json_post in req.json()["hits"]:
            yield self._unmarshal(json_post)

    def _unmarshal(self, hn_json):
        """
        unmarshal json into a Post object
        """
        content = hn_json["title"]
        timestamp = hn_json["created_at"]
        url = hn_json["url"]
        id = hn_json["story_id"]  # might be None
        return Post(content=content, timestamp=timestamp, url=url, id=id)

    def fetch_posts(self, search_query, limit=20):
        def limit_reached():
            return len(posts) >= limit

        posts = []
        if search_query is None:
            raise ValueError("search query is empty")

        post_iterator = self.fetch(search_query)
        posts = []
        for post in post_iterator:
            posts.append(post)
            if limit_reached():
                break

        return posts
