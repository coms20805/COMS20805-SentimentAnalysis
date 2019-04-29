import re

import langdetect as langdetect
from fuzzywuzzy import fuzz

from spam_classifier.classifier import Result
from twitter_scraper import TwitterScraper
import requests
import argparse

TWITTER_TOPICS_PATH = "twitter_dataset.txt"
ES_ENDPOINT = "https://es-app.herokuapp.com/insert"
from spam_classifier import PreTrainedClassifier

clf = PreTrainedClassifier()


def almost_same(s1, s2):
    if fuzz.ratio(s1, s2) > 70:
        return True
    return False


def almost_similar_to_existing_set(tweet_set, candidate):
    for tweet in tweet_set:
        if almost_same(tweet, candidate):
            print("too close")
            print(tweet + "----" + candidate)
            return True
    return False


def to_dict(post):
    json_post = {"content": post.content, "url": post.url, "score": post.score,
                 "timestamp": post.timestamp}
    return {"post": json_post}


def get_topics():
    topics = []
    with open(TWITTER_TOPICS_PATH) as f:
        lines = f.readlines()
        for topic in lines:
            print(topic.strip())
            topics.append(topic.strip())
    return topics


def run(production, limit, verbose):
    topics = get_topics()
    twitter = TwitterScraper()
    tweet_set = set()
    for topic in topics:
        for post in twitter.fetch_posts(topic, limit):
            if clf.classify(post.to_dict(), key="content") == Result.SPAM or post.to_dict()["score"] == 0:
                continue
            raw_tweet = re.sub(r'http\S+', '', post.content).strip()
            if almost_similar_to_existing_set(tweet_set, raw_tweet):
                continue
            tweet_set.add(raw_tweet)
            if production:
                r = requests.post(ES_ENDPOINT, json={"post": post.to_dict()})  # this is the json format
                print(r.status_code)
            if verbose:
                print(post)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--limit", type=int, required=False, help="how many tweets to scrape per topic?")
    parser.add_argument('--production', default=False, type=lambda x: (str(x).lower() == 'true'),
                        help="run on production instance?--")
    parser.add_argument("--verbose", type=bool, required=False, help="log each post on console when scraping",
                        default=True)

    args = parser.parse_args()
    run(args.production, args.limit, args.verbose)
