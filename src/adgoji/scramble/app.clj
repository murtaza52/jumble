(ns adgoji.scramble.app
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [adgoji.scramble.routes :refer [app-routes]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def app
  (wrap-defaults app-routes api-defaults))

(defn run-app
  []
  (run-jetty app {:port 3000}))
