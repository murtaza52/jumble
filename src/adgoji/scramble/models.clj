(ns adgoji.scramble.models
  (:require [clojure.set :as cs]
            [adgoji.scramble.specs :as sspecs]
            [clojure.spec.alpha :as s]))

(defn char-occurrence
  "Returns the number of occurrence for the given `character` in the given `string`.
  Both `character` and `string` are of string type."
  [string character]
  {:pre [(re-matches #"[a-z]" character)]}
  (count
   (re-seq (re-pattern character)
           string)))

(s/fdef char-occurrence
  :args (s/cat :string ::sspecs/allowed-str :character ::sspecs/allowed-char)
  :ret integer?)

(defn includes-every-multiple-char?
  "Checks that any character which appears multiple times in `word`, also appears at-least that many times in the `string`."
  [string word]
  (every?
   (fn [[character frequency]]
     (>= (char-occurrence string (str character))
         frequency))
   (filter (fn [[_ v]]
             (> v 1))
           (frequencies word))))

(s/fdef includes-every-multiple-char?
  :args (s/cat :string ::sspecs/allowed-str :word ::sspecs/allowed-str)
  :ret boolean?)

(defn includes-every-char?
  [string word]
  (cs/subset? (set word) (set string)))

(s/fdef includes-every-char?
  :args (s/cat :string ::sspecs/allowed-str :word ::sspecs/allowed-str)
  :ret boolean?)

(defn scramble?
  [string word]
  (and (includes-every-char? string word) (includes-every-multiple-char? string word)))

(s/fdef scramble?
  :args (s/cat :string ::sspecs/allowed-str :word ::sspecs/allowed-str)
  :ret boolean?)
