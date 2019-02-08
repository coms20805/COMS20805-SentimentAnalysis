const API_URL = "https://es-app.herokuapp.com";

const buildUrl = (path) => API_URL + path;

export default class SearchService {

    static async getPosts(query) {
        return fetch(buildUrl("/search?literal_query=" + query))
            .then(function (response) {
                return response.json();
            });
    }

    static async getTimeSeries(query) {
        return fetch(buildUrl("/median?framework=" + query))
            .then(function(response) {
                return response.json();
            })
    }
}