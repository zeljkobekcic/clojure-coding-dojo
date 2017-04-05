(ns gol.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest] 
            [gol.core :refer :all]))

(deftest die-lonely 
  (is (= (rule 1 1) 0)))

(deftest die-overpopulation
  (is (= (rule 3 0) 1)))

(deftest live-with-two-three-neighbours
  (is (= (rule 2 1) 1))
  (is (= (rule 3 1) 1)))

(deftest revive-unsuccessful
  (is (= (rule 2 0) 0)))

(deftest revive-successful
  (is (= (rule 3 0) 1)))

(deftest count-no-neighbour
  (is (= (compute-neighbor-count blinker [3 2]) 0)))

(deftest count-one-neighbour
  (is (= (compute-neighbor-count blinker [1 0]) 1)))

(deftest count-three-neighbour 
  (is (= (compute-neighbor-count blinker [0 1]) 3)))
