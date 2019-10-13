(ns adgoji.scramble.handlers
  (:require [adgoji.scramble.models :as models]
            [adgoji.scramble.specs :as sspecs]
            [adgoji.scramble.utils :refer [json-response-with-status]]
            [clojure.spec.alpha :as s]))

(defn- allowed-input?
  [string]
  (s/valid? ::sspecs/allowed-str string))

(def validation-failure-text "Not a valid input - Input values should only contain lower case characters, without any numbers or special characters.")

(defn scramble?
  [string word]
  (if (every? allowed-input? [word string])
    (json-response-with-status {:result (models/scramble? string word)} 200)
    (json-response-with-status {:reason validation-failure-text} 400)))
