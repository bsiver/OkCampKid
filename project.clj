(defproject okcampkid "0.1.0-SNAPSHOT"
  :description "A simple utility for automating the band formation process of Girls Rock!"
  :url "https://github.com/bsiver/OkCampKid"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot okcampkid.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
