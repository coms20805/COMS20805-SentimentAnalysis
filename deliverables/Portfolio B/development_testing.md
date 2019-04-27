# Development Testing

## Back end

### Elasticsearch API

We designed, implemented and tested a custom API that provided a simpler interface to the larger [elasticsearch library](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html). Concretely, it aimed to abstract away the complexities of following methods:

* Post Insertion
* Post Deletion 
* Post Searching strategies 
* Index Creation

We tested these methods aginst a suite of unit-tests, making sure that we tested edge-cases such as duplicate post insertions and deletion of non-existent posts. We also used and tested fuzzy matching to so that we could match againsts posts even if the seach query had a typo in it. 


### Python modules

### Scraper

To populate our elasticsearch instance, we wrote a cron-job that would go through a list of selected topics, fetch posts post up to a certain limit for each topic, then insert these posts into our instance. 

We used Twitter as our source of data, leveraging its API to collect tweets (which, in later iterations, would be run through a spam classifier).

We made a conscious design choice to not allow users to dynamically update our list of topics. The reason was two-fold:

* Limited Storage: We are using a [free-tier elasticsearch instance](https://bonsai.io/) that caps out a [certain capacity](https://bonsai.io/pricing). Thus, if we allowed dynamic updates then it would be hard to predict when we would run out of space. We would have to design new protocols that would limit the number of topics a given user can add to the list, and limit how many we can collect per new additional topic. This becomes every more challenging considering the next point.
* Spam: Our current list was deliberately chosen as we knew it would return posts that would more-or-less conform to our requirements. In our initial iteration, we did not build a spam classifier, and so allowing dynamic topic updates meant that we would have no idea whether it would introduce legitimate or spammy content. This was a not a tradeoff we were willing to make. (Even with a general spam classifier several iterations later, *accurately* classifying a post as spam/non-spam still remains a problem difficult enough that it deters us from allowing dynamic updates)  


### Server
We architected a REST API that allowed us to interact with our elasticsearch library, with the follwing goals in mind:
* Idempotency 
* Efficiency 
* Proper Error Handling 

Making it idempotent meant we need to ensure any given `GET` request would not change the state of the server. Thus, we made sure post insertions/deletions were `POST` requests, while searching was made a `GET` request. 

In terms of efficiency, we used a [Least-Recently-Used cache (LRU)](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)) to dynamically cache the results of a query and thus save computational load. An LRU was tailored to our domain since it would have a high "hit rate" on popular queries, whilst dynamically discarding posts that were not queried often enough. 

We also adhered to standard HTTP protocols, returning a `201` for every successful post creation, a `200` for successful deletion and search. Misuse of the API reulted in the API throwing a `4xx`, along with a json blob indicating what the domain-specific error could be, and how to fix it. 

We tested all of these factors against a local instance of elastic search, populated with a testing dataset. Idempotency was tested by make sure search results stay the same between multiple `GET` requests, the LRU cache was tested on the expected number of hits against the dataset, and the HTTP protocols were tested with every request invocation.  

### Spam Classifier 
An issue we faced in our initial iteration was that our posts were not quite representative of our task. Specifically, we found that a large chunk of our dataset was filled with spammy content, such as "free courses" and general tech advertisement.  This was not ideal since our goal was to extract sentiment from *opinionated posts* about technology.

To mitigate this, we built a spam-classifier, leveraging out-of-the-box libraries such as [sklearn](https://scikit-learn.org/), and training it on a general-purpose dataset. We tested the classifier against spammy content we were likely to encounter and iteratively tweaked its parameters for best results. 

We observed a noticeable improvement in the quality of posts, although it wasn't perfect. This is mostly likely because training dataset was more general-purpose than targeted to our domain. 

It's worth mentioning that we were able to iterate over different classification heuristics because of our design decision to make it easier to create and delete elasticsearch indices on the fly. This meant that if our spam classifer did not work well at all, we could easily delete the associated index and create a new one. 

## Front end
Our testing approach in our React.js front end application was mainly integration tests as there was not much logic delegated to the front end itself. We were mainly interested in whether we were correctly receiving data and that it was in the right format. We made sure that score values were valid and within the expected boundaries and that URLs and timestamps were in the correct format witht the help of regular expressions. This was repeated for all of our service functions that interact with the back end REST API.

The testing framework we used was Jest.js.

## Continuous Integration
As part of our development process we used continuous integration via Circle CI to prevent integration issues. Our integration tests are run sequentially to verify that all the components of our application interact with each other as expected. We used a bottom-up approach, where we started with the lowest level module of our application and added components into our integration tests one-by-one until all components have been integrated. This means that if an integration test fails it will be easy to see which component has caused the failure.

In addition, after every commit is made to the repository all the integration tests are run on CircleCI and we are immediately notified via email if any of the tests fail.

## Challenges
Testing is an underlying challenge when developing web applications. Different browsers, interfaces, security threats, and overall app integration were some of the issues we faced as developers. Since our web app was dependent on different browsers, consistent usability was crucial. Additionally, since the app is the brand (or a component thereof), any inconsistency within the user experience may translate into a negative experience, affecting the brand and its potential growth. When testing usability, we faced issues with scalability and interactivity. Since every user is different, it was necessary for us to utilize a representative group to test the application across different browsers, using different devices. To overcome these challenges...

## Testing frameworks
The unit testing frameworks we used were *JUnit* and *Hamcrest* for Java, *Mocha* for React.js and *unittest* for Python.
