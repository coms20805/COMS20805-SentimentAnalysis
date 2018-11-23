const API_URL = "http://localhost:8080/api";

const buildUrl = (path) => API_URL + path;

export class SearchService {

    async getResults(query) {
        return fetch(buildUrl("/search?query=" + query))
            .then(function (response) {
                return response.json();
            });
    }
}