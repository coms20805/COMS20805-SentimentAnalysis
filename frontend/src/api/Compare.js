const API_URL = "https://es-app.herokuapp.com";

const buildUrl = (path) => API_URL + path;

export default class Compare {

    static async getTimeSeries(query2) {
        return fetch(buildUrl("/median?framework=" + query2))
            .then(function(response) {
                return response.json();
            })
    }
}
