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

 (defn get-root "Returns bst root from BST"
   [bst]
   (:root bst)
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

(defn find-hlp
  [bst look-key]
  (cond (nil? bst) nil
        (= (:key bst) look-key) (:value bst)
        (< (:key bst) look-key) (find-hlp (:right bst) look-key)
        :else (find-hlp (:left bst) look-key)))

(defn find-hlp2
  [bst look-value]
  (cond (nil? bst) nil
        (= (:value bst) look-value) (:key bst)
        :else (if (nil? (find-hlp2 (:left bst) look-value)) (find-hlp2 (:right bst) look-value) (find-hlp2 (:left bst) look-value))
        ))

(defn find "Look for a key and return the corresponding value."
  [bt look-key]
  (find-hlp (:root bt) look-key)
  )

(defn find-key "Look for a value and return the corresponding key."
  [bt look-value]
  (find-hlp2 (:root bt) look-value)
  )
;; # Delete
;;
;; Similiarly, we have two versions of delete.  Please use the predecessor node if
;; you need to delete a child with two elements.

(defn predec
  [bst victim]
  (cond (nil? (:right bst)) bst
        (< (:key bst) victim) (predec (:right bst) victim)
        :else bst))

(defn pr-help
  [bst victim]
  (cond (nil? (:right bst)) nil
        (< (:key bst) victim) (BNode. (:left bst) (:key bst) (:value bst) (pr-help (:right bst) victim))
        :else nil))

(defn dlt-hlp
  [bst value]
  (cond (and (nil? (:left bst)) (nil? (:right bst))) nil
        (nil? (:left bst)) (:right bst)
        (nil? (:right bst)) (:left bst)
        :else (BNode. (pr-help (:left bst) value) (:key (predec (:left bst) value)) (:value (predec (:left bst) value)) (:right bst))))

(defn my-del
  [bst victim]
  (cond (nil? bst) nil
        (= (:key bst) victim) (dlt-hlp bst victim)
        (< (:key bst) victim) (BNode. (:left bst) (:key bst) (:value bst) (my-del (:right bst) victim))
        :else (BNode. (my-del (:left bst) victim) (:key bst) (:value bst) (:right bst))))

(defn my-del2
  [bst victim]
  (cond (nil? bst) nil
        (= (:value bst) victim) (dlt-hlp bst victim)
        :else (if (nil? (my-del2 (:left bst) victim)) (BNode. (:left bst) (:key bst) (:value bst) (my-del2 (:right bst) victim)) (BNode. (my-del2 (:left bst) victim) (:key bst) (:value bst) (:right bst)))
        ))

(defn delete [bst victim]
  (BST. (my-del (:root bst) victim) (- (:size bst) 1))
  )

(defn delete-value [bst victim]
  (BST. (my-del2 (:root bst) victim) (- (:size bst) 1))
  )

;; # Map Tree
;;
;; This function takes a tree t and maps a function f over it.
;; If your tree is ((x 3 x) 5 ((x 7 x) 6 x)), then (map-tree t inc)
;; will return ((x 4 x) 6 ((x 8 x) 7 x))

(defn my-map
  [t f]
  (cond (nil? t) nil
        (and (nil? (:left t)) (nil? (:right t))) (BNode. nil (:key t) (f (:value t)) nil)
        (nil? (:left t)) (BNode. nil (:key t) (f (:value t)) (my-map (:right t) f))
        (nil? (:right t)) (BNode. (my-map (:left t) f) (:key t) (f (:value t)) nil)
        :else (BNode. (my-map (:left t) f) (:key t) (f (:value t)) (my-map (:right t) f))))

(defn map-tree
  [t f]
  (BST. (my-map (:root t) f) (:size t))
  )
