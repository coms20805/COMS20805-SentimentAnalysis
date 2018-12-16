"""
A generic wrapper to encapsulate all
posts fetched from twitter, reddit, hackernews etc
Should be treated as an immutable data class
"""
from textblob import TextBlob


class Post:
    def __init__(self, content, url, id, timestamp=0):
        self._content = content
        self._url = url
        self._timestamp = timestamp
        self._score = TextBlob(content).sentiment.polarity
        self._id = id

    @property
    def content(self):
        return self._content

    @property
    def url(self):
        return self._url

    @property
    def timestamp(self):
        return self._timestamp

    @property
    def score(self):
        return self._score

    @property
    def id(self):
        return self._id

    def __str__(self):
        return "{content=%s, url =%s, score=%s}" \
               % (self.content, self.url, self.score)

    def __eq__(self, other):
        """
        we only care about the content of a post
        """
        return self.content == other.content

    def __hash__(self):
        return hash((self.content))
