(ns diehard.core
	(:require  [clojure.spec :as spec]))

(defn best-group
  [cart]
  (count (filter #(not (zero? %)) (map cart (keys cart)))))

(defn subtract-once-from-cart
  [cart]  
    (reduce 
      (fn [new-map [key val]] 
        (assoc new-map key (if (> val 1 ) (dec val) 0)))
        {}
        cart))

(defn calculate-price
  [amount sale] 
  (* (* 8 amount) sale))

(defn calculate-best-sale-groups
  [cart]
  (let [newCart (subtract-once-from-cart cart)
        amount (best-group cart)
        price (calculate-price amount (- 1 (* amount 0.05)))]
      (if (= newCart cart) 
        '() 
        (conj (calculate-best-sale-groups newCart) price ))))

(defn calculate-price-for-cart
  [cart]
  (reduce + (calculate-best-sale-groups cart)))
