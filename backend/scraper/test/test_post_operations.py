import unittest

from post import Post


class TestPostMethods(unittest.TestCase):
    def test_post_duplicates(self):

        post_1 = Post(url="xyz", content="foo", timestamp=0)
        post_2 = Post(url="xyz", content="foo", timestamp=0)
        post_set = {post_1, post_2}
        self.assertEqual(len(post_set), 1)

    def test_post_immutability(self):
        post_1 = Post(url="xyz", content="foo", timestamp=0)
        try:
            post_1.score = 100
        except AttributeError as e:
            pass
        except Exception as e:
            self.fail('Unexpected exception raised:', e)
        else:
            self.fail('ExpectedException not raised')
