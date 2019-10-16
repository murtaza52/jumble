(ns adgoji.scramble.main
  (:require [adgoji.scramble.http :refer [start-server]])
  (:gen-class))

(defn -main
  []
  (start-server))
