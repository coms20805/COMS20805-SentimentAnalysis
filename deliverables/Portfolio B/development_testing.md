# Development Testing

## Back end

### Elasticsearch wrapper
### Elasticsearch instance
### Python module
### Scraper
Given that Machine Learning is non-deterministic, we cannot write unit tests to test our model. As such, we have decided *not* to build our own model but to use a popular out-of-the-box sentiment analysis library and manually test it on a collection of random posts representative of our task. If it performed well enough on such posts, we would use it as is. If it did not meet our expectations, we would either look at different libraries or tweak the parameters of the API model and determine what option best suits our needs. We will also use unit tests to make sure that newly scraped posts are hashed correctly, and that when new posts are added to our database they are not stored if that post already exists. We will have a unit test that attempts to add the same post to our database multiple times and check that the number of posts only increases by one.

### Server
We used unit tests to ensure that the REST API returns JSON in the correct format.

## Front end
We also used unit tests to ensure our front end React.js components behave as intended. For instance, checking that clicking the 'search' button sends a query or checking that the results of a query are shown to the user in the correct format. In addition, we have tested to check if the posts contain valid values and the time series loads without error.



## Continuous Integration
As part of our development process we used continuous integration via Circle CI to prevent integration issues. Our integration tests are run sequentially to verify that all the components of our application interact with each other as expected. We used a bottom-up approach, where we started with the lowest level module of our application and added components into our integration tests one-by-one until all components have been integrated. This means that if an integration test fails it will be easy to see which component has caused the failure.

In addition, after every commit is made to the repository all the integration tests are run on CircleCI and we are immediately notified via email if any of the tests fail.

## Challenges
Testing is an underlying challenge when developing web applications. Different browsers, interfaces, security threats, and overall app integration were some of the issues we faced as developers. Since our web app was dependent on different browsers, consistent usability was crucial. Additionally, since the app is the brand (or a component thereof), any inconsistency within the user experience may translate into a negative experience, affecting the brand and its potential growth. When testing usability, we faced issues with scalability and interactivity. Since every user is different, it was necessary for us to utilize a representative group to test the application across different browsers, using different devices. To overcome these challenges...

## Testing frameworks
The unit testing frameworks we used were *JUnit* and *Hamcrest* for Java, *Mocha* for React.js and *unittest* for Python.
