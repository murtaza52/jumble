(ns adgoji.scramble.routes
  (:require [adgoji.scramble.handlers :as handlers]
            [compojure.core :refer [defroutes POST GET]]
            [compojure.route :as route]
            [ring.util.response :as resp]))

(def not-found-body "<h1>Page not found</h1>")

(defroutes app-routes
  (POST "/scramble" [string word] (handlers/scramble? string word)))

(defroutes static-routes
  (GET "/" []
       (resp/content-type (resp/resource-response "index.html" {:root "public"}) "text/html"))
  (route/resources "/"))

(defroutes base-routes
  (route/not-found not-found-body))

(defroutes all-routes
  app-routes
  static-routes
  base-routes)
