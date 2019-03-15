# Mark posts as either tech related (1) or non-tech related (0) and save to data.csv

from twitter_scraper import TwitterScraper

TWITTER_TOPICS_PATH = "twitter_dataset.txt"
POST_COUNT = 1 #Number of posts from each topic to scrape

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
            print(post.content)
            result = input('Tech?: ')
            with open('data.csv', 'a') as f:
            	f.write('"' + post.content + '",' + result + '\n')	

run(POST_COUNT)
