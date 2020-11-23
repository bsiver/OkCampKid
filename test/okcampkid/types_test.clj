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

; band tests
(deftest is-valid-band-missing-drummer
  (testing "A band without a drummer should not be valid"
    (let [drummer-less-band {:campers (remove #(= (:instrument %) :drums) (:campers sd/family-band))}]
      (is (= false (is-valid-band drummer-less-band))))))

(deftest is-valid-band-with-one-of-each-instrument
  (testing "A band with one member on each instrument should be valid"
    (let [band sd/other-band]
      (is (= true (is-valid-band band))))))

(deftest is-valid-band-with-two-guitarists
  (testing "A band with two guitarists and one of every other instrument should be valid"
    (let [band {:campers [{:name "Jen" :age 10 :instrument :drums :preferences []}
                          {:name "Tina" :age 8 :instrument :guitar :preferences []}
                          {:name "Magdalena" :age 9 :instrument :guitar :preferences []}
                          {:name "Cynthia" :age 11 :instrument :bass :preferences []}]}]
      (is (= true (is-valid-band band))))))

; formation tests
(deftest is-valid-formation-with-all-distinct-campers
  (testing "A formation with all distinct campers should be valid"
    (let [band-one {:campers [{:name "Jen" :age 10 :instrument :drums :preferences []}
                              {:name "Tina" :age 8 :instrument :guitar :preferences []}
                              {:name "Magdalena" :age 9 :instrument :guitar :preferences []}
                              {:name "Cynthia" :age 11 :instrument :bass :preferences []}]}
          band-two {:campers [{:name "Berta" :age 10 :instrument :drums :preferences []}
                              {:name "Ursula" :age 8 :instrument :guitar :preferences []}
                              {:name "Rosario" :age 9 :instrument :guitar :preferences []}
                              {:name "Ifeoluwapo" :age 11 :instrument :bass :preferences []}]}]
      (is (= true (is-valid-formation [band-one band-two]))))))

(deftest is-valid-formation-with-repeated-camper
  (testing "A formation with one camper in multiple bands is not valid"
    (let [band-one {:campers [{:name "Jen" :age 10 :instrument :drums :preferences []}
                              {:name "Tina" :age 8 :instrument :guitar :preferences []}
                              {:name "Magdalena" :age 9 :instrument :guitar :preferences []}
                              {:name "Cynthia" :age 11 :instrument :bass :preferences []}]}
          band-two {:campers [{:name "Berta" :age 10 :instrument :drums :preferences []}
                              {:name "Tina" :age 8 :instrument :guitar :preferences []}
                              {:name "Rosario" :age 9 :instrument :guitar :preferences []}
                              {:name "Ifeoluwapo" :age 11 :instrument :bass :preferences []}]}]
      (is (= false (is-valid-formation [band-one band-two]))))))