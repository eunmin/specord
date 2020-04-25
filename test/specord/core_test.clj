(ns specord.core-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer :all]
            [specord.core :as core :refer :all])
  (:import clojure.lang.ExceptionInfo))

(defprotocol DTO
  (get-id [this]))

(s/def ::name string?)

(defspecord UserDTO [id   integer?
                     name ::name]
  DTO
  (get-id [this]
    id))

(deftest defspecord-test
  (is (instance? UserDTO (make-user-dto {:id 1 :name "specord"})))
  (is (not (instance? UserDTO (make-user-dto {:id 1 :name 2}))))
  (is (= 1 (get-id (make-user-dto {:id 1 :name "specord"}))))
  (is (thrown? ExceptionInfo (make-user-dto! {:id 1 :name 2}))))

