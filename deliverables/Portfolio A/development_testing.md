# Development Testing

One challenge we face is testing the accuracy of the sentiment scores given to posts. Since the process is inherently non-deterministic, we cannot predict a specific score for a test post. Instead, we plan to use sanity checking. We will give our sentiment analyser a very positive and very negative post and check that the sentiment scores are greater and less than 0.5 respectively. These tests can be put into unit tests, so when changes are made to the machine learning part of our application, we will at least be able to make sure it is sane.

The unit testing frameworks we will be using are *JUnit* for Java and *unittest* for Python.
