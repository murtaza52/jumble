(ns adgoji.scramble.handlers-test
  (:require [adgoji.scramble.handlers :refer [scramble? validation-failure-text]]
            [clojure.test :refer [testing is deftest]]))

(def true-result "{\"result\":true}")

(def false-result "{\"result\":false}")

(deftest app-test
  (testing "true response"
    (is (= true-result (:body (scramble? "hello" "he")))))
  (testing "false response"
    (is (= false-result (:body (scramble? "hello" "hi")))))
  (testing "validation failure"
    (is (= 400 (:status (scramble? "hello" ";"))))))
