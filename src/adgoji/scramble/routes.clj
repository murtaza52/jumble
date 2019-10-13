(ns adgoji.scramble.routes
  (:require [adgoji.scramble.handlers :as handlers]
            [compojure.core :refer [defroutes POST]]
            [compojure.route :as route]))

(def not-found-body "<h1>Page not found</h1>")

(defroutes app-routes
  (POST "/scramble" [string word] (handlers/scramble? string word))
  (route/not-found not-found-body))
