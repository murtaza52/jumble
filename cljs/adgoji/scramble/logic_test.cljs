(ns adgoji.scramble.logic-test
  (:require [cljs.test :refer (deftest is testing)]
            [adgoji.scramble.logic :as l]))

(deftest validate-test
  (testing "empty strings are not allowed"
    (is (= false (l/validate ""))))
  (testing "numbers are not allowed"
    (is (= false (l/validate "jkh2"))))
  (testing "upper case is not allowed"
    (is (= false (l/validate "Hakjl"))))
  (testing "special characters are not allowed"
    (is (= false (l/validate "jhj;"))))
  (testing "lower case chars are allowed"
    (is (= true (l/validate "jkhkjh")))))

(deftest can-submit?-test
  (testing "can submit when both inputs are valid"
    (is (= true (l/can-submit? (atom {:string {:valid? true}
                                      :word {:valid? true}})))))
  (testing "can not submit when either input in invalid"
    (is (= false (l/can-submit? (atom {:string {:valid? false}
                                       :word {:valid? true}}))))
    (is (= false (l/can-submit? (atom {:string {:valid? true}
                                       :word {:valid? false}}))))))

(def sample-event (clj->js {"target" {"value" "car"}}))

