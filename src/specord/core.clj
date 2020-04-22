(ns specord.core
  (:require [clojure.spec.alpha :as s]))

(defmacro defspecord [name & [bindings]]
  (let [bindings (partition 2 bindings)
        fields (map first bindings)]
    `(do
       ~@(map (fn [[field spec-form]]
                `(s/def ~(keyword (str *ns*) (str field)) ~spec-form))
              bindings)
       (s/def ~(keyword (str *ns*) (str name))
         (s/keys :req-un ~(vec (map (fn [field]
                                      (keyword (str *ns*) (str field))) fields))))
       (defrecord ~name ~(vec fields)))))
