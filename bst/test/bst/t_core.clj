 (ns bst.t-core
  (:use midje.sweet)
  (:use [bst.core])
  (:import [bst.core BST] ))

(facts "About add function"
       (let [bt1 (BST. (make-node 1 10) 1)]
         (fact "add function correctly adds an element"
               (:value (:left (:root (add bt1 -1 20)))) => 20
               (:value (:right (:root (add bt1 2 30)))) => 30
               (:value (:root (add bt1 1 11))) => 11
               )
         (fact "add function increments the size properly"
               (:size (add bt1 3 17)) => 2
               (:size (add bt1 1 11)) => 1)))

(facts "About find and find-value function"
       (let [bt1 (BST. (make-node 1 10) 1)
             bt2 (add (add bt1 -1 20) 2 39)]
         (fact "find function correctly finds an element"
               (find bt2 -1) => 20
               (find bt2 1) => 10
               (find bt2 2) => 39)
         (fact "find-key function correctly finds an element"
               (find-key bt2 10) => 1
               (find-key bt2 20) => -1
               (find-key bt2 39) => 2)))

(facts "About delete and delete-value function"
       (let [bt1 (BST. (make-node 20 20) 1)
             bt2 (add (add bt1 -1 21) 2 39)
             bt3 (add (add (add (add (add (add bt1 10 10) 30 30) 5 5) 11 11) 22 22) 36 36)]
         (fact "delete correctly deletes an element"
               (:key (:root (delete bt2 20))) => -1
               (:key (:root (delete bt2 -1))) => 20
               (:key (:root (delete bt2 2))) => 20
               (:key (:root (delete bt2 3))) => 20
               (:key (:left (:root (delete bt2 2)))) => -1
               (:key (:right (:root (delete bt2 20)))) => 2)
         (fact "delete decrements properly"
               (:size (delete bt2 20)) => 2
               (:size (delete bt2 100)) => 3
               (:size (delete bt2 -1)) => 2)
         (fact "delete is not good with many nodes"
               (:key (:root (delete bt3 20))) => 11
               (:key (:root (delete bt3 10))) => 20
               (:key (:root (delete bt3 30))) => 20
               (:key (:root (delete bt3 5))) => 20
               (:key (:left (:root (delete bt3 10)))) => 5
               (:key (:right (:root (delete bt3 30)))) => 22
               (:key (:right (:left (:root (delete bt3 20))))) => nil)
         (fact "delete-value correctly deletes an element"
               (:key (:root (delete-value bt2 20))) => -1
               (:key (:root (delete-value bt2 21))) => 20
               (:key (:root (delete-value bt2 39))) => 20
               (:key (:root (delete-value bt2 50))) => 20
               (:key (:left (:root (delete-value bt2 20)))) => nil
               (:key (:right (:root (delete-value bt2 21)))) => nil)
         (fact "delete-value is not good with many nodes"
               (:key (:root (delete-value bt3 20))) => 11
               (:key (:root (delete-value bt3 10))) => 20
               (:key (:root (delete-value bt3 30))) => 20
               (:key (:root (delete-value bt3 5))) => 20
               (:key (:left (:root (delete-value bt3 10 )))) => 5
               (:key (:right (:root (delete-value bt3 30)))) => 22
                      (:key (:right (:left (:root (delete-value bt3 20))))) => nil)
         (fact "delete-value decrements properly"
               (:size (delete-value bt2 20)) => 2
               (:size (delete-value bt2 100)) => 3
               (:size (delete-value bt2 39)) => 2)))

(facts "About map function function"
       (let [bt1 (BST. (make-node 1 10) 1)
             bt2 (add (add bt1 -1 20) 2 39)
             ]
         (fact "Map tree correctly increments items"
               (:value (:left (:root (map-tree bt2 inc)))) => 21
               (:value (:right (:root (map-tree bt2 inc)))) => 40
               (:value (:root (map-tree bt2 inc))) => 11)))

(facts "about this lab"
  (fact "the student started the lab."
        (+ 1 2)  => 3))
