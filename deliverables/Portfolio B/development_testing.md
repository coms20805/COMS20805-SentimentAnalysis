# Development Testing


## Challenges
Testing is an underlying challenge when developing web applications. Different browsers, interfaces, security threats, and overall app integration were some of the issues we faced as developers. Since our web app was to be run on different browsers and environments, consistent usability was crucial. Additionally, since the app is the brand (or a component thereof), any inconsistency within the user experience may translate into a negative experience, affecting the brand and its potential growth. When testing usability, we faced issues with scalability and interactivity. Since every user is different, it was necessary for us to utilize a representative group to test the application across different browsers, using different devices. We overcame these challenges by testing in an agile manner, where we took an incremental approach to test features as they were developed. We believe this was a good strategy as it supported DevOps and continious testing. And continuous testing is important to improving product quality. The following were the  approaches we took for each end.

## Back end

### Elasticsearch API

We designed, implemented, and tested a custom API that provided a simpler interface to the larger [Elasticsearch library](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html). Concretely, it aimed to abstract away the complexities of following methods:

* Post Insertion
* Post Deletion 
* Post Searching Strategies 
* Index Creation

We tested these methods against a suite of unit tests, making sure that we tested edge cases such as inserting duplicate posts and deleting non-existent posts. We also used and tested fuzzy matching so that we could match against posts even if the search query had a typo in it. 


### Python modules

### Scraper

To populate our Elasticsearch instance, we wrote a cron job that would go through a list of selected topics, fetch posts up to a certain limit for each topic and then insert these posts into our instance. 

We used Twitter as our source of data, leveraging its API to collect tweets (which, in later iterations, would be run through a spam classifier).

We made a conscious design choice to not allow users to dynamically update our list of topics. The reason was two-fold:

* Limited Storage: We are using a [free-tier Elasticsearch instance](https://bonsai.io/) that is capped at a [certain capacity](https://bonsai.io/pricing). Thus, if we allowed dynamic updates, then it would be hard to predict when we would run out of space. We would have to design new protocols that would limit the number of topics that can be added, and limit how many posts we can collect per new topic. This becomes ever more challenging considering the next point.

* Spam: Our current list was deliberately chosen as we knew it would return posts that would more-or-less conform to our requirements. Given that we did not build a spam classifier initially, allowing dynamic topic updates would mean that we would have no idea whether our scraper was scraping legitimate or spammy content, until it was too late. This was a trade-off we were *not* willing to make. (Even with a general spam classifier several iterations later, *accurately* classifying a post as spam/non-spam still remains a problem difficult enough that it deters us from allowing dynamic updates)  


### Server
We architected a REST API that allowed us to interact with our Elasticsearch library, with the following goals in mind:
* Idempotency 
* Efficiency 
* Good error handling 

Making it idempotent meant we need to ensure any given `GET` request would not change the state of the server. Thus, we made sure post insertions/deletions were `POST` requests, while searching was made a `GET` request. 

In terms of efficiency, we used a [Least-Recently-Used cache (LRU)](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)) to dynamically cache the results of a query and thus save computational load. An LRU was tailored to our domain since it would have a high "hit rate" on popular queries, whilst dynamically discarding posts that were not queried often enough. 

We also adhered to standard HTTP protocols, returning a `201` for every successful post creation, a `200` for successful deletion and search. Misuse of the API results in a `4xx`, along with a JSON blob indicating what the domain-specific error could be, and how to fix it. 

We tested all of these factors against a local instance of Elasticsearch, populated with a testing dataset. Idempotency was tested by making sure search results stayed the same between multiple `GET` requests, the LRU cache was tested on the expected number of hits against the dataset, and the HTTP protocols were tested with every request invocation.  

### Spam Classifier 
An issue we faced in our initial iteration was that our posts were not quite representative of our task. Specifically, we found that a large chunk of our dataset was filled with spammy content, such as free courses and general tech advertisements. This was not ideal since our goal was to extract sentiment from *posts expressing an opinion* about technology.

To mitigate this, we built a spam classifier, leveraging out-of-the-box libraries such as [sklearn](https://scikit-learn.org/), and training it on a general-purpose dataset. We tested the classifier against spammy content we were likely to encounter and iteratively tweaked its parameters for best results. 

We observed a noticeable improvement in the quality of posts, although it was not perfect. This is mostly likely because the training dataset was more general purpose than targeted to our domain. 

It is worth mentioning that we were able to iterate over different classification heuristics because of our design decision to make it easier to create and delete Elasticsearch indices on the fly. This meant that if our spam classifier did not work well at all, we could easily delete the associated index and create a new one. 

---
## Front end
Our testing approach in our React.js front end application was mainly integration tests as there was not much logic delegated to the front end itself. We were mostly interested in whether we were correctly receiving data and that it was in the right format. We made sure that score values were valid and within the expected boundaries and that URLs and timestamps were in the correct format witht the help of regular expressions. This was repeated for all of our service functions that interact with the back end REST API.

The testing of components was mainly done visually by us, the developers, and our trial user group.

## Continuous Integration
As part of our development process we used continuous integration with CircleCI to prevent integration issues. Our integration tests are run sequentially to verify that all the components of our application interact with each other as expected. We used a bottom-up approach, where we started with the lowest level module of our application and added components into our integration tests one-by-one until all components have been integrated. This means that if an integration test fails it will be easy to see which component has caused the failure.

In addition, after every commit is made to the repository all the integration tests are run on CircleCI and we are immediately notified via email if any of the tests fail.


## Testing frameworks
The unit testing frameworks we used were *JUnit* and *Hamcrest* for Java, *unittest* for Python, and *Jests.js* for React.js.