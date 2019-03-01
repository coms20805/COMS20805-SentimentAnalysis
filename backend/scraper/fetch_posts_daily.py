from twitter_scraper import TwitterScraper
import requests

TWITTER_TOPICS_PATH = "twitter_dataset.txt"
POST_COUNT = 1 #Number of posts from each topic to scrape
ENDPOINT = "https://es-app.herokuapp.com/insert"

def get_topics():
    topics = []
    with open(TWITTER_TOPICS_PATH) as f:
        lines = f.readlines()
        for topic in lines:
            print(topic.strip())
            topics.append(topic.strip())
    return topics

def run(limit):
    topics = get_topics()
    tw = TwitterScraper()
    for topic in topics:
        for post in tw.fetch_posts(topic, limit):
            requests.post(ENDPOINT, json={"post": post.to_dict()})

run(POST_COUNT)
