(in-ns 'rubiks-cube.cube)

(require '[rubiks-cube.face :as face])

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

(defn- paint-bottom-row
  "Changes the `color` of the bottom row, on the face targeted by `face-key`"
  [cube face-key color]
  (paint-row cube face-key color face/paint-bottom-row))

(defn- paint-column
  "Changes the `color` of a column, on the `face` targeted by `face-key`
  The column is painted by calling `paint-column-fn`, which must match the key
  "
  [cube face-key color paint-column-fn]
  (assoc-in
    cube
    [face-key]
    (paint-column-fn (face cube face-key) color)))

(defn- paint-left-column
  "Changes the `color` of the left column, on the face targeted by `face-key`"
  [cube face-key color]
  (paint-column cube face-key color face/paint-left-column))

(defn- rotate-horizontal-slice
  "Applies a new color on a single row of the front, right, back and left face
  of the `cube`
  Current colors are extracted, rotated by `rotate-colors-fn` taking a
  collection, then colors are applied again by `paint-row-fn`, which should
  take the cube, a face key and the new color
  "
  [cube rotate-colors-fn paint-row-fn]
  (let [faces-cycle [front-face-key right-face-key back-face-key left-face-key],
        new-colors (->> faces-cycle
                     (mapv #(face cube %))
                     (mapv face/color)
                     (rotate-colors-fn))]
    (reduce
      #(paint-row-fn %1 (first %2) (last %2))
      cube
      (seq (zipmap faces-cycle new-colors)))))

(defn- rotate-top-slice
  "Applies a new color on the top row of the front, right, back and left face
  of the `cube`
  Current colors are extracted, rotated by `rotate-colors-fn` taking a
  collection, then colors are applied again
  "
  [cube rotate-colors-fn]
  (rotate-horizontal-slice cube rotate-colors-fn paint-top-row))

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
  Current colors are extracted, rotated by `rotate-colors-fn` taking a
  collection, then colors are applied again
  "
  [cube rotate-colors-fn]
  (rotate-horizontal-slice cube rotate-colors-fn paint-equator-row))

(defn rotate-equator-slice-left
  "Moves the equator row of every face to the one on its left"
  [cube]
  (rotate-equator-slice cube coll/rotate-left))

(defn rotate-equator-slice-right
  "Moves the equator row of every face to the one on its right"
  [cube]
  (rotate-equator-slice cube coll/rotate-right))

(defn- rotate-bottom-slice
  "Applies a new color on the bottom row of the front, right, back and left face
  of the `cube`
  Current colors are extracted, rotated by `rotate-colors-fn` taking a
  collection, then colors are applied again
  "
  [cube rotate-colors-fn]
  (rotate-horizontal-slice cube rotate-colors-fn paint-bottom-row))

(defn rotate-bottom-slice-left
  "Moves the bottom row of every face to the one on its left"
  [cube]
  (rotate-bottom-slice cube coll/rotate-left))

(defn rotate-bottom-slice-right
  "Moves the bottom row of every face to the one on its right"
  [cube]
  (rotate-bottom-slice cube coll/rotate-right))

(defn- rotate-vertical-slice
  "Applies a new color on a single column of the front, top, back and bottom
  face of the `cube`
  Current colors are extracted, rotated by `rotate-colors-fn` taking a
  collection, then colors are applied again by `paint-column-fn`, which should
  take the cube, a face key and the new color
  "
  [cube rotate-colors-fn paint-column-fn]
  (let [faces-cycle [front-face-key top-face-key back-face-key bottom-face-key],
        new-colors (->> faces-cycle
                     (mapv #(face cube %))
                     (mapv face/color)
                     (rotate-colors-fn))]
    (reduce
      #(paint-column-fn %1 (first %2) (last %2))
      cube
      (seq (zipmap faces-cycle new-colors)))))

(defn- rotate-left-slice
  "Applies a new color on the left column of the front, top, back and bottom
  face of the `cube`
  Current colors are extracted, rotated by `rotate-colors-fn` taking a
  collection, then colors are applied again
  "
  [cube rotate-colors-fn]
  (rotate-vertical-slice cube rotate-colors-fn paint-left-column))

(defn rotate-left-slice-up
  "Moves the left column of every face to the one upwards"
  [cube]
  (rotate-left-slice cube coll/rotate-right))
