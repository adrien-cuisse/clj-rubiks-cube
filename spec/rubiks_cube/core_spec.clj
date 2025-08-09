(ns rubiks-cube.core-spec
  (:require [speclj.core :refer :all]
            [rubiks-cube.core :refer :all]))

(describe "a cube"
  (with cube (create-cube))
  (it "has 6 faces"
    (should-have-count 6 @cube))
  (it "has 9 cells on front face"
    (should-have-count 9 (front-face @cube)))
  (it "has 9 cells on left face"
    (should-have-count 9 (left-face @cube)))
  (it "has 9 cells on right face"
    (should-have-count 9 (right-face @cube)))
  (it "has 9 cells on top face"
    (should-have-count 9 (top-face @cube)))
  (it "has 9 cells on bottom face"
    (should-have-count 9 (bottom-face @cube)))
  (it "has 9 cells on back face"
    (should-have-count 9 (back-face @cube))))

(describe "new cube"
  (with cube (create-cube))
  (it "has blue face in front"
    (should= blue (color (front-face @cube))))
  (it "has red face on the left"
    (should= red (color (left-face @cube))))
  (it "has orange face on the right"
    (should= orange (color (right-face @cube))))
  (it "has white face on top"
    (should= white (color (top-face @cube))))
  (it "has yellow face at the bottom"
    (should= yellow (color (bottom-face @cube))))
  (it "has green face on the back"
    (should= green (color (back-face @cube)))))

  (describe "cube left rotation"
    (with cube (create-cube))
    (with rotated-cube (rotate-left @cube))
    (it "moves the front face to the left"
      (should=
        (color (front-face @cube))
        (color (left-face @rotated-cube))))
    (it "moves the left face to the back"
      (should=
        (color (left-face @cube))
        (color (back-face @rotated-cube))))
    (it "moves the back face to the right"
      (should=
        (color (back-face @cube))
        (color (right-face @rotated-cube))))
    (it "moves the right face to the front"
      (should=
        (color (right-face @cube))
        (color (front-face @rotated-cube)))))

(describe "cube right rotation"
  (with cube (create-cube))
  (with rotated-cube (rotate-right @cube))
  (it "moves the front face to the right"
    (should=
      (color (front-face @cube))
      (color (right-face @rotated-cube))))
  (it "moves the right face to the back"
    (should=
      (color (right-face @cube))
      (color (back-face @rotated-cube))))
  (it "moves the back face to the left"
    (should=
      (color (back-face @cube))
      (color (left-face @rotated-cube))))
  (it "moves the left face to the front"
    (should=
      (color (left-face @cube))
      (color (front-face @rotated-cube)))))

(describe "cube up rotation"
  (with cube (create-cube))
  (with rotated-cube (rotate-up @cube))
    (it "moves the front face to the top"
      (should=
        (color (front-face @cube))
        (color (top-face @rotated-cube))))
    (it "moves the top face to the back"
      (should=
        (color (top-face @cube))
        (color (back-face @rotated-cube))))
    (it "moves the back face to the bottom"
      (should=
        (color (back-face @cube))
        (color (bottom-face @rotated-cube))))
    (it "moves the bottom face to the front"
      (should=
        (color (bottom-face @cube))
        (color (front-face @rotated-cube)))))

(describe "cube down rotation"
  (with cube (create-cube))
  (with rotated-cube (rotate-down @cube))
  (it "moves the front face to the bottom"
    (should=
      (color (front-face @cube))
      (color (bottom-face @rotated-cube))))
  (it "moves the bottom face to the back"
    (should=
      (color (bottom-face @cube))
      (color (back-face @rotated-cube))))
  (it "moves the back face to the top"
    (should=
      (color (back-face @cube))
      (color (top-face @rotated-cube))))
  (it "moves the top face to the front"
    (should=
      (color (top-face @cube))
      (color (front-face @rotated-cube)))))

(describe "cube clockwise rotation"
  (with cube (create-cube))
  (with rotated-cube (rotate-clockwise @cube))
  (it "moves the top face to the right"
    (should=
      (color (top-face @cube))
      (color (right-face @rotated-cube))))
  (it "moves the right face to the bottom"
    (should=
      (color (right-face @cube))
      (color (bottom-face @rotated-cube))))
  (it "moves the bottom face to the left"
    (should=
      (color (bottom-face @cube))
      (color (left-face @rotated-cube))))
  (it "moves the left face to the top"
    (should=
      (color (left-face @cube))
      (color (top-face @rotated-cube)))))

(describe "cube anticlockwise rotation"
  (with cube (create-cube))
  (with rotated-cube (rotate-anticlockwise @cube))
  (it "moves the top face to the left"
    (should=
      (color (top-face @cube))
      (color (left-face @rotated-cube))))
  (it "moves the left face to the bottom"
    (should=
      (color (left-face @cube))
      (color (bottom-face @rotated-cube))))
  (it "moves the bottom face to the right"
    (should=
      (color (bottom-face @cube))
      (color (right-face @rotated-cube))))
  (it "moves the right face to the top"
    (should=
      (color (right-face @cube))
      (color (top-face @rotated-cube)))))

(describe "top slice left rotation"
  (with cube (create-cube))
  (with rotated-cube (rotate-top-slice-left @cube))

  (it "moves the front face top row to the left face top row"
    (should=
      (top-row (front-face @cube))
      (top-row (left-face @rotated-cube)))))
