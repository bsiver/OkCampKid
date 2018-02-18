(ns okcampkid.types
  (:require [schema.core :as s]
            [schema.coerce :as coerce]))

(s/defschema Instrument (s/enum :guitar :drums :keys :bass))

(s/defschema Camper {:name s/Str
                     :instrument Instrument
                     :age s/Int
                     :preferences [s/Str]})

(def parse-camper (coerce/coercer Camper coerce/json-coercion-matcher))

(s/defschema Band {:campers [Camper]})

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
  (let [bass (filter #(= (:instrument %) :bass) band)
        guitar (filter #(= (:instrument %) :guitar) band)
        keys (filter #(= (:instrument %) :keys) band)
        drums (filter #(= (:instrument %) :drums) band)]
    (every? #(= (count %) 1) [bass guitar keys drums])))
