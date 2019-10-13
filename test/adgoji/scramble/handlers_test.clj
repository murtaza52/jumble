(ns adgoji.scramble.handlers-test
  (:require [adgoji.scramble.handlers :refer [scramble? validation-failure-text]]
            [clojure.test :refer [testing is deftest]]))

(def true-result {:status 200,
                  :headers {"Content-Type" "application/json"},
                  :body "{\"result\":true}"})

(def false-result {:status 200,
                   :headers {"Content-Type" "application/json"},
                   :body "{\"result\":false}"})

(def failure-result {:status 400,
                     :headers {"Content-Type" "application/json"},
                     :body "{\"reason\":\"Not a valid input - Input values should only contain lower case characters, without any numbers or special characters.\"}"})

(deftest app-test
  (testing "true response"
    (is (= true-result (scramble? "hello" "he"))))
  (testing "false response"
    (is (= false-result (scramble? "hello" "hi"))))
  (testing "validation failure"
    (is (= failure-result (scramble? "hello" ";")))))
