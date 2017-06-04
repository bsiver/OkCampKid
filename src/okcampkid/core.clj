(ns okcampkid.core
  (:gen-class))

(def menu-banner-text (slurp (clojure.java.io/resource "menubanner.txt")))

(defn -main
  [& args]
  (println menu-banner-text)
  (println "Welcome to OkCampKid!\n"))
