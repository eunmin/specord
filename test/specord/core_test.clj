(ns specord.core-test
  (:require [clojure.test :refer :all]
            [specord.core :refer :all]
            [clojure.spec.alpha :as s]))

(defspecord UserDTO [id   integer?
                     name string?])

(deftest defspecord-test
  (is (s/valid? ::UserDTO (->UserDTO 1 "specord")))
  (is (not (s/valid? ::UserDTO (->UserDTO 1 2)))))
