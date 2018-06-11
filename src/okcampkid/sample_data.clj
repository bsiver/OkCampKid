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

(def mary-camper
  {:name "Mary"
   :age 10
   :instrument :bass
   :preferences []})

(def patty-camper
  {:name "Patty"
   :age 10
   :instrument :keys
   :preferences []})

(def marley-camper
  {:name "Marley"
   :age 14
   :instrument :guitar
   :preferences []})

(def sam-camper
  {:name "Sam"
   :age 10
   :instrument :drums
   :preferences []})

(def family-band {:campers [ben-camper jess-camper paul-camper cupcake-camper]})
(def other-band {:campers [marley-camper mary-camper patty-camper sam-camper]})
(def all-campers [ben-camper jess-camper cupcake-camper paul-camper jane-drummer marley-camper mary-camper
                  patty-camper marley-camper sam-camper])
