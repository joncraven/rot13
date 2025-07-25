(ns rot13.core-test
  (:require [clojure.test :refer :all]
            [rot13.core :refer :all]))

(deftest test-rot13-char
  (is (= (rot13-char \a) \n))
  (is (= (rot13-char \N) \A))
  (is (= (rot13-char \!) \!)))


(deftest test-rot13-str
  (is (= (rot13-str "abcXYZ") "nopKLM"))
  (is (= (rot13-str "Hello, World!") "Uryyb, Jbeyq!"))
  (is (= (rot13-str "") "")))


