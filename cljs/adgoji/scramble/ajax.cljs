(ns adgoji.scramble.ajax
  (:require [ajax.core :refer [POST]]
            [adgoji.scramble.response-handlers :refer [response-handler error-handler]]))

(def server-uri "http://localhost:3000/scramble")

(defn scramble
  [string word app-state]
  (POST server-uri
        {:format :json
         :params {:string string
                  :word word}
         :handler (partial response-handler app-state)
         :error-handler (partial error-handler app-state)}))
