(ns adgoji.scramble.utils
  (:require [ring.util.json-response :refer [json-response]]
            [ring.util.response :refer [status]]))

(defn json-response-with-status
  "Constructs a json response and adds the status-code."
  [data status-code]
  (status (json-response data) status-code))
