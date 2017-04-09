(ns diehard.core
	(:require  [clojure.spec :as spec]))

(def sale {0 1.0, 
           1 1.0, 
           2 0.95, 
           3 0.9, 
           4 0.8, 
           5 0.75})

(defn best-group
  [cart]
  (count (filter pos? (vals cart))))

(defn subtract-once-from-cart
  [cart]  
    (reduce 
      (fn [new-map [key val]] 
        (assoc new-map key (if (> val 1 ) (dec val) 0)))
        {}
        cart))

(defn calculate-price
  [amount] 
  (* 8 amount (get sale amount)))

(defn calculate-best-sale-groups
  [cart]
  (loop [newCart cart 
         amount  '()]
    (if (some pos? (vals newCart)) 
      (recur 
        (subtract-once-from-cart newCart) 
        (conj amount (best-group newCart)))
      amount
      )))

(defn optimize-sale-groups
  [sale-groups]
  (let [amount5 (count (filter (partial = 5) sale-groups))
        amount3 (count (filter (partial = 3) sale-groups))
        amount4 (* 2 (min amount3 amount5))]
        (into  
          (into 
            (remove #(or (= 3 %) (= 5 %)) sale-groups)
            (repeat (Math/abs (- amount3 amount5)) 
                    (if (> amount5 amount3) 5 3)))
          (repeat amount4 4))))

(defn calculate-price-for-cart
  [cart]
  (reduce + (calculate-best-sale-groups cart)))
