from twitter_scraper import TwitterScraper
import requests
import langdetect
import argparse

TWITTER_DATASET_PATH = "twitter_dataset.txt"
ES_ENDPOINT = "https://es-app.herokuapp.com/insert"
LIMIT = 40


def toJson(post):
    json_post = {}
    json_post["id"] = post.id
    json_post["content"] = post.content
    json_post["url"] = post.url
    json_post["score"] = post.score
    return {"post": json_post}


def get_topics():
    topics = []
    with open(TWITTER_DATASET_PATH) as f:
        lines = f.readlines()
        for topic in lines:
            print(topic.strip(), len(topic.strip()))
            topics.append(topic.strip())
    return topics


def main():
    topics = get_topics()
    tw = TwitterScraper()
    for topic in topics:
        for post in tw.fetch_posts(topic, LIMIT):
            try:
                if "en" in str(langdetect.detect_langs(post.content)):
                    json_post = toJson(post)
                    r = requests.post(ES_ENDPOINT, json=json_post)
                    print(r.status_code)
                    print(json_post)

            except langdetect.lang_detect_exception.LangDetectException:
                print("Weird post... " + post.content)


if __name__ == "__main__":
    execute = input(
        "Are you sure you want to execute this streamer (1/0)? Please have a look at the code first"
    )
    if execute == "1":
        main()
