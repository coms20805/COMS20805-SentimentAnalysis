import pickle

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.multiclass import OneVsRestClassifier
from sklearn.svm import SVC
from itertools import chain

TRAIN_SIZE = 4400
MAXIMUM_SENTIMENT_SCORE = 0.2
VECTORIZER_FILENAME = "vectorizer.pkl"
CLASSIFIER_FILENAME = "classifier.pkl"


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
            "You haven't saved the vectorized. Simply call train() on the classifier - it will save the model as well as the vectorizer")


def _save_model(spam_model):
    with open(CLASSIFIER_FILENAME, 'wb') as fd:
        pickle.dump(spam_model, fd)


def _save_vectroizer(vectorizer):
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
    _save_vectroizer(vectorizer)


def is_spam(post, key, verbose=False):
    model = _load_model()
    vectorizer = _load_vectorizer()
    content_vec = vectorizer.transform([post[key]])
    result = model.predict(content_vec)[0]
    probs = model.predict_proba(content_vec).tolist()
    predict_proba = max(chain(*probs))
    if verbose:
        print("LOG: " + post[key] + " is classified as <" + result.upper() + "> with prob =  " + str(predict_proba))
    return result == "spam"


def main():
    is_spam({"content": "udemy coupon for only 3 dollars", "score": 0}, "content", verbose=True)


if __name__ == '__main__':
    main()
