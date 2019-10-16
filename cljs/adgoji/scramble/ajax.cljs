(ns adgoji.scramble.ajax
  (:require [ajax.core :refer [POST]]
            [adgoji.scramble.state :refer [app-state]]))

(def server-uri "http://localhost:3000/scramble")

(defn response-handler
  [{:strs [result]}]
  (swap! app-state dissoc :error)
  (swap! app-state assoc :scramble? result))

(defn error-handler
  [{:keys [response]}]
  (swap! app-state dissoc :scramble?)
  (if response
    (swap! app-state assoc :error (response "reason"))
    (swap! app-state assoc :error "Server Error - Please Contact Adgoji !")))

(defn scramble
  [string word]
  (println string word)
  (POST server-uri
        {:format :json
         :params {:string string
                  :word word}
         :handler response-handler
         :error-handler error-handler}))
