(ns okcampkid.types
  (:require [schema.core :as s]))

(s/defschema Instrument (s/enum :guitar :drums :keys :bass))

(s/defschema Camper {:name s/Str
                     :instrument Instrument
                     :age s/Int
                     :preferences [s/Str]})

(s/defschema Band {:campers [Camper]})
