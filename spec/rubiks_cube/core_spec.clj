(ns rubiks-cube.core-spec
  (:require [speclj.core :refer :all]
            [rubiks-cube.color :refer :all]
            [rubiks-cube.face :as face]
            [rubiks-cube.cube :as cube]))

(describe "a cube"
  (with cube (cube/create))
  (it "has 6 faces"
    (should-have-count 6 @cube))
  (it "has 9 cells on front face"
    (should-have-count 9 (cube/front-face @cube)))
  (it "has 9 cells on left face"
    (should-have-count 9 (cube/left-face @cube)))
  (it "has 9 cells on right face"
    (should-have-count 9 (cube/right-face @cube)))
  (it "has 9 cells on top face"
    (should-have-count 9 (cube/top-face @cube)))
  (it "has 9 cells on bottom face"
    (should-have-count 9 (cube/bottom-face @cube)))
  (it "has 9 cells on back face"
    (should-have-count 9 (cube/back-face @cube))))

(describe "new cube"
  (with cube (cube/create))
  (it "has blue face in front"
    (should= blue (face/color (cube/front-face @cube))))
  (it "has red face on the left"
    (should= red (face/color (cube/left-face @cube))))
  (it "has orange face on the right"
    (should= orange (face/color (cube/right-face @cube))))
  (it "has white face on top"
    (should= white (face/color (cube/top-face @cube))))
  (it "has yellow face at the bottom"
    (should= yellow (face/color (cube/bottom-face @cube))))
  (it "has green face on the back"
    (should= green (face/color (cube/back-face @cube)))))

  (describe "cube left rotation"
    (with cube (cube/create))
    (with rotated-cube (cube/rotate-left @cube))
    (it "moves the front face to the left"
      (should=
        (face/color (cube/front-face @cube))
        (face/color (cube/left-face @rotated-cube))))
    (it "moves the left face to the back"
      (should=
        (face/color (cube/left-face @cube))
        (face/color (cube/back-face @rotated-cube))))
    (it "moves the back face to the right"
      (should=
        (face/color (cube/back-face @cube))
        (face/color (cube/right-face @rotated-cube))))
    (it "moves the right face to the front"
      (should=
        (face/color (cube/right-face @cube))
        (face/color (cube/front-face @rotated-cube)))))

(describe "cube right rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-right @cube))
  (it "moves the front face to the right"
    (should=
      (face/color (cube/front-face @cube))
      (face/color (cube/right-face @rotated-cube))))
  (it "moves the right face to the back"
    (should=
      (face/color (cube/right-face @cube))
      (face/color (cube/back-face @rotated-cube))))
  (it "moves the back face to the left"
    (should=
      (face/color (cube/back-face @cube))
      (face/color (cube/left-face @rotated-cube))))
  (it "moves the left face to the front"
    (should=
      (face/color (cube/left-face @cube))
      (face/color (cube/front-face @rotated-cube)))))

(describe "cube up rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-up @cube))
    (it "moves the front face to the top"
      (should=
        (face/color (cube/front-face @cube))
        (face/color (cube/top-face @rotated-cube))))
    (it "moves the top face to the back"
      (should=
        (face/color (cube/top-face @cube))
        (face/color (cube/back-face @rotated-cube))))
    (it "moves the back face to the bottom"
      (should=
        (face/color (cube/back-face @cube))
        (face/color (cube/bottom-face @rotated-cube))))
    (it "moves the bottom face to the front"
      (should=
        (face/color (cube/bottom-face @cube))
        (face/color (cube/front-face @rotated-cube)))))

(describe "cube down rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-down @cube))
  (it "moves the front face to the bottom"
    (should=
      (face/color (cube/front-face @cube))
      (face/color (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face to the back"
    (should=
      (face/color (cube/bottom-face @cube))
      (face/color (cube/back-face @rotated-cube))))
  (it "moves the back face to the top"
    (should=
      (face/color (cube/back-face @cube))
      (face/color (cube/top-face @rotated-cube))))
  (it "moves the top face to the front"
    (should=
      (face/color (cube/top-face @cube))
      (face/color (cube/front-face @rotated-cube)))))

(describe "cube clockwise rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-clockwise @cube))
  (it "moves the top face to the right"
    (should=
      (face/color (cube/top-face @cube))
      (face/color (cube/right-face @rotated-cube))))
  (it "moves the right face to the bottom"
    (should=
      (face/color (cube/right-face @cube))
      (face/color (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face to the left"
    (should=
      (face/color (cube/bottom-face @cube))
      (face/color (cube/left-face @rotated-cube))))
  (it "moves the left face to the top"
    (should=
      (face/color (cube/left-face @cube))
      (face/color (cube/top-face @rotated-cube)))))

(describe "cube anticlockwise rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-anticlockwise @cube))
  (it "moves the top face to the left"
    (should=
      (face/color (cube/top-face @cube))
      (face/color (cube/left-face @rotated-cube))))
  (it "moves the left face to the bottom"
    (should=
      (face/color (cube/left-face @cube))
      (face/color (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face to the right"
    (should=
      (face/color (cube/bottom-face @cube))
      (face/color (cube/right-face @rotated-cube))))
  (it "moves the right face to the top"
    (should=
      (face/color (cube/right-face @cube))
      (face/color (cube/top-face @rotated-cube)))))

(describe "top slice left rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-top-slice-left @cube))

  (it "moves the front face top row to the left face top row"
    (should=
      (face/top-row (cube/front-face @cube))
      (face/top-row (cube/left-face @rotated-cube))))
  (it "moves the left face top row to the back face top row"
    (should=
      (face/top-row (cube/left-face @cube))
      (face/top-row (cube/back-face @rotated-cube))))
  (it "moves the back face top row to the right face top row"
    (should=
      (face/top-row (cube/back-face @cube))
      (face/top-row (cube/right-face @rotated-cube))))
  (it "moves the right face top row to the front face top row"
    (should=
      (face/top-row (cube/right-face @cube))
      (face/top-row (cube/front-face @rotated-cube)))))

(describe "top slice right rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-top-slice-right @cube))

  (it "moves the front face top row to the right face top row"
    (should=
      (face/top-row (cube/front-face @cube))
      (face/top-row (cube/right-face @rotated-cube))))
  (it "moves the right face top row to the back face top row"
    (should=
      (face/top-row (cube/right-face @cube))
      (face/top-row (cube/back-face @rotated-cube))))
  (it "moves the back face top row to the left face top row"
    (should=
      (face/top-row (cube/back-face @cube))
      (face/top-row (cube/left-face @rotated-cube))))
  (it "moves the left face top row to the front face top row"
    (should=
      (face/top-row (cube/left-face @cube))
      (face/top-row (cube/front-face @rotated-cube)))))

(describe "equator slice left rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-equator-slice-left @cube))

  (it "moves the front face equator row to the left face equator row"
    (should=
      (face/equator-row (cube/front-face @cube))
      (face/equator-row (cube/left-face @rotated-cube))))
  (it "moves the left face equator row to the back face equator row"
    (should=
      (face/equator-row (cube/left-face @cube))
      (face/equator-row (cube/back-face @rotated-cube))))
  (it "moves the back face equator row to the right face equator row"
    (should=
      (face/equator-row (cube/back-face @cube))
      (face/equator-row (cube/right-face @rotated-cube))))
  (it "moves the right face equator row to the front face equator row"
    (should=
      (face/equator-row (cube/right-face @cube))
      (face/equator-row (cube/front-face @rotated-cube)))))

(describe "equator slice right rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-equator-slice-right @cube))

  (it "moves the front face equator row to the right face equator row"
    (should=
      (face/equator-row (cube/front-face @cube))
      (face/equator-row (cube/right-face @rotated-cube))))
  (it "moves the right face equator row to the back face equator row"
    (should=
      (face/equator-row (cube/right-face @cube))
      (face/equator-row (cube/back-face @rotated-cube))))
  (it "moves the back face equator row to the left face equator row"
    (should=
      (face/equator-row (cube/back-face @cube))
      (face/equator-row (cube/left-face @rotated-cube))))
  (it "moves the left face equator row to the front face equator row"
    (should=
      (face/equator-row (cube/left-face @cube))
      (face/equator-row (cube/front-face @rotated-cube)))))

(describe "bottom slice left rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-bottom-slice-left @cube))

  (it "moves the front face bottom row to the left face bottom row"
    (should=
      (face/bottom-row (cube/front-face @cube))
      (face/bottom-row (cube/left-face @rotated-cube))))
  (it "moves the left face bottom row to the back face bottom row"
    (should=
      (face/bottom-row (cube/left-face @cube))
      (face/bottom-row (cube/back-face @rotated-cube))))
  (it "moves the back face bottom row to the right face bottom row"
    (should=
      (face/bottom-row (cube/back-face @cube))
      (face/bottom-row (cube/right-face @rotated-cube))))
  (it "moves the right face bottom row to the front face bottom row"
    (should=
      (face/bottom-row (cube/right-face @cube))
      (face/bottom-row (cube/front-face @rotated-cube)))))

(describe "bottom slice right rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-bottom-slice-right @cube))

  (it "moves the front face bottom row to the right face bottom row"
    (should=
      (face/bottom-row (cube/front-face @cube))
      (face/bottom-row (cube/right-face @rotated-cube))))
  (it "moves the right face bottom row to the back face bottom row"
    (should=
      (face/bottom-row (cube/right-face @cube))
      (face/bottom-row (cube/back-face @rotated-cube))))
  (it "moves the back face bottom row to the left face bottom row"
    (should=
      (face/bottom-row (cube/back-face @cube))
      (face/bottom-row (cube/left-face @rotated-cube))))
  (it "moves the left face bottom row to the front face bottom row"
    (should=
      (face/bottom-row (cube/left-face @cube))
      (face/bottom-row (cube/front-face @rotated-cube)))))

(describe "left slice up rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-left-slice-up @cube))

  (it "moves the front face left column to the top face left column"
    (should=
      (face/left-column (cube/front-face @cube))
      (face/left-column (cube/top-face @rotated-cube))))
  (it "moves the top face left column to the back face left column"
    (should=
      (face/left-column (cube/top-face @cube))
      (face/left-column (cube/back-face @rotated-cube))))
  (it "moves the back face left column to the bottom face left column"
    (should=
      (face/left-column (cube/back-face @cube))
      (face/left-column (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face left column to the front face left column"
    (should=
      (face/left-column (cube/bottom-face @cube))
      (face/left-column (cube/front-face @rotated-cube)))))

(describe "left slice down rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-left-slice-down @cube))

  (it "moves the front face left column to the bottom face left column"
    (should=
      (face/left-column (cube/front-face @cube))
      (face/left-column (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face left column to the back face left column"
    (should=
      (face/left-column (cube/bottom-face @cube))
      (face/left-column (cube/back-face @rotated-cube))))
  (it "moves the back face left column to the top face left column"
    (should=
      (face/left-column (cube/back-face @cube))
      (face/left-column (cube/top-face @rotated-cube))))
  (it "moves the top face left column to the front face left column"
    (should=
      (face/left-column (cube/top-face @cube))
      (face/left-column (cube/front-face @rotated-cube)))))

(describe "middle slice up rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-middle-slice-up @cube))

  (it "moves the front face middle column to the top face middle column"
    (should=
      (face/middle-column (cube/front-face @cube))
      (face/middle-column (cube/top-face @rotated-cube))))
  (it "moves the top face middle column to the back face middle column"
    (should=
      (face/middle-column (cube/top-face @cube))
      (face/middle-column (cube/back-face @rotated-cube))))
  (it "moves the back face middle column to the bottom face middle column"
    (should=
      (face/middle-column (cube/back-face @cube))
      (face/middle-column (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face middle column to the front face middle column"
    (should=
      (face/middle-column (cube/bottom-face @cube))
      (face/middle-column (cube/front-face @rotated-cube)))))

(describe "middle slice down rotation"
  (with cube (cube/create))
  (with rotated-cube (cube/rotate-middle-slice-down @cube))

  (it "moves the front face middle column to the bottom face middle column"
    (should=
      (face/middle-column (cube/front-face @cube))
      (face/middle-column (cube/bottom-face @rotated-cube))))
  (it "moves the bottom face middle column to the back face middle column"
    (should=
      (face/middle-column (cube/bottom-face @cube))
      (face/middle-column (cube/back-face @rotated-cube))))
  (it "moves the back face middle column to the top face middle column"
    (should=
      (face/middle-column (cube/back-face @cube))
      (face/middle-column (cube/top-face @rotated-cube))))
  (it "moves the top face middle column to the front face middle column"
    (should=
      (face/middle-column (cube/top-face @cube))
      (face/middle-column (cube/front-face @rotated-cube)))))
