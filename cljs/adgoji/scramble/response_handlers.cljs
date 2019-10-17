(ns adgoji.scramble.response-handlers)

(def server-error-text "Server Error - Please Contact Adgoji !")

(defn response-handler
  [app-state {:strs [result]}]
  (swap! app-state dissoc :error)
  (swap! app-state assoc :scramble? result))

(defn error-handler
  [app-state {:keys [response]}]
  (swap! app-state dissoc :scramble?)
  (if response
    (swap! app-state assoc :error (response "reason"))
    (swap! app-state assoc :error server-error-text)))
