(ns rubiks-cube.core
  (:require [clojure.set :refer [rename-keys]]
            [rubiks-cube.collection :as coll]
            [rubiks-cube.face :as face]))

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

(load "rotations")

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
    (map #(face/create %) (vals faces-startup-location))))

  (defn- paint-row
    "Changes the `color` of a row, on the `face` targeted by `face-key`
    The row is painted by calling `paint-row-fn`, which must match the key
    "
    [cube face-key color paint-row-fn]
    (assoc-in
      cube
      [face-key]
      (paint-row-fn (face cube face-key) color)))

  (defn- paint-top-row
    "Changes the `color` of the top row, on the face targeted by `face-key`"
    [cube face-key color]
    (paint-row cube face-key color face/paint-top-row))

  (defn- paint-equator-row
    "Changes the `color` of the equator row, on the face targeted by `face-key`"
    [cube face-key color]
    (paint-row cube face-key color face/paint-equator-row))

(defn- rotate-horizontal-slice
  "Applies a new color on a single row of the front, right, back and left face
  of the `cube`
  Current colors are extracted, rotated by `f-rotation` taking a collection,
  then colors are applied again by `f-paint-row, which should take the cube,
  a face key and the new color
  "
  [cube f-rotate-colors f-paint-row]
  (let [faces-cycle [front-face-key right-face-key back-face-key left-face-key],
        new-colors (->> faces-cycle
                     (mapv #(face cube %))
                     (mapv #(face/color %))
                     (f-rotate-colors))]
    (reduce
      #(f-paint-row %1 (first %2) (last %2))
      cube
      (seq (zipmap faces-cycle new-colors)))))

(defn- rotate-top-slice
  "Applies a new color on the top row of the front, right, back and left face
  of the `cube`
  Current colors are extracted, rotated by `f-rotation` taking a collection,
  then colors are applied again
  "
  [cube f-rotate-colors]
  (rotate-horizontal-slice cube f-rotate-colors paint-top-row))

(defn rotate-top-slice-left
  "Moves the top row of every face to the one on its left"
  [cube]
  (rotate-top-slice cube coll/rotate-left))

(defn rotate-top-slice-right
  "Moves the top row of every face to the one on its right"
  [cube]
  (rotate-top-slice cube coll/rotate-right))

(defn- rotate-equator-slice
  "Applies a new color on the equator row of the front, right, back and left face
  of the `cube`
  Current colors are extracted, rotated by `f-rotation` taking a collection,
  then colors are applied again
  "
  [cube f-rotate-colors]
  (rotate-horizontal-slice cube f-rotate-colors paint-equator-row))

(defn rotate-equator-slice-left
  "Moves the equator row of every face to the one on its left"
  [cube]
  (rotate-equator-slice cube coll/rotate-left))

(defn rotate-equator-slice-right
  "Moves the equator row of every face to the one on its right"
  [cube]
  (rotate-equator-slice cube coll/rotate-right))
