(ns adgoji.scramble.specs
  (:require [clojure.spec.alpha :as s]
            [miner.strgen :as sg]))


(s/def ::allowed-str (let [re #"[a-z]+"]
                       (s/spec (s/and string? #(re-matches re %))
                               :gen  #(sg/string-generator re))))

(s/def ::allowed-char (let [re #"[a-z]"]
                        (s/spec (s/and string? #(re-matches re (str %)))
                                :gen #(sg/string-generator re))))

(comment
  (s/exercise ::allowed-str)
  (s/exercise ::allowed-char))
