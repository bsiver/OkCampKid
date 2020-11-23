(ns okcampkid.types-test
  (:require [clojure.test :refer :all]
            [okcampkid.types :refer :all]
            [okcampkid.sample-data :as sd]
            [schema.core :as sc]
            [schema.test :as st]))

(use-fixtures :once st/validate-schemas)

(deftest validate-camper-test
  (is sd/sam-camper (sc/validate Camper sd/sam-camper)))

(deftest validate-band-test
  (is sd/other-band (sc/validate Band sd/other-band)))

(deftest is-valid-band-missing-drummer
  (let [drummer-less-band {:campers (remove #(= (:instrument %) :drums) (:campers sd/family-band))}]
    (is (= false (okcampkid.types/is-valid-band drummer-less-band)))))