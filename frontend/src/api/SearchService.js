const API_URL = "http://132.145.54.186:2000/api";

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