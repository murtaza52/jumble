(ns adgoji.scramble.http
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [adgoji.scramble.app :refer [app]]))

(def port 3000)

(def server nil)

(defn start-server
  []
  (alter-var-root #'server
                  (constantly (run-jetty #'app
                                         {:port port :join? false}))))

(defn stop-server
  []
  (.stop server))
