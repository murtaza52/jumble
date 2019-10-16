(ns adgoji.scramble.http-test
  (:require [clojure.test :refer [testing is deftest use-fixtures]]
            [org.httpkit.client :as client]
            [adgoji.scramble.http :refer [start-server stop-server]]
            [cheshire.core :refer [generate-string]]))

(defn setup
  [tests]
  (start-server)
  (tests)
  (stop-server))

(use-fixtures :once setup)

(deftest http-server
  (testing "http server smoke test"
    (is (= 200 (:status @(client/post "http://localhost:3000/scramble"
                                      {:body (generate-string {:string "abc" :word "ab"})
                                       :headers {"Content-Type" "application/json"}}))))))
