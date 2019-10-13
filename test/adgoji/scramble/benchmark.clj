(ns adgoji.scramble.benchmark
  (:require [adgoji.scramble.models :refer :all]
            [criterium.core :refer [quick-bench bench]]))

(defn gen-data
  [n]
  (apply str
         (take n
               (cycle ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m"]))))

(comment 

  (quick-bench (scramble? (gen-data 20) (gen-data 10)))

  ;; Execution time mean : 103.757808 µs

  (quick-bench (scramble? (gen-data 100) (gen-data 50)))

  ;; Execution time mean : 498.444407 µs

  (quick-bench (scramble? (gen-data 200) (gen-data 100)))

  ;; Execution time mean : 621.787674 µs

  (quick-bench (scramble? (gen-data 2000) (gen-data 1000)))

  ;; Execution time mean : 6.348356 ms

  (quick-bench (scramble? (gen-data 20000) (gen-data 10000)))

  ;; Execution time mean : 68.462122 ms


;;;; includes-every-char? ;;;;

  (bench (includes-every-char? (gen-data 2000) (gen-data 1000)))

  ;; Execution time mean : 3.660976 ms

  (bench (includes-every-char? (gen-data 20000) (gen-data 10000)))

  ;; Execution time mean : 36.451856 ms

  
;;;; includes-every-char? ;;;;

  (bench (includes-every-multiple-char? (gen-data 2000) (gen-data 1000)))

  ;; Execution time mean : 4.067609 ms

  (bench (includes-every-multiple-char? (gen-data 20000) (gen-data 10000))))

;; Execution time mean : 42.896140 ms
