(ns adgoji.scramble.app-test
  "The below tests checks if the middlewares are configured correctly."
  (:require [adgoji.scramble.app :refer [app]]
            [clojure.test :refer [testing is deftest]]
            [ring.mock.request :refer [json-body request]]))

(def headers {"Content-Type" "application/json"})

(deftest app-test
  (testing "middlewares are working correctly"
    (let [request (-> (request :post "/scramble")
                      (json-body {:string "abc" :word "ab"}))
          response (app request)]
      (is (= headers (:headers response))
          (= 200 (:status response))))))
