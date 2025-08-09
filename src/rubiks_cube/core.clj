(ns rubiks-cube.core
  (:require [clojure.set :refer [rename-keys]]))

(def blue \b)
(def green \g)
(def orange \o)
(def red \r)
(def white \w)
(def yellow \y)

(def ^:private front-face-key :front-face)
(def ^:private back-face-key :back-face)
(def ^:private left-face-key :left-face)
(def ^:private right-face-key :right-face)
(def ^:private top-face-key :top-face)
(def ^:private bottom-face-key :bottom-face)

(defn- ^:no-doc create-face
  "Creates a face with the specified `color`"
  [color]
  (vec (repeat 9 color)))

(defn color
  "Returns the color of a `face`"
  [face]
  (nth face 4)) ; center cell can't move

(defn- ^:no-doc face
  "Returns a `face` of the `cube` from its `key`"
  [cube key]
  (key cube))

(defn front-face
  "Returns the front face of the `cube`"
  [cube]
  (face cube front-face-key))

(defn left-face
  "Returns the left face of the `cube`"
  [cube]
  (face cube left-face-key))

(defn right-face
  "Returns the right face of the `cube`"
  [cube]
  (face cube right-face-key))

(defn top-face
  "Returns the top face of the `cube`"
  [cube]
  (face cube top-face-key))

(defn bottom-face
  "Returns the bottom face of the `cube`"
  [cube]
  (face cube bottom-face-key))

(defn back-face
  "Returns the back face of the `cube`"
  [cube]
  (face cube back-face-key))

(def ^:private faces-startup-location
  "Where faces are placed on the cube at creation"
  {front-face-key blue,
   left-face-key red,
   right-face-key orange,
   top-face-key white,
   bottom-face-key yellow,
   back-face-key green})

(defn create-cube
  "Creates a solved cube"
  []
  (zipmap
    (keys faces-startup-location)
    (map #(create-face %) (vals faces-startup-location))))

(defn- ^:no-doc rotate-coll-left
  "Rotates a `collection` once to the left, moving the first element at the end
  Returns a lazy sequence

  ```clojure
  (rotate-coll-right [1 2 3]) ; => [2 3 1]
  ```
  "
  [coll]
  (->>
    (cycle coll)
    (drop 1)
    (take (count coll))))

(defn- ^:no-doc create-faces-switch-map
  "Creates a map where keys are `source faces`, and values their `destination`
  Values in the map are the provided keys but rotated once to the right

  ```clojure
  (create-faces-switch-map [a b c]) ; => {a b, b c, c a}
  ```
  "
  [faces-cycle]
  (zipmap faces-cycle (rotate-coll-left faces-cycle)))

(defn- ^:no-doc rotate-cube
  "Rotates the `cube` on itself, the first face will be replaced by the second
  one in `faces-cycle`, the second by the third, [...], the last by the first

  ```clojure
  (def cube {:a [\\a], :b [\\b], :c [\\c], :d [\\d]})
  (rotate cube [:a :b :d]) ; => {:a [d], :b [a], :c [c], :d [b]}
  ```
  "
  [cube faces-cycle]
  (rename-keys
    cube
    (create-faces-switch-map faces-cycle)))

(defn rotate-left
  "Rotates the `cube` to the left"
  [cube]
  (rotate-cube cube [front-face-key left-face-key back-face-key right-face-key]))

(defn rotate-right
  "Rotates the `cube` to the right"
  [cube]
  (rotate-cube cube [front-face-key right-face-key back-face-key left-face-key]))

(defn rotate-up
  "Rotates the `cube` up"
  [cube]
  (rotate-cube cube [front-face-key top-face-key back-face-key bottom-face-key]))

(defn rotate-down
  "Rotates the `cube` down"
  [cube]
  (rotate-cube cube [front-face-key bottom-face-key back-face-key top-face-key]))

(defn rotate-clockwise
  "Rotates the `cube` clockwise"
  [cube]
  (rotate-cube cube [top-face-key right-face-key bottom-face-key left-face-key]))

(defn rotate-anticlockwise
  "Rotates the `cube` anticlockwise"
  [cube]
  (rotate-cube cube [top-face-key left-face-key bottom-face-key right-face-key]))

(defn top-row
  [face]
  (subvec face 0 3))

(defn- paint-top-row
  "Changes the color of the top row to `color`, in the face with key `face-key`"
  [cube face-key color]
  (reduce
     #(assoc-in %1 [face-key %2] color)
     cube
     [0 1 2]))

(defn rotate-top-slice-left
  [cube]
  (reduce
    #(paint-top-row %1 (first %2) (last %2))
    cube
    (seq {left-face-key blue, back-face-key red, right-face-key green})))
