(ns bst.t-core
  (:use midje.sweet)
  (:use [bst.core])
  (:import [bst.core BST] ))

(facts "About add function"
       (let [bt1 (BST. (make-node 1 10) 1)
       ])
       (fact "add function correctly adds an element"
             (-> :root :left :value (add bt1 -2 20))  => 20
             (-> :root :right :value (add bt1 2 30))  => 30)
             (:value (:root (add bt1 1 11))) => 11
       (fact "add function increments the size properly"
             (:size (add bt1 -2 20)) => 2
             (:size (add bt1 1 11)) => 1
             ))

(facts "About find and find-value function"
       (let [bt1 (BST. (make-node 1 10) 1)])
       (fact "add function correctly adds an element"
             (-> :root :left :value (add bt1 -2 20))  => 20
             (-> :root :right :value (add bt1 2 30))  => 30)
       (:value (:root (add bt1 1 11))) =>11
       (fact "add function increments the size properly"
             (:size (add bt1 -2 20)) =>2
             (:size (add bt1 1 11)) => 1))

(facts "About delete and delete-value function"
       (let [bt1 (BST. (make-node 1 10) 1)])
       (fact "add function correctly adds an element"
             (-> :root :left :value (add bt1 -2 20))  => 20
             (-> :root :right :value (add bt1 2 30))  => 30)
       (:value (:root (add bt1 1 11))) =>11
       (fact "add function increments the size properly"
             (:size (add bt1 -2 20)) =>2
             (:size (add bt1 1 11)) => 1))

(facts "About map function function"
       (let [bt1 (BST. (make-node 1 10) 1)
             bt2 (add (add bt1 -1 20) 2 39)
             ])
       (fact "Map tree correctly increments items"
             (:value (:left (:root (map-tree bt2 inc)))) => 21
             (:value (:right (:root (map-tree bt2 inc)))) => 40
             (:value (map-tree (:root bt2) inc)) => 11
             ))

(facts "about this lab"
  (fact "the student started the lab."
        (+ 1 2)  => 3))
