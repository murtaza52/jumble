(ns adgoji.scramble.main
  (:require [goog.dom :as gdom]
            [reagent.core :as r]
            [adgoji.scramble.html :refer [scramble-form]]
            [adgoji.scramble.state :refer [app-state]]))

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (r/render-component [scramble-form app-state] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

(defn main! []
  (mount-app-element))

(defn ^:after-load on-reload []
  (main!)
  (reset! app-state {}))
