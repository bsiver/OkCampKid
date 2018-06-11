(ns okcampkid.types
  (:require [clojure.math.combinatorics :as combo]
            [okcampkid.util :refer [standard-deviation]]
            [schema.core :as s]
            [schema.coerce :as coerce]))

(s/defschema Instrument (s/enum :guitar :drums :keys :bass))

(s/defschema Camper {:name s/Str
                     :instrument Instrument
                     :age s/Int
                     :preferences [s/Str]})

(def parse-camper (coerce/coercer Camper coerce/json-coercion-matcher))

(s/defschema Band {:campers [Camper]})
(def ^:const band-size 4)

;(def bassists
;  (filter #(= (:instrument %) :bass ) @Campers))
;
;(def guitarists
;  (filter #(= (:instrument %) :guitar ) @Campers))
;
;(def bassists
;  (filter #(= (:instrument %) :guitar ) @Campers))
;
;(def drummers
;  (filter #(= (:instrument %) :drums ) @Campers))

(s/defn is-valid-band
  [band :- Band]
  (let [
        ;band (:campers band)
        bass (filter #(= (:instrument %) :bass) band)
        guitar (filter #(= (:instrument %) :guitar) band)
        keys (filter #(= (:instrument %) :keys) band)
        drums (filter #(= (:instrument %) :drums) band)]
    (and (every? #(<=  1 (count %) 2) [bass guitar keys])
         (every? #(= (count %) 1) [drums]))))

(s/defn in-band
  [name :- s/Str
   band :- Band]
  (some #(= (:name %) name) band))

(s/defn is-valid-formation
  [bands :- [Band]]
  (let [distinct-campers (distinct (flatten bands))
        all-campers (flatten bands)]
       (= (count all-campers) (count distinct-campers))))

(s/defn all-formations
  [campers :- [Camper]]
  (let [all-bands (combo/combinations campers band-size)
        possible-formations (combo/combinations all-bands (int (/ (count campers) band-size)))]
    (filter is-valid-formation possible-formations)))

(s/defn rank-band
  [band :- Band]
  (let [age-stddev (standard-deviation (map :age band))
        age-rank (cond
                   (<= 1 age-stddev 2) 10
                   (<= 3 age-stddev 4) 8
                   (<= 5 7) 4
                   (<= 7 10) 2
                   :else 1)

        prefs (map :preferences (:campers band))]
    age-rank))

(s/defn score-formation
  [formation :- [s/Any]]
  (reduce + (map rank-band formation)))
