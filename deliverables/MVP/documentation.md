# Minimum Viable Product

Our product is a tool that shows which open source projects hosted on GitHub are the most popular, based on sentiment analysis of social media (Twitter, Reddit, etc).

Link to our MVP:
http://132.145.54.186:3000/

Our MVP successfully accomplishes both of our primary use case goals, which are:

### "User – submit query and receive result"
The user can use the website to get a result for their query. The flow goes as follows:
1. Submit query
1. Run query through the Elasticsearch engine
1. Match the query to posts in database
1. Display the result to the user in the right format

![Screenshot with query "javascript"](images/query_test-javascript.png)
*Screenshot with query "javascript"*

### "ML module: scrape for posts and populate the database"
Our system can use the Twitter API to get posts, and then assess the sentiment of them.
1. Run instances of the different scrapers
1. Determine the sentiment values of the scraped posts
1. Push resulting objects to the Elasticsearch database

## To improve
Things that we will improve in the future:
- Build up a better data set
- Make our sentiment analyser more accurate
- Improve our UI
- Include a timestamp field
- Include a graph for historical sentiment
- Have faster loading times
- Add other APIs
