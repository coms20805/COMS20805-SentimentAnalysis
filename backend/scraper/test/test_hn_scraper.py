import unittest
from hacker_news_scraper import HnScraper
from post import Post


class TestHnMethods(unittest.TestCase):
    def setUp(self):
        self.hn = HnScraper()

    def test_unmarshal(self):
        """
        checks if we're correctly parsing the expected  json object
        """
        test_json = {"url": "blah", "title": "foo", "id": 123}
        post = self.hn._unmarshal(test_json)
        self.assertEqual(post, Post(url="blah", content="foo", score=0))


if __name__ == "__main__":
    unittest.main()
