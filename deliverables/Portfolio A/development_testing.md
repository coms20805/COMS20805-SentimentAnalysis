# Development Testing

With respect to the back end, we will use unit tests to ensure that the REST API is idempotent and that it returns JSON in the correct format. We will also use unit tests to make sure that when new posts are added to our database, they are not stored if that post already exists. We will have a unit test that attempts to add the same post to our database multiple times, and check that the number of posts only increase by one.

One challenge we face is testing the accuracy of the sentiment scores given to posts. Since the process is inherently non-deterministic, we cannot predict a specific score for a test post. In order to overcome this, we plan to use sanity checking. We will give our sentiment analyser a very positive and very negative post and check that the sentiment scores are greater and less than 0.5 respectively. These tests can be put into unit tests, so when changes are made to the machine learning part of our application, we will at least be able to make sure it is sane.

The unit testing frameworks we will be using are *JUnit* for Java and *unittest* for Python.
