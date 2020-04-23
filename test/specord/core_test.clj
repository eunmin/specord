(ns specord.core-test
  (:require [clojure.test :refer :all]
            [specord.core :refer :all]
            [clojure.spec.alpha :as s]))

(defspecord UserDTO [id   integer?
                     name string?])

(deftest defspecord-test
  (is (instance? UserDTO (make-user-dto {:id 1 :name "specord"})))
  (is (not (instance? UserDTO (make-user-dto {:id 1 :name 2})))))

