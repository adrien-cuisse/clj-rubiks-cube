(ns rubiks-cube.collection)

(defn ^:no-doc rotate-left
  "Rotates a `collection` to the left, moving the first elements at the end
  If shift-size isn't provided, it defaults to 1
  Returns a lazy sequence

  ```clojure
  (rotate-left [1 2 3]) ; => [2 3 1]
  (rotate-left [1 2 3] 1) ; => [2 3 1]
  (rotate-left [1 2 3] 2) ; => [3 1 2]
  ```
  "
  ([coll]
   (rotate-left coll 1))
  ([coll shift-size]
   (->>
     (cycle coll)
     (drop shift-size)
     (take (count coll)))))

(defn ^:no-doc rotate-right
  "Rotates a `collection` once to the right, moving the last element at the
  beginning
  Returns a lazy sequence

  ```clojure
  (rotate-right [1 2 3]) ; => [3 1 2]
  ```
  "
  [coll]
  (rotate-left
    coll
    (dec (count coll))))
