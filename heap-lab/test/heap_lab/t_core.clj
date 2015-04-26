(ns heap-lab.t-core
  (:use midje.sweet)
  (:use [heap-lab.core])
  (:import [heap_lab.core Heap] ))


(facts "about numbers"
       (fact "one plus one is two."
             (+ 1 1)  =>  2)
       (fact "two plus one is three."
             (+ 2 1)  =>  3))

(facts "About Top function"
       (let [a (Heap. 10 [3 4 8 11 24 10 100 18 17 42 nil nil])]
         (fact "Top returns Correctly"
               (top a) => 3
               (top (make-heap 15)) => nil
               )))

(facts "About Add function"
       (let [a (Heap. 10 [3 4 8 11 24 10 100 18 17 42 nil nil])
             b (Heap. 0 [nil nil nil])
             c (Heap. 1 [7 nil nil])]
         (fact "Add Adds the element Correctly"
               (top (add a 1)) => 1
               (top (add a 2)) => 2
               (top (add (add a 2) 1)) => 1
               (add b 1) => (Heap. 1 [1 nil nil])
               (add c 1) => (Heap. 2 [1 7 nil])
               )
         (fact "Add increments size Correctly"
               (:size (add a 1)) => 11
               (:size (add b 1)) => 1
               (:size (add c 2)) => 2
               )))

(facts "About Delete function"
       (let [a (Heap. 10 [3 4 8 11 24 10 100 18 17 42 nil nil])
             b (Heap. 0 [nil nil nil])
             c (Heap. 1 [7 nil nil])
             d (Heap. 2 [2 7 nil])]
         (fact "Delete Deleted the element Correctly"
               (top (delete a)) => 4
               (top (delete (delete a))) => 8
               (delete b) => (Heap. 0 [nil nil nil])
               (delete c) => (Heap. 0 [nil nil nil])
               (delete d) => (Heap. 1 [7 nil nil]))
         
         (fact "Delete decrements size Correctly"
               (:size (delete a)) => 9
               (:size (delete c)) => 0
               )))

(facts "add function"
       (fact "increments size correctly"
             (add (make-heap 0) "data") => (Heap. 1 ["data"])
             (add (make-heap 1) "data") => (Heap. 1 ["data"])
             (add (make-heap 2) "data") => (Heap. 1 ["data" nil])))

(facts "moving up"
       (fact "move up moves repeatedly"
             (add (Heap. 6 [2 3 5 8 12 10]) 1) => (Heap. 7 [1 3 2 8 12 10 5 nil nil nil nil nil])))

(facts "percolate down"
       (fact "goes left and right"
             (add (Heap. 7 [2 4 3 9 13 11 6 nil nil nil nil nil]) 5) => (Heap. 8 [2 4 3 5 13 11 6 9 nil nil nil nil])
             (add (Heap. 7 [2 5 4 9 13 11 6 nil nil nil nil nil]) 3) => (Heap. 8 [2 3 4 5 13 11 6 9 nil nil nil nil]))
       (fact "swaps correctly"
             (add (Heap. 11 [2 3 8 7 4 10 9 11 9 6 5]) 1) => (Heap. 12 [1 3 2 7 4 8 9 11 9 6 5 10 nil nil nil nil nil nil nil nil nil nil])))

(facts "deletion"
       (fact "decrements size"
             (delete (Heap. 6 [2 3 5 8 12 10])) => (Heap. 5 [3 8 5 10 12 10])
             (delete (Heap. 9 [1 3 7 8 11 15 12 14 9])) => (Heap. 8 [3 8 7 9 11 15 12 14 9])
             (delete (Heap. 8 [3 8 7 9 11 15 12 14])) => (Heap. 7 [7 8 12 9 11 15 14 14])))
