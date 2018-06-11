(ns okcampkid.util)

(defn mean
  [coll]
  (/ (reduce + coll) (count coll)))

(defn standard-deviation
  [coll]
  (let [avg (mean coll)
        squares (for [x coll]
                  (let [x-avg (- x avg)]
                    (* x-avg x-avg)))
        total (count coll)]
    (-> (/ (apply + squares)
           (- total 1))
        (Math/sqrt))))
