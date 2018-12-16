from scraper_interface import Scraper
import praw


class RedditScraper(Scraper):
    def __init__(self):
        """
        This bot collects posts from the reddit api.
        TODO: document implementation details
        """
        self.reddit = praw.Reddit('bot', user_agent="bot user agent")

    def fetch_posts(self, limit):
        pass

    def fetch(self, url):
        pass

    def _unmarshal(self, some_json):
        pass
