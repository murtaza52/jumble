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

(deftest update-text-test
  (let [app-state (atom {:error "hi" :scramble? true})
        local-state (atom {:to-validate? false :string {:text "c" :valid? false}})
        k :string
        _ (l/update-text sample-event k local-state app-state)]
    (testing "removes response keys from app-state"
      (is (= {} @app-state)))
    (testing "updates the text in local state"
      (is (= "car" (get-in @local-state [k :text]))))
    (testing "stores the validation for the field"
      (is (= true (get-in @local-state [k :valid?]))))
    (testing "allows validation display for the field"
      (is (= true (get-in @local-state [k :to-validate?]))))))

(deftest get-validation-class-test
  (let [f (l/get-validation-class "valid" "invalid")]
    (testing "returns nil when the field is not be validated"
      (is (= nil (f :string
                    (atom {:string {:to-validate? false}})))))
    (testing "input is valid and field is to be validated"
      (is (= "valid" (f :string
                        (atom {:string {:to-validate? true :valid? true}})))))
    (testing "input is invalid and field is to be validated"
      (is (= "invalid" (f :string
                          (atom {:string {:to-validate? true :valid? false}})))))))

(deftest get-alert-box-class-test
  (testing "server error"
    (is (= "alert-danger" (l/get-alert-box-class (atom {:error "error"})))))
  (testing "server is able to scramble the word"
    (is (= "alert-success" (l/get-alert-box-class (atom {:scramble? true})))))
  (testing "server is not able to scramble the word"
    (is (= "alert-dark" (l/get-alert-box-class (atom {:scramble? false})))))
  (testing "initial state"
    (is (= "alert-info" (l/get-alert-box-class (atom {}))))))
