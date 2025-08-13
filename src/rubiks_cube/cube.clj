(ns rubiks-cube.cube
  (:require [rubiks-cube.color :refer :all]
            [rubiks-cube.face :as face]))

(def ^:private front-face-key :front-face)
(def ^:private back-face-key :back-face)
(def ^:private left-face-key :left-face)
(def ^:private right-face-key :right-face)
(def ^:private top-face-key :top-face)
(def ^:private bottom-face-key :bottom-face)

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

(defn create
  "Creates a solved cube"
  []
  (zipmap
    (keys faces-startup-location)
    (map #(face/create %) (vals faces-startup-location))))

(load "rotations")
(load "slices_rotations")
