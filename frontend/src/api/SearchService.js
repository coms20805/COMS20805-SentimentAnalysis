const API_URL = "http://132.145.54.186:2000/api";

const buildUrl = (path) => API_URL + path;

export default class SearchService {

    static async handleErrors(response) {
        if (!response.ok) {
            throw Error(response.status);
        }
        else {
            return response;
        }
    }

    static async getPosts(query) {
        return fetch(buildUrl("/search?query=" + query))
            .then(this.handleErrors)
            .then(response => {
                return response.json();
            })
            .catch(error => {
                throw Error(error.message);
            });
    }

    static async getTimeSeries(query) {
        return fetch(buildUrl("/median?query=" + query))
            .then(this.handleErrors)
            .then(response => {
                return response.json();
            })
            .catch(error => {
                throw Error(error.message);
            });
    }
}