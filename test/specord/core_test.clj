(ns specord.core-test
  (:require [clojure.test :refer :all]
            [specord.core :refer :all]
            [clojure.spec.alpha :as s]))

(defprotocol DTO
  (get-id [this]))

(defspecord UserDTO [id   integer?
                     name string?]
  DTO
  (get-id [this]
    id))

(deftest defspecord-test
  (is (instance? UserDTO (make-user-dto {:id 1 :name "specord"})))
  (is (not (instance? UserDTO (make-user-dto {:id 1 :name 2}))))
  (is (= 1 (get-id (make-user-dto {:id 1 :name "specord"})))))

