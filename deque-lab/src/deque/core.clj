(ns deque.core)

(defrecord Deque [front back size])

;; # Your Work

(defn make-deque
  "Create an empty deque."
  []
  (Deque. '() '() 0))

(defn deque-size
  "Return the size of a deque."
  [dq]
  (:size dq))

(defn push-front
  "Adds an element to the front of the deque."
  [dq elt]
  (let [{:keys [front back size]} dq]
    (Deque. (cons elt (:front dq)) (:back dq) (inc size))))

(defn push-back
  "Adds an element to the back fo the deque."
  [dq elt]
  (let [{:keys [front back size]} dq]
    (Deque. (:front dq) (cons (:back dq) elt) (inc size))))

(defn flip-front
  "Flip the back list to the front list, if necessary."
  [dq]
  (let [{:keys [front back size]} dq]
    (cond (empty? (:front dq)) (Deque. (reverse (:back dq)) '() (:size dq))
          :else dq))
)

(defn flip-back
  "Flip the front list to the back list, if necessary."
  [dq]
  (let [{:keys [front back size]} dq]
    (cond (empty? (:back dq)) (Deque. '() (reverse (:front dq)) (:size dq))
                  :else dq))
)

(defn front
  "Return the front element of the deque.  May cause a flip."
  [dq]
  (let [{:keys [front back size]} dq]
    (:first (:front (flip-front dq))))
)

(defn back
  "Return the back element of the deque.  May cause a flip."
  [dq]
  (let [{:keys [front back size]} dq]
    (:first (:back (flip-back dq))))
)

(defn pop-front
  "Pops/dequeues an element from the front of the deque."
  [dq]
  (let [{:keys [front back size]} dq]
        (Deque. (:rest front) back (- size 1))))

(defn pop-back
  "Pops/dequeues an element from the back of the deque."
  [dq]
  (let [{:keys [front back size]} dq]
        (Deque. front (:rest back) (- size 1))))

