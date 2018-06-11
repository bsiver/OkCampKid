(ns okcampkid.core
  (:require [clojure.math.combinatorics :as combo]
            [okcampkid.sample-data :as sd]
            [okcampkid.types :refer [Band
                                     band-size
                                     Camper
                                     parse-camper
                                     rank-band]]
            [schema.core :as s])
  (:gen-class))

(def menu-banner-text (slurp (clojure.java.io/resource "menubanner.txt")))

(defn clear-screen []
  (print "\u001b[2J")
  (print "\u001B[0;0f"))

(def Campers (atom []))

(s/defn add-camper
  [camper :- Camper]
  (swap! Campers conj camper))

(s/defn prompt-for-camper :- Camper
  []
  (print "Enter the camper's name: ")
  (flush)
  (let [name (read-line)]
    (print (format "Enter %s's age: " name))
    (flush)
    (let [age (Integer/parseInt (read-line))]
      (print (format "What instrument does %s play? " name))
      (flush)
      (let [instrument (read-line)
            camper (s/validate Camper
                               (parse-camper {:name name :age age :instrument instrument :preferences []}))]
        (add-camper camper)))))


(defn list-campers
  []
  (prn @Campers))

(defn suggest-formations
  []
  (let [formations (okcampkid.types/all-formations @Campers)]
    (doseq [ [i formation] (map-indexed vector formations)]
      (let [band {:campers formation}]
        (println (format "Band %s" i))
        (println "----------------------------\n")
        (println "Ranking: " (rank-band band))
        (doseq [{member :name instrument :instrument} formation]
          (println (format "%s on %s" member (-> instrument name))))))))


(defn input-repl
  []
  (loop []
    (println "\n\nPlease make a selection")
    (println "----------------------------\n")
    (println "1) Add a new camper")
    (println "2) View current campers")
    (println "3) Edit a camper")
    (println "4) Suggest band formations")
    (println "5) Exit")
    (println "----------------------------\n")
    (println "6) Load test data")
    (print "Your selection: ")
    (flush)
    (let [choice (read-line)
          result (cond
                  (= choice "1") (prompt-for-camper)
                  (= choice "2") (list-campers)
                  (= choice "3") "edit not implemented"
                  (= choice "4") (suggest-formations)
                  (= choice "5") "Bye!"
                  (= choice "6") (map add-camper sd/all-campers)
                  :else "invalid choice")]
         (prn result)
      (when (not= choice "5")  (recur)))))

(defn -main
  [& args]
  (println menu-banner-text)
  (println "Welcome to OkCampKid!\n\n")
  (input-repl))

(comment
  (require '[okcampkid.sample-data :as sd])
  (map add-camper sd/all-campers))
