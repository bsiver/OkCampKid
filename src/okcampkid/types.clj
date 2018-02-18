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
