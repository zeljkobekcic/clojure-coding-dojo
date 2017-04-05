(ns diehard.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]
            [diehard.core :refer :all]))

;;; defining some variables for the testcases
(def single-cart {:a 1})
(def double-cart {:a 2 :b 2})
(def tripple-cart-0 {:a 1 :b 2 :c 2})
(def tripple-cart-1 {:a 2 :b 2 :c 2})
(def tripple-cart-2 {:a 3 :b 1 :c 1})
(def tripple-cart-3 {:a 3 :b 0 :c 3})
(def quad-cart-0 {:a 1 :b 1 :c 1 :d 1})
(def quad-cart-1 {:a 2 :b 2 :c 4 :d 3})
(def quad-cart-2 {:b 1 :c 3 :d 2 :e 1})
(def penta-cart-0 {:a 2 :b 3 :c 2 :d 2 :e 3})
(def penta-cart-1 {:a 1 :b 1 :c 3 :d 3 :e 3})

(deftest test-subtract-once-from-cart
  (is (= {:a 0} (subtract-once-from-cart single-cart)))
  (is (= {:a 1 :b 1} (subtract-once-from-cart double-cart)))
  (is (= {:a 0 :b 1 :c 1} (subtract-once-from-cart tripple-cart-0)))
  (is (= {:a 1 :b 1 :c 1} (subtract-once-from-cart tripple-cart-1)))
  (is (= {:a 2 :b 0 :c 0} (subtract-once-from-cart tripple-cart-2)))
  (is (= {:a 2 :b 0 :c 2} (subtract-once-from-cart tripple-cart-3)))
  (is (= {:a 0 :b 0 :c 0 :d 0} (subtract-once-from-cart quad-cart-0))) 
  (is (= {:a 1 :b 1 :c 3 :d 2} (subtract-once-from-cart quad-cart-1))) 
  (is (= {:b 0 :c 2 :d 1 :e 0} (subtract-once-from-cart quad-cart-2)))
  (is (= {:a 1 :b 2 :c 1 :d 1 :e 2} (subtract-once-from-cart penta-cart-0))) 
  (is (= {:a 0 :b 0 :c 2 :d 2 :e 2} (subtract-once-from-cart penta-cart-1))))

(deftest test-best-group
  (is (= 1 (best-group single-cart)))
  (is (= 2 (best-group double-cart)))
  (is (= 3 (best-group tripple-cart-0))) 
  (is (= 3 (best-group tripple-cart-1)))
  (is (= 3 (best-group tripple-cart-2)))
  (is (= 2 (best-group tripple-cart-3)))
  (is (= 4 (best-group quad-cart-0)))
  (is (= 4 (best-group quad-cart-1)))
  (is (= 4 (best-group quad-cart-2)))
  (is (= 5 (best-group penta-cart-0)))
  (is (= 5 (best-group penta-cart-1))))
