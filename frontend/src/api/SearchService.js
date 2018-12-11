const API_URL = "http://132.145.54.186:8080/api";

const buildUrl = (path) => API_URL + path;

export class SearchService {

    static async getResults(query) {
        return fetch(buildUrl("/search?query=" + query))
            .then(function (response) {
                return response.json();
            });
    }
}