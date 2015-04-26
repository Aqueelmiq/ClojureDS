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
