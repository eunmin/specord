(ns specord.core
  (:require [camel-snake-kebab.core :refer [->kebab-case-string]]
            [clojure.spec.alpha :as s]))

(defmacro defspecord [name & [bindings]]
  (let [bindings (partition 2 bindings)
        fields (map first bindings)
        kebab-name (->kebab-case-string name)]
    `(do
       ~@(map (fn [[field spec-form]]
                `(s/def ~(keyword (str *ns* "." kebab-name) (str field)) ~spec-form))
              bindings)
       (s/def ~(keyword (str *ns*) kebab-name)
         (s/keys :req-un ~(vec (map (fn [field]
                                      (keyword (str *ns* "." kebab-name) (str field))) fields))))
       (defrecord ~name ~(vec fields))
       (defn ~(symbol (str "make-" kebab-name)) [value#]
         (if-let [error# (s/explain-data ~(keyword (str *ns*) kebab-name) value#)]
           error#
           (~(symbol (str "map->" name)) value#))))))
