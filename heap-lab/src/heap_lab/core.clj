(ns heap-lab.core)

;; # Array Based Heaps
;;
;; Just in time for thanksgiving, a simple lab about Heaps!
;;
;; We will use vectors to handle this, with a top-level record
;; to keep track of the vector and the size.

(defrecord Heap [size data])

;; We will initialize this using the `make-heap` function.

(defn make-heap
    "Creates an empty heap.  Specify the size for the data vector.
The vector will be populated with `nil`."
    [capacity]
    (Heap. 0 (apply vector (repeat capacity nil))))

;; To access the elements of the heap, we will use these functions
;; `get`, `left`, `right`, and `parent`.

(defn heap-get
  "Return the value of the heap vector at the given index.
Throws an exception if the index is out of the range.
this is part of the implementation, not for public consumption."
  [heap loc]
  (cond (>= loc (count (:data heap)))
        (throw (Exception. (str "Get called with " loc " but last vector slot is " (dec (count (:data heap))))))
        :else
        (get-in heap [:data loc])))

(defn heap-set
  "Set the value of the heap vector at the given index.
Throws an exception if the index is out of the range.
this is part of the implementation, not for public consumption."
  [heap loc value]
  (cond (>= loc (count (:data heap)))
        (throw (Exception. (str "Get called with " loc " but last vector slot is " (dec (count (:data heap))))))
        :else
        (assoc-in heap [:data loc] value)))

(defn heap-left
  "Return the left index."
  [loc]
  (inc (* loc 2)))

(defn heap-right
  "Return the right index."
  [loc]
  (+ 2 (* loc 2)))

(defn heap-parent
  "Return the parent index."
  [loc]
  (int (/ (dec loc) 2)))

;; Now it's time for your code!  You need these three, but you are welcome to
;; write helper functions if you want (e.g., `percolate-down`.)  Do **not** write
;; `midje` tests for them, because they are not part of the spec.

(defn swap [v i1 i2]
     (assoc v i2 (v i1) i1 (v i2)))

(defn top
  "Return the top element of a heap.
If the heap has no elements, return `nil`."
  [heap]
  (cond (= 0 (:size heap)) nil
        :else (first (:data heap))))

(defn precolate-up
  ([heap loc]
   (cond (< loc 1) heap
         (> (heap (heap-parent loc)) (heap loc)) (precolate-up (swap heap loc (heap-parent loc)) (heap-parent loc))
         :else heap)
   ))

(defn percolate-down
  ([heap loc]
   (cond (> loc (- (:size heap) 1)) (:data heap)
         (> ((:data heap) loc) ((:data heap) (heap-left loc))) (percolate-down (Heap. (:size heap) (swap (:data heap) loc (heap-left loc))))
         (> ((:data heap) loc) ((:data heap) (heap-right loc))) (percolate-down (Heap. (:size heap) (swap (:data heap) loc (heap-right loc))))
                  :else (:data heap))))

(defn delete
  "Deletes the first element of the heap.
Returns the new heap."
  [heap]
  (cond (= 0 (:size heap)) heap
        :else (Heap. (- (:size heap) 1) (percolate-down (Heap. (:size heap) (vec (rest (:data heap)))) 0))))

(defn add
  "Adds a new element to the heap.
If the data vector is too small, we resize it."
  [heap data]
  (cond (> (:size heap) (count (:data heap))) (add (Heap. (:size heap) (conj (:data heap) nil))) 
        :else (Heap. (+ 1 (:size heap)) (precolate-up (assoc (:data heap) (:size heap) data) (:size heap)))))
