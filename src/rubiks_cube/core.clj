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

(load "slices_rotations")
