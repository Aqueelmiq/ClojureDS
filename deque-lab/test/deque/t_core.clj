(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque] ))

(facts "about this lab"
  (fact "the student started the lab"
        (+ 1 2)  => 3))

(facts "About Make Deque"
       (fact "It makes a nice empty Deque list"
             (make-deque) => ))
