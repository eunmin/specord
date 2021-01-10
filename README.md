# specord

Define record with spec!

## Installation

Add the following dependency to your `project.clj` file:

    [specord "0.1.1"]

## Usage

``` clojure
(ns my
  (:require [specord.core :refer [defspecord]]))

(defspecord User [id   integer?
                  name string?])

;; It generates spec for User, id and name like this:
;;
;; (clojure.spec.alpha/def :my.User/id integer?)
;; (clojure.spec.alpha/def :my.User/name string?)
;; (clojure.spec.alpha/def
;;   :my/User
;;   (clojure.spec.alpha/keys :req-un [:my.User/id :my.User/name]))
;; (clojure.spec.alpha/fdef
;;   ->User
;;   :args
;;   (clojure.spec.alpha/cat :id integer? :name string?)
;;   :ret
;;   :my/User)
;; (clojure.spec.alpha/fdef
;;   map->User
;;   :args
;;   (clojure.spec.alpha/cat :m :my/User)
;;   :ret
;;   :my/User)
;; (defrecord User [id name])

(s/valid? :my/User (->User 1 "Eunmin Kim"))
;; true

(require '[clojure.spec.test.alpha :as stest])

(stest/instrument `->User)

(->User "1" "Eunmin Kim")
;; You will see an error like this:
;; clojure.lang.ExceptionInfo
;;  Spec assertion failed.
;;  Spec: ...
;; Value: ("1" "Eunmin Kim")
;; Problems : ...
```

## License

Copyright © 2020 Eunmin Kim

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
