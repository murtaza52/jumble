(ns adgoji.scramble.response-handlers-test
  (:require [adgoji.scramble.response-handlers :as h]
            [cljs.test :refer (deftest is testing)]))

(deftest response-handler-test
  (let [app-state (atom {:error "error" :scramble? false})
        _ (h/response-handler app-state {"result" true})]
    (testing "there is no error key"
      (is (= nil (:error @app-state))))
    (testing "scramble? has the result value"
      (is (= true (:scramble? @app-state))))))

(deftest error-handler-test
  (let [app-state (atom {:error "prev error" :scramble? true})
        error-reason "validation error"
        _ (h/error-handler app-state {:response {"reason" error-reason}})]
    (testing "there is no scramble? key"
      (is (= nil (:scramble? @app-state))))
    (testing "error key has the correct value"
      (is (= error-reason (:error @app-state))))))

(deftest error-handler-server-error-test
  (let [app-state (atom {:error "prev error" :scramble? true})
        _ (h/error-handler app-state {})]
    (testing "error key has the correct value"
      (is (= h/server-error-text (:error @app-state))))))
