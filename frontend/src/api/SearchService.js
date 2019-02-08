const API_URL = "http://localhost:8080/api";

const buildUrl = (path) => API_URL + path;

export default class SearchService {

    static async getPosts(query) {
        return fetch(buildUrl("/search?query=" + query))
            .then(function (response) {
                return response.json();
            });
    }

    static async getTimeSeries(query) {
        return {
            dates: ["1 Feb", "2 Feb", "3 Feb", "4 Feb", "5 Feb", "6 Feb"],
            scores: [0.1, 0.2, 0.15, 0.4, 0.7, 0.2],
            posts: ["Java is great",
                    "Java is alright",
                    "It's a love-hate relationship, Java and me",
                    "Kotlin is way better than Java",
                    "Java is the best",
                    "I tolerate Java"]
        }
        // return fetch(buildUrl("/series?query=" + query))
        //     .then(function (response) {
        //         return response.json();
        //     });
    }
}