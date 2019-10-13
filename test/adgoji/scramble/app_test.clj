(ns adgoji.scramble.app-test
  "The below test checks if the middlewares are configured correctly."
  (:require [adgoji.scramble.app :refer [app]]
            [clojure.test :refer [testing is deftest]]
            [ring.mock.request :refer [request]]))

(def response {:status 200,
               :headers {"Content-Type" "application/json"},
               :body "{\"result\":true}"})

(deftest app-test
  (testing "middlwares are working correctly"
    (is (= response (app (request :post "/scramble" {:string "abc" :word "ab"}))))))
