(ns specord.core-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer :all]
            [specord.core :as core :refer :all]))

(defprotocol DTO
  (get-id [this]))

(s/def ::name string?)

(defspecord UserDTO [id   integer?
                     name ::name]
  DTO
  (get-id [this]
    id))
