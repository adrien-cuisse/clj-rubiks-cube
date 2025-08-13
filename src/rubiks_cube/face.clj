(ns rubiks-cube.face)

(defn ^:no-doc create
  "Creates a face with the specified `color`"
  [color]
  (vec (repeat 9 color)))

(defn color
  "Returns the color of a `face`"
  [face]
  (nth face 4)) ; center cell can't move

(defn- span
  "Returns a span of the `face`, being a row or a column
  The span is defined by the `cells-key`"
  [face cells-key]
  (get face cells-key))

(defn top-row
  "Returns the top row of the `face`"
  [face]
  (span face [0 1 2]))

(defn equator-row
  "Returns the equator row of the `face`"
  [face]
  (span face [3 4 5]))

(defn bottom-row
  "Returns the bottom row of the `face`"
  [face]
  (span face [6 7 8]))

(defn left-column
  "Returns the left column of the `face`"
  [face]
  (span face [0 3 6]))

(defn- paint-row
  "Changes the color of a row to `color`, on the `face` with key `face-key`
  The row is defined by the `cells-key` on that face
  "
  [face color cells-key]
  (reduce
    #(assoc-in %1 [%2] color)
    face
    cells-key))

(defn paint-top-row
  "Changes the color of the top row to `color`, on the face with
  key `face-key`"
  [face color]
  (paint-row face color [0 1 2]))

(defn paint-equator-row
  "Changes the color of the equator row to `color`, on the face with
  key `face-key`"
  [face color]
  (paint-row face color [3 4 5]))

(defn paint-bottom-row
  "Changes the color of the bottom row to `color`, on the face with
  key `face-key`"
  [face color]
  (paint-row face color [6 7 8]))

(defn- paint-column
  "Changes the color of a column to `color`, on the `face` with key `face-key`
  The column is defined by the `cells-key` on that face
  "
  [face color cells-key]
  (reduce
    #(assoc-in %1 [%2] color)
    face
    cells-key))

(defn paint-left-column
  "Changes the color of the left column to `color`, on the face with
  key `face-key`"
  [face color]
  (paint-column face color [0 3 6]))
