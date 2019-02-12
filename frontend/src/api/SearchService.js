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
        return fetch(buildUrl("/median?query=" + query))
            .then(function(response) {
                return response.json();
            })
    }
}