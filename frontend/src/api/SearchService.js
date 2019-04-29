const API_URL = "https://www.techsentiment.com/api";

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

    static async handleErrors2(response2) {
        if (!response2.ok) {
            throw Error(response2.status);
        }
        else {
            return response2;
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
    static async getPosts2(query2) {
        return fetch(buildUrl("/search?query2=" + query2))
            .then(this.handleErrors2)
            .then(response2 => {
                return response2.json();
            })
            .catch(error => {
                throw Error(error.message);
            });
    }

    static async getTimeSeries2(query2) {
        return fetch(buildUrl("/median?query2=" + query2))
            .then(this.handleErrors2)
            .then(response2 => {
                return response2.json();
            })
            .catch(error => {
                throw Error(error.message);
            });
    }
}
