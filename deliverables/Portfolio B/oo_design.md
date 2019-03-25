# OO Design & UML
## Architecture
![High-level architecture diagram](includes/high-level.png)

The overall structure of the application will be as follows:
1. **Front end**: receives input from the user, communicates with server via a Rest API to receive a result and outputs the result to the user
1. **Server**: stores a database of posts with pre-calculated sentiment values, handles requests from the front end, gets new posts from the ML module
1. **ML**: Natural Language Processing algorithm coupled with various web scrapers – procures new posts and assigns them with sentiment scores, sends them to the Server module for storage

## Static UML example
![Class diagram](includes/class-diagram.png)

This is a sample of a class diagram for a class within our application. The Post class is an important one in our system as Post objects will form the basis for our Elasticsearch database. This diagram illustrates the structure of a Post object and its relationship with the scrapers.

## Dynamic UML example
![Sequence diagram](includes/sequence-diagram.png)

This is a sample of a sequence diagram for the goal of getting the average sentiment for some query. This goal is the most important one in our application and the diagram visualises almost the entire working of the application. This diagram helped us to decide on the sequence and order of events that need to occur to accomplish the goal – getting a rating score for a given query.
