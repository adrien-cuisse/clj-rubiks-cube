(defproject rubiks-cube "0.1.0-SNAPSHOT"
  :description "3x3 rubik's cube"
  :url "https://github.com/adrien-cuisse/clj-rubiks-cube"
  :license {:name "Apache License 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :main rubiks-cube.core
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"])
