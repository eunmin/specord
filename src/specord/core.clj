(ns specord.core
  (:require [clojure.spec.alpha :as s]))

(defmacro defspecord [name bindings & opts+specs]
  (let [bindings (partition 2 bindings)
        fields (map first bindings)
        spec-key (keyword (str *ns*) (str name))
        ctor-name (symbol (str "->" name))
        map-ctor-name (symbol (str "map->" name))]
    `(do
       ~@(map (fn [[field spec-form]]
                `(s/def ~(keyword (str *ns* "." name) (str field)) ~spec-form))
              bindings)
       (s/def ~spec-key
         (s/keys :req-un ~(vec (map (fn [field]
                                      (keyword (str *ns* "." name) (str field))) fields))))
       (s/fdef ~ctor-name
         :args (s/cat ~@(->> bindings
                             (map (fn [[field spec]]
                                    [(keyword field) spec]))
                             (apply concat)))
         :ret ~spec-key)
       (s/fdef ~map-ctor-name
         :args (s/cat :m ~spec-key)
         :ret ~spec-key)
       (defrecord ~name ~(vec fields) ~@opts+specs))))
