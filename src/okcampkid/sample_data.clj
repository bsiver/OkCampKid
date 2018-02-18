(ns okcampkid.sample-data)

(def ben-camper
  {:name "Betty"
   :age 28
   :instrument :drums
   :preferences []})

(def jess-camper
  {:name "Jess"
   :age 29
   :instrument :guitar
   :preferences []})

(def cupcake-camper
  {:name "Cupcake"
   :age 11
   :instrument :keys
   :preferences []})

(def paul-camper
  {:name "Paul"
   :age 7
   :instrument :bass
   :preferences []})

(def jane-drummer
  {:name "Jane"
   :age 7
   :instrument :drums
   :preferences []})

(def family-band {:campers [ben-camper jess-camper paul-camper cupcake-camper]})
(def all-campers [ben-camper jess-camper cupcake-camper paul-camper jane-drummer])
