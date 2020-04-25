# specord

Define record with spec!

## Usage

``` clojure
(require '[specord.core :refer [defspecord]])

(ns my)

(defspecord User [id   integer?
                  name string?])

;; It generates spec for User, id and name like this:
;; (s/def :my.user/id integer?)
;; (s/def :my.user/name string?)
;; (s/def :my/user (s/keys :req-un [:my.user/id :my.user/name]))

(s/valid? :my/user (->User 1 "Eunmin Kim"))
;; true

;; It also makes useful constructors for record like this
;; (defn make-user [m] ...)
;; (defn make-user! [m] ...)
;;
;; This constructors has the ability to validate specs.

(make-user {:id 1 :name "Eunmin Kim"})
;; #my.User{:id 1 :name "Eunmin Kim"}

(make-user {:id 1 :name nil})
;; #:clojure.spec.alpha{:problems ({ ...

(make-user! {:id 1 :name nil})
;; An exception occurred with ExceptionInfo.
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
