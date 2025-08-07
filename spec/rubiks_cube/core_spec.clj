(ns rubiks-cube.core-spec
  (:require [speclj.core :refer :all]
            [rubiks-cube.core :refer :all]))

(describe "new cube"
    (let [cube (create-cube)]
        (it "has 6 faces"
            (should= 6 (count cube)))
        (it "has 9 cells on front face"
            (should= 9 (count (front-face cube))))
        (it "has 9 cells on left face"
            (should= 9 (count (left-face cube))))
        (it "has 9 cells on right face"
            (should= 9 (count (right-face cube))))
        (it "has 9 cells on top face"
            (should= 9 (count (top-face cube))))
        (it "has 9 cells on bottom face"
            (should= 9 (count (bottom-face cube))))
        (it "has 9 cells on back face"
            (should= 9 (count (back-face cube))))
        (it "has blue face in front"
            (should= \b (color (front-face cube))))
        (it "has red face on the left"
            (should= \r (color (left-face cube))))
        (it "has orange face on the right"
            (should= \o (color (right-face cube))))
        (it "has white face on top"
            (should= \w (color (top-face cube))))
        (it "has yellow face at the bottom"
            (should= \y (color (bottom-face cube))))
        (it "has green face on the back"
            (should= \g (color (back-face cube))))))

(describe "cube left rotation"
    (let [cube (create-cube), rotated-cube (rotate-left cube)]
        (it "moves the front face to the left"
            (should=
                (color (front-face cube))
                (color (left-face rotated-cube))))
        (it "moves the left face to the back"
            (should=
                (color (left-face cube))
                (color (back-face rotated-cube))))
        (it "moves the back face to the right"
            (should=
                (color (back-face cube))
                (color (right-face rotated-cube))))
        (it "moves the right face to the front"
            (should=
                (color (right-face cube))
                (color (front-face rotated-cube))))))

(describe "cube right rotation"
    (let [cube (create-cube), rotated-cube (rotate-right cube)]
        (it "moves the front face to the right"
            (should=
                (color (front-face cube))
                (color (right-face rotated-cube))))
        (it "moves the right face to the back"
            (should=
                (color (right-face cube))
                (color (back-face rotated-cube))))
        (it "moves the back face to the left"
            (should=
                (color (back-face cube))
                (color (left-face rotated-cube))))
        (it "moves the left face to the front"
            (should=
                (color (left-face cube))
                (color (front-face rotated-cube))))))

(describe "cube up rotation"
    (let [cube (create-cube), rotated-cube (rotate-up cube)]
        (it "moves the front face to the top"
            (should=
                (color (front-face cube))
                (color (top-face rotated-cube))))
        (it "moves the top face to the back"
            (should=
                (color (top-face cube))
                (color (back-face rotated-cube))))
        (it "moves the back face to the bottom"
            (should=
                (color (back-face cube))
                (color (bottom-face rotated-cube))))
        (it "moves the bottom face to the front"
            (should=
                (color (bottom-face cube))
                (color (front-face rotated-cube))))))

(describe "cube down rotation"
    (let [cube (create-cube), rotated-cube (rotate-down cube)]
        (it "moves the front face to the bottom"
            (should=
                (color (front-face cube))
                (color (bottom-face rotated-cube))))
        (it "moves the bottom face to the back"
            (should=
                (color (bottom-face cube))
                (color (back-face rotated-cube))))
        (it "moves the back face to the top"
            (should=
                (color (back-face cube))
                (color (top-face rotated-cube))))
        (it "moves the top face to the front"
            (should=
                (color (top-face cube))
                (color (front-face rotated-cube))))))

(describe "cube clockwise rotation"
    (let [cube (create-cube), rotated-cube (rotate-clockwise cube)]
        (it "moves the top face to the right"
            (should=
                (color (top-face cube))
                (color (right-face rotated-cube))))
        (it "moves the right face to the bottom"
            (should=
                (color (right-face cube))
                (color (bottom-face rotated-cube))))
        (it "moves the bottom face to the left"
            (should=
                (color (bottom-face cube))
                (color (left-face rotated-cube))))
        (it "moves the left face to the top"
            (should=
                (color (left-face cube))
                (color (top-face rotated-cube))))))

(describe "cube anticlockwise rotation"
    (let [cube (create-cube), rotated-cube (rotate-anticlockwise cube)]
        (it "moves the top face to the left"
            (should=
                (color (top-face cube))
                (color (left-face rotated-cube))))
        (it "moves the left face to the bottom"
            (should=
                (color (left-face cube))
                (color (bottom-face rotated-cube))))
        (it "moves the bottom face to the right"
            (should=
                (color (bottom-face cube))
                (color (right-face rotated-cube))))
        (it "moves the right face to the top"
            (should=
                (color (right-face cube))
                (color (top-face rotated-cube))))))
