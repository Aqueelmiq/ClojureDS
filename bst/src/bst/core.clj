(ns bst.core)

;; # Introduction
;;
;; In this lab you get to write a BST like the one we did in class, only
;; this time it is a dictionary structure and not a set.
;; As such, the "data" element from before will have a key and value instead.

(defrecord BST [root size])
(defrecord BNode [left key value right])

(defn make-node
  ([key value]  (make-node nil key value nil))
  ([left key value right] (BNode. left key value right))
  )

(defn make-tree []
  (BST. nil 0))

;; # Size
;;
;; A warmup function.

(defn size "Return the size of the tree."
  [t]
  (:size t)
  )

;; # Add
;;
;; The nodes will be entered into the tree on the basis of their key.
;; If someone tries to add a key that is already there, we replace the value
;; with the new entry.
(defn add-helper
  [bt nu-key nu-val]
  (cond (nil? bt) (BNode. nil nu-key nu-val nil)
        (= (:key bt) nu-key) (BNode. (:left bt) (nu-key) (nu-val) (:right bt))
        (> (:key bt) nu-key) (BNode. (add-helper (:left bt) nu-key nu-val) (:key bt) (:value bt) (:right bt)) 
        :else (BNode. (:left bt) (:key bt) (:value bt) (add-helper (:right bt) nu-key nu-val))
        ))

(defn add "Add a key and value to the BST."
  [bst nu-key nu-val]
  (BST. (add-helper (:root bst) nu-key nu-val) (+ (:size bst) 1))
  )

;; # Find
;;
;; We need two versions of find.  The first one takes a key and returns the
;; value.  The second takes a value and returns the key.  Note that the second
;; version of the function must search the entire tree!  If the search item is not
;; there, return nil.


(defn find "Look for a key and return the corresponding value."
  [bst look-key]
  (cond (nil? bst) nil
        (= (:key bst) look-key) (:value bst)
        (< (:key bst) look-key) (find (:right bst) look-key)
        :else (find (:left bst) look-key)))

(defn find-key "Look for a value and return the corresponding key."
  [bst look-value]
  (cond (nil? bst) nil
        (= (:value bst) look-value) (:key bst)
        :else (do (find-key (:left bst) look-value) (find-key (:right bst) look-value))))

;; # Delete
;;
;; Similiarly, we have two versions of delete.  Please use the predecessor node if
;; you need to delete a child with two elements.

(defn predec
  [bst victim]
  (cond (nil? (:right bst)) bst
        (< (:value bst) victim) (predec (:right bst) victim)
        :else bst
        ))

(defn dlt-hlp
  [bst value]
  (cond (and (nil? (:left bst)) (nil? (:right bst))) nil
        (nil? (:left bst)) (:right bst)
        (nil? (:right bst)) (:left bst)
        :else (predec (:left bst) value)))

(defn delete [bst victim]
  (cond (nil? bst) nil
        (= (:key bst) victim) (dlt-hlp bst victim)
        :else (do (delete (:left bst)) (delete (:right bst) victim))
        ))

(defn delete-value [bst victim]
  (cond (nil? bst) nil
        (= (:value bst) victim) (dlt-hlp bst victim)
        (< (:value bst) victim) (delete (:left bst) victim)
        :else (delete (:right bst) victim)
        ))

;; # Map Tree
;;
;; This function takes a tree t and maps a function f over it.
;; If your tree is ((x 3 x) 5 ((x 7 x) 6 x)), then (map-tree t inc)
;; will return ((x 4 x) 6 ((x 8 x) 7 x))

(defn map-tree
  [t f]
  (cond (nil? t) nil
        :else (BNode. (map-tree (:left t)) (:key t) (f (:data t)) (map-tree (:right t)))))
