import pickle

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.multiclass import OneVsRestClassifier
from sklearn.svm import SVC
from itertools import chain
from enum import Enum

TRAIN_SIZE = 4400
MAXIMUM_SENTIMENT_SCORE = 0.2
VECTORIZER_FILENAME = "vectorizer.pkl"
CLASSIFIER_FILENAME = "classifier.pkl"


class Result(Enum):
    SPAM = 1
    NOT_SPAM = 0


class PreTrainedClassifier:
    def __init__(self):
        self.model = _load_model()
        self.vectorizer = _load_vectorizer()

    def classify(self, post, key, verbose=False):
        if key not in post:
            raise ValueError(
                "%s doesn't exist in the post. It has to be a valid attribute of the post we want to run the "
                "spam classifier against", key)
        content_vec = self.vectorizer.transform([post[key]])
        result = self.model.predict(content_vec)[0]
        probs = self.model.predict_proba(content_vec).tolist()
        predict_proba = max(chain(*probs))
        if verbose:
            print("LOG: " + post[key] + " is classified as <" + result.upper() + "> with prob =  " + str(predict_proba))
        return Result.SPAM if result == "spam" else Result.NOT_SPAM


def _load_model():
    try:
        with open(CLASSIFIER_FILENAME, 'rb') as fid:
            model = pickle.load(fid)
            return model
    except FileNotFoundError:
        return Exception("You haven't trained the model. Call train() on the classifier")


def _load_vectorizer():
    try:
        with open(VECTORIZER_FILENAME, 'rb') as fid:
            model = pickle.load(fid)
            return model
    except FileNotFoundError:
        raise Exception(
            "You haven't saved the vectorizer. Simply call train() on the classifier - it will save the model as well as the vectorizer")


def _save_model(spam_model):
    with open(CLASSIFIER_FILENAME, 'wb') as fd:
        pickle.dump(spam_model, fd)


def _save_vectorizer(vectorizer):
    with open(VECTORIZER_FILENAME, 'wb') as fd:
        pickle.dump(vectorizer, fd)


def train():
    df = pd.read_csv("spam.csv", encoding='latin-1')
    classifier = OneVsRestClassifier(SVC(kernel='linear', probability=True))
    train_data = df[:TRAIN_SIZE]
    vectorizer = TfidfVectorizer()
    vectorizer.fit(train_data.v2)
    vectorize_text = vectorizer.transform(train_data.v2)
    classifier.fit(vectorize_text, train_data.v1)
    _save_model(classifier)
    _save_vectorizer(vectorizer)


def main():
    clf = PreTrainedClassifier()
    res = clf.classify({"content": "udemy coupon for only 3 dollars", "score": 0}, "content", verbose=True)


if __name__ == '__main__':
    main()
