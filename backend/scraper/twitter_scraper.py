""""
Implementation to scrape tweets from Twitter.
Twitter search API has a limit of 100.

Example usage:
scraper = TwitterScraper()
posts = scraper.fetch_posts('Node.js', 50)
"""
import requests
import tweepy
from post import Post
import re
import os


class TwitterScraper:
    def __init__(self):
        self.consumer_key = os.getenv("consumer_key")
        self.consumer_secret = os.getenv("consumer_secret")
        self.access_token = os.getenv("access_token")
        self.access_token_secret = os.getenv("access_token_secret")
        self.auth = tweepy.OAuthHandler(self.consumer_key,
                                        self.consumer_secret)
        self.auth.set_access_token(self.access_token, self.access_token_secret)
        self.api = tweepy.API(self.auth)

    def _unmarshal(self, twitter_json):

        """
        Converts json to Post
        :param twitter_json:
        :return: Post
        """

        content = twitter_json.text.encode('utf8').decode()
        url = 'https://twitter.com/i/web/status/' + twitter_json.id_str
        timestamp = twitter_json.created_at

        content = " ".join(re.findall("[a-zA-Z:./0-9]+", content))
        post = Post(content=content, url=url, id=int(twitter_json.id_str))

        return post

    def fetch(self, url):

        # Tweet id is extracted from given url
        tweet_id = [url[33:]]

        # Search will only yield one result and must take a list containing the tweet id
        tweets = self.api.statuses_lookup(tweet_id)

        for tweet in tweets:
            p = self._unmarshal(tweet)

        return p

    def fetch_posts(self, search_term, limit):

        post_set = set()

        tweets = self.api.search(
            q=search_term + " -filter:retweets AND -filter:replies",
            count=limit,
            lang="en")

        for tweet in tweets:
            post_set.add(self._unmarshal(tweet))

        return post_set
