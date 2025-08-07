(ns rubiks-cube.core
  (:require [clojure.set :refer [rename-keys]]))

(def blue \b)
(def green \g)
(def orange \o)
(def red \r)
(def white \w)
(def yellow \y)

(defn- ^:no-doc create-face
  "Creates a face with the specified color"
  [color]
  (repeat 9 color))

(defn color
  "Returns the color of a face"
  [face]
  (nth face 5)) ; center cell can't move

(defn front-face
  "Returns the front face of the cube"
  [cube]
  (:front cube))

(defn left-face
  "Returns the left face of the cube"
  [cube]
  (:left cube))

(defn right-face
  "Returns the right face of the cube"
  [cube]
  (:right cube))

(defn top-face
  "Returns the top face of the cube"
  [cube]
  (:top cube))

(defn bottom-face
  "Returns the bottom face of the cube"
  [cube]
  (:bottom cube))

(defn back-face
  "Returns the back face of the cube"
  [cube]
  (:back cube))

(defn create-cube
  "Creates a solved cube"
  []
  (let [colors {:front \b :left \r :right \o :top \w :bottom \y :back \g}]
    (zipmap
      (keys colors)
      (map #(create-face %) (vals colors)))))

(defn- ^:no-doc create-faces-switch-map
  "Creates a map where keys are source faces, and values their destination"
  [faces-cycle]
  (zipmap
    faces-cycle
    (conj
      (vec (rest faces-cycle))
      (first faces-cycle))))

(defn- ^:no-doc rotate
  "Rotates the cube on itself"
  [cube faces-cycle]
  (rename-keys
    cube
    (create-faces-switch-map faces-cycle)))

(defn rotate-left
  "Rotates the cube to the left"
  [cube]
  (rotate cube [:front :left :back :right]))

(defn rotate-right
  "Rotates the cube to the right"
  [cube]
  (rotate cube [:front :right :back :left]))

(defn rotate-up
  "Rotates the cube up"
  [cube]
  (rotate cube [:front :top :back :bottom]))

(defn rotate-down
  "Rotates the cube down"
  [cube]
  (rotate cube [:front :bottom :back :top]))

(defn rotate-clockwise
  "Rotates the cube clockwise"
  [cube]
  (rotate cube [:top :right :bottom :left]))

(defn rotate-anticlockwise
  "Rotates the cube anticlockwise"
  [cube]
  (rotate cube [:top :left :bottom :right]))
