# Requirements

## System Stakeholders

### I. Internal Stakeholders
1.  **Client**: The software company Ghyston, represented by Mr Richard Meal
1. **Software Development Team**: The team will be managing, testing, updating and developing this Open source software for our client

**Software Development Team consists of:**
* Ben Price (Team Leader)
* Navya Zaveri
* Tharidu Jayaratne
* Martynas Noruišis

**Supporting Team**:
* **Resource Managers** at the University of Bristol will be providing us with funding if necessary
* **Our Mentor** Nuha Tumia, a third year student. Will be helping us with any technical difficulties, throughout the development stage.
* **Development Managers** Dr Daniel Schien and Dr Simon Lock will be supervising us throughout the stages of development and testing.

### II. External Stakeholders
1. **Developers**: The developers of the open source software that we will be generating sentiment scores for
1. **End users**: The people who will be using our software, once it has been fully developed
  1. **Consultants**: People who would use our software to provide expert advice professionally for advertising.
  1. **Non-profit groups**: Organisations that could use our software to promote a particular social cause or promoting for a shared point of view, where no part of the organisation's income is distributed to its members, directors, or officers.
  1. **The general public**: People who may be interested in finding the sentiment of a post.

## Use-case diagram
![Use-case diagram](/includes/use-case.png)

---

## Functional Requirements

1. The software shall be able to extract insights from social media posts related to technology.
1. It must display an overall rating of a query given by the user.
1. It must display a list of up to 10 related posts for every successful query.

## Non-functional Requirements
1. **Performance**: The software must be able to handle up to 36 requests per second
  * Rough estimate of requests per second (at peak times)
    * 4.7 million developers in Europe
    * Each making 20 requests
    * Let's assume they all access our website in the same 2 hour period
    * 4.7M * 20 / 365 / 2 / 60 / 60 = 36 requests per second
1. **Legislative**: The software shall be released under the appropriate open source licence
