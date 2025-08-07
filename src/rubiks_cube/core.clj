(ns rubiks-cube.core
  (:require [clojure.set :as set]))

(defn- create-face
  [color]
  "Creates a face with the specified color"
  (repeat 9 color))

(defn color
  [face]
  "Returns the color of a face"
  (nth face 5)) ; center cell can't move

(defn front-face
  [cube]
  "Returns the front face of the cube"
  (:front cube))

(defn left-face
  [cube]
  "Returns the left face of the cube"
  (:left cube))

(defn right-face
  [cube]
  "Returns the right face of the cube"
  (:right cube))

(defn top-face
  [cube]
  "Returns the top face of the cube"
  (:top cube))

(defn bottom-face
  [cube]
  "Returns the bottom face of the cube"
  (:bottom cube))

(defn back-face
  [cube]
  "Returns the back face of the cube"
  (:back cube))

(defn create-cube
  []
  "Creates a solved cube"
  (let [colors {:front \b :left \r :right \o :top \w :bottom \y :back \g}]
    (zipmap
      (keys colors)
      (map #(create-face %) (vals colors)))))

(defn- create-faces-switch-map
  [faces-cycle]
  "Creates a map where keys are source faces, and values their destination"
  (zipmap
    faces-cycle
    (conj
      (vec (rest faces-cycle))
      (first faces-cycle))))

(defn- rotate
  [cube faces-cycle]
  "Rotates the cube on itself"
  (set/rename-keys
    cube
    (create-faces-switch-map faces-cycle)))

(defn rotate-left
  [cube]
  "Rotates the cube to the left"
  (rotate cube [:front :left :back :right]))

(defn rotate-right
  [cube]
  "Rotates the cube to the right"
  (rotate cube [:front :right :back :left]))

(defn rotate-up
  [cube]
  "Rotates the cube up"
  (rotate cube [:front :top :back :bottom]))

(defn rotate-down
  [cube]
  "Rotates the cube down"
  (rotate cube [:front :bottom :back :top]))

(defn rotate-clockwise
  [cube]
  "Rotates the cube clockwise"
  (rotate cube [:top :right :bottom :left]))

(defn rotate-anticlockwise
  [cube]
  "Rotates the cube anticlockwise"
  (rotate cube [:top :left :bottom :right]))
