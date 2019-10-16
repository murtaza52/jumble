(ns adgoji.scramble.app
  (:require [adgoji.scramble.routes :refer [all-routes]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-params]]
            [clojure.pprint :refer [pprint]]))

(defn wrap-debug
  [handler]
  (fn [request]
    (pprint request)
    (handler request)))

(def app
  (-> all-routes
      (wrap-defaults api-defaults)
      wrap-json-params))
