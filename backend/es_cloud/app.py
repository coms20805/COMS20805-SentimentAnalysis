from elasticsearch import Elasticsearch
from elasticsearch_dsl import Search
from flask import Flask, json, request

app = Flask(__name__)

app.config.from_object("config.ProductionConfig")


client = Elasticsearch(
    app.config.get("ENDPOINT"),
    http_auth=(app.config.get("ES_USERNAME"), app.config.get("ES_PASSWORD")))

s = Search().using(client).query("match", title="python")
response = s.execute()


@app.route("/", methods=["GET"])
def index():
    """
    """
    return json.dumps({"result": "success"}), 200


@app.route("/query", methods=["GET"])
def find_posts():
    q = request.args.get("literal_query")
    l = request.args.get("limit")
    s = request.args.get("strategy")
    if not q or not l or not s:
        return json.dumps({"result": "invalid query parameters"}), 400
    return json.dumps({"result": "success"}), 200


@app.route("/repopulate", methods=["POST"])
def populate_es_database():
    """
    delete all existing documents and
    repopulate them from the json file

    """
    # check if the post request has the file part
    if "file" not in request.files:
        return json.dumps({"result": "please send a json file"}), 400
    file = request.files["file"]
    print(file)
    return "ok"


if __name__ == "__main__":
    app.run()
