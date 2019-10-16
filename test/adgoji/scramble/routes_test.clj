(ns adgoji.scramble.routes-test
  (:require [adgoji.scramble.routes :refer [all-routes not-found-body]]
            [clojure.test :refer [testing is deftest]]))

(def scramble-response {:status 200,
               :headers {"Content-Type" "application/json"},
               :body "{\"result\":false}"})

(def not-found-response {:status 404,
                         :headers {"Content-Type" "text/html; charset=utf-8"},
                         :body not-found-body})

(deftest app-routes-test
  (testing "scramble route is matched"
    (is (= scramble-response (all-routes {:request-method :post :uri "/scramble" :params {:string "hello" :word "hi"}}))))
  (testing "incorrect route returns 404"
    (is (= not-found-response (all-routes {:request-method :post :uri "/scramble1" :params {:string "hello" :word "he"}}))))
  (testing "index.html is returned on /"
    (is (= "index.html" (-> {:request-method :get :uri "/"}
                            all-routes
                            :body
                            .toString
                            (clojure.string/split #"/")
                            last)))))
