(in-ns 'rubiks-cube.core)

(defn- ^:no-doc create-faces-switch-map
  "Creates a map where keys are `source faces`, and values their `destination`
  Values in the map are the provided keys but rotated once to the left

  ```clojure
  (create-faces-switch-map [:a :b :c]) ; => {:a :b, :b :c, :c :a}
  ```
  "
  [faces-cycle]
  (zipmap faces-cycle (coll/rotate-left faces-cycle)))

(defn- ^:no-doc rotate-cube
  "Rotates the `cube` on itself, the first face will be replaced by the second
  one in `faces-cycle`, the second by the third, [...], the last by the first

  ```clojure
  (def cube {:a [\\a], :b [\\b], :c [\\c], :d [\\d]})
  (rotate cube [:a :b :d]) ; => {:a [\\d], :b [\\a], :c [c], :d [\\b]}
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
