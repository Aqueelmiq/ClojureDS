(ns bst.t-core
  (:use midje.sweet)
  (:use [bst.core])
  (:import [bst.core BST] ))

(facts "About add function"
       (fact "add function correctly adds an element"
             (+ 1 2)  => 3)
       (fact "add function increments the size properly"

             ))

(facts "about this lab"
  (fact "the student started the lab."
        (+ 1 2)  => 3))
