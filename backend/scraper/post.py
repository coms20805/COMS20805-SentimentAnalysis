import hashlib

from textblob import TextBlob


class Post:
    DOC_TYPE = "post"

    def __init__(self, content, timestamp=None, score=None, url=None, id=None):
        self._score = TextBlob(content).sentiment.polarity if score is None else score
        self._timestamp = timestamp
        self._content = content
        self._url = url

        # posts are uniquely defined by their content.
        self._id = int(hashlib.sha1(self.content.encode("utf-8")).hexdigest(), 16) % (10 ** 8) if not id else id

    @property
    def score(self):
        return self._score

    @property
    def timestamp(self):
        return self._timestamp

    @property
    def content(self):
        return self._content

    @property
    def id(self):
        return self._id

    @property
    def url(self):
        return self._url

    def __eq__(self, other):
        return isinstance(other, Post) and self.id == other.id

    def __str__(self):
        return str(self.to_dict())

    def to_dict(self):
        return {
            "content": self.content,
            "timestamp": self.timestamp,
            "id": self.id,
            "score": self.score,
            "url": self.url
        }

    def __hash__(self):
        return self.id
