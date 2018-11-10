# Requirements

## System Stakeholders

### I. Internal Stakeholders
1.  **Client**: The software company Ghyston, represented by Mr Richard Meal
2. **Software Development Team**: The team will be managing, testing, updating and developing this Open source software for our client.

**Software Development Team consists of:**
* Ben Price (Team Leader)
* Navya Zaveri
* Tharidu Jayaratne
* Martynas Noruišis

3. **Supporting Team**:
* **Resource Managers** at the University of Bristol will be providing us with funding if necessary
* **Our Mentor** Nuha Tumia, a third year student. Will be helping us with any technical difficulties, throughout the development stage.
* **Development Managers** Dr Daniel Schien and Dr Simon Lock will be supervising us throughout the stages of development and testing.

### II. External Stakeholders
1. **Developers**: The developers of the open source software that we will be generating sentiment scores for.
2. **End users**: The people who will be using our software, once it has been fully developed.
  3. **Consultants**: People who would use our software to provide expert advice professionally for advertising.
  4. **Non-profit groups**: Organisations that could use our software to promote a particular social cause or promoting for a shared point of view, where no part of the organisation's income is distributed to its members, directors, or officers.
  5. **The general public**: People who may be interested in finding the sentiment of a post.

## Use-case diagram
![Use-case diagram](includes/Newusecase.xml)

---

## Functional Requirements

1. The software will be able to extract insights from social media posts related to technology.
2. It must display an overall rating of a query given by the user.
3. It must display a list of up to 10 related posts for every successful query. If there are fewer than 10 posts, the front end will display all posts related to its respected query.
4. When a malformed query is provided *fuzzy matching* will take place.
* The algorithm will find correspondences between segments of a text and entries in our database of previous translations.
5. Our rest API should be *idempotent*.
  * Our algorithm can be applied multiple times without affecting the result beyond initial application.
6. Database will be updated at least once a week.
* The development team will be responsible for maintaining and updating the system, with new posts.
7. The application uses the Elastic search engine to

## Non-functional Requirements
1. **Performance**: The software shall be able to handle up to 36 requests per second
  * Rough estimate of requests per second (at peak times)
    * 4.7 million developers in Europe
    * Each making 20 requests
    * Let's assume they all access our website in the same 2 hour period
    * 4.7M * 20 / 365 / 2 / 60 / 60 = 36 requests per second
2. **Legislative**: The software shall be released under the MIT licence.
3. **Usability**: We aim to make our software as usable as possible by testing on its:
*  ***Time-on-task***
* ***Success-rate***
*  ***Efficiency***: This will be tested through unit and system integration tests, to ensure our product delivers the result as fast as possible.
 * ***Memorability***: When a user returns to the application after a period of not using it, the user shall remember enough to use it effectively the next time.

  * We will be testing this by showing our product to a small sample of users and observing how they interact with it. Users will be asked for feedback via a questionnaire*.
    * Users will rate the usability on a scale 1-10. The average score shall be >7.
4. **Ethical**: To ensure that we are not biased against or towards particular pieces of software, we have to make sure that we are scraping posts expressing both negative and positive opinions for all queries.
5. **Front-End**: We aim to make our Interface as user friendly as possible by:
* Keeping a minimalistic look to
7. **Security**: We shall ensure that no user can alter our database through the queries they submit. This will be tested through our unit testing.
8.
