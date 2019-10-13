(ns adgoji.scramble.models-test
  (:require [adgoji.scramble.models :refer [scramble? char-occurrence includes-every-multiple-char? includes-every-char?]]
            [clojure.spec.test.alpha :as stest]
            [clojure.test :refer [testing is deftest]]))

;;;; generative test ;;;;

(def run-generative-tests #(stest/summarize-results (stest/check)))

;;;; unit tests ;;;;

(deftest char-occurence-test
  (testing "char-occurrence"
    (is (= 2 (char-occurrence "abac" "a")))
    (is (= 3 (char-occurrence "abaca" "a")))
    (is (= 1 (char-occurrence "bac" "a")))))

(deftest includes-every-multiple-char?-test
  (testing "checks only if a character occurring multiple times is included"
    (is (= true (includes-every-multiple-char? "abce" "jkh")))
    (is (= true (includes-every-multiple-char? "eeabece" "eeerie")))
    (is (= false (includes-every-multiple-char? "eabec" "eerie")))))

(deftest includes-every-char?-test
  (testing "checks if every character is included atleast once"
    (is (= true (includes-every-char? "abcde" "abc")))
    (is (= true (includes-every-char? "aabbcc" "aaabbbccc")))
    (is (= false (includes-every-char? "abcde" "cdf")))))

(deftest scramble?-test
  (testing "scramble?"
    (testing "should return true"
      (is (= true (scramble? "rekqodlw" "world")))
      (is (= true (scramble? "cedewaraaossoqqyt" "codewars"))))
    (testing "should return false"
      (is (= false (scramble? "katas" "steak"))))
    (testing "should return true if the same character is included multiple times"
      (is (= true (scramble? "aebecderi" "eerie"))))
    (testing "should return false if the same character is not included multiple times"
      (is (= false (scramble? "aebecdri" "eerie"))))))


(comment
  (run-generative-tests)
  (stest/check)
  (clojure.test/run-tests))
