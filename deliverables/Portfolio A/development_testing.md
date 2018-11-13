# Development Testing

## Back end

With respect to the back end, we will use unit tests to ensure that the REST API is idempotent and that it returns JSON in the correct format. We will also use unit tests to make sure that new posts are hashed correctly, and that when new posts are added to our database, they are not stored if that post already exists. We will have a unit test that attempts to add the same post to our database multiple times, and check that the number of posts only increases by one.

We will use integration testing to verify that the components of our application interact with each other as expected. We will use a bottom-up approach, where we start with the lowest level module of our application and add components into our integration tests one-by-one until all components have been integrated. This means that if an integration test fails it will be easy to see which component has caused the failure.

## Front end

We will also use unit tests to ensure our front-end React components behave as intended. For instance, checking that clicking the ‘search’ button sends a query, and checking that the results of a query are shown to the user in the correct format.


## Sentiment Analysis
Given that Machine Learning is non-deterministic, we cannot write unit-tests to test our model. As such, we have decided *not* to build our own model but to use a popular out-of-the-box sentiment analysis library, and manually test it on a collection of random posts representative of our task. If it performs well enough on such posts, we use it as is. If it doesn't meet our expectations, we either look at different libraries or tweak the parameters of the API model and determine what option best suits our needs.


## Challenges

One challenge we face is testing the machine learning part of our application. It will be difficult to test the accuracy of the sentiment scores given to posts, because the process is inherently non-deterministic, which means we cannot predict a specific score for a test post. In order to overcome this, we plan to use sanity checking. We will give our sentiment analyser a very positive and a very negative post and check that the sentiment scores are greater and less than 0.5 respectively. These tests can be put into unit tests, so when changes are made to the machine learning part of our application, we will at least be able to make sure it is sane.

## Testing frameworks

The unit testing frameworks we will be using are *JUnit* for Java, *Mocha* for React and *unittest* for Python.
