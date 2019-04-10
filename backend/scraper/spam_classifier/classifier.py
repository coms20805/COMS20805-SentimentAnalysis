import pickle

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.multiclass import OneVsRestClassifier
from sklearn.svm import SVC

TRAIN_SIZE = 4400
MAXIMUM_SENTIMENT_SCORE = 0.2
VECTORIZER_FILENAME = "vectorizer.pkl"
CLASSIFIER_FILENAME = "classifier.pkl"


def _load_model():
    with open(CLASSIFIER_FILENAME, 'rb') as fid:
        model = pickle.load(fid)
        return model


def _load_vectorizer():
    with open(VECTORIZER_FILENAME, 'rb') as fid:
        model = pickle.load(fid)
        return model


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
    print("saved classifier")
    _save_vectroizer(vectorizer)
    print("saved vectorizer")


# a conservative spam classifier
def is_spam(post, key):
    model = _load_model()
    vectorizer = _load_vectorizer()
    content_vec = vectorizer.transform([post[key]])
    result = model.predict(content_vec)[0]
    predict_proba = max(model.predict_proba(content_vec))
    post_sentiment = post["score"]
    print(result)
    print(predict_proba)
    return result == "spam" and post_sentiment < MAXIMUM_SENTIMENT_SCORE


def main():
    train()
    is_spam({"content": "udemy coupon for only 3 dollars", "score": 0}, "content")


if __name__ == '__main__':
    main()
