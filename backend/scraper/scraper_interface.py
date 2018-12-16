from abc import ABC, abstractmethod

"""
A generic Scraper to encapsulate all kinds of scrapers 
(eg: reddit scraper, twitter scraper, etc)
All implementations should ideally inherit from this class and override the abstract methods.
"""


class Scraper(ABC):

    @abstractmethod
    def _unmarshal(self, some_json):
        """
        private method to convert the json from any source to a generic post

        :param some_json: a json object representing post info from a source (reddit, hn, twitter)
        :return: Post
        """
        raise Exception("NotImplementedException")

    @abstractmethod
    def fetch(self, url):
        """
        public method to fetch a Post object from a given url

        :param url:
        :return: Post
        """
        raise Exception("NotImplementedException")

    @abstractmethod
    def fetch_posts(self, limit):
        """
        returns a list of posts
        :param limit: how many posts to fetch
        :return: [Post]
        """
        raise Exception("NotImplementedException")
