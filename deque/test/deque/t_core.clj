(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque] ))

(facts "about this lab"
  (fact "the student started the lab"
        (+ 1 2)  => 3))

(facts "About Make Deque"
       (fact "It makes a nice empty Deque list"
             (:front (make-deque)) => '()
             (:back (make-deque)) => '()
             (:size (make-deque)) => 0
             ))

(facts "About Size of Deque"
       (let [q17 (Deque. '(1 2 3 4) '(10 9 8 7) 8)
             q18 (Deque. '() '(5 4 3 2) 4)
             q19 (Deque. '(3 4 5 6) '() 4)]
         (fact "It shows size  correctly"
               (deque-size q17) => 8
               (deque-size q18) => 4
               (deque-size q19) => 4
               )))

(facts "About Push-Front and Push-Back"
       (let [q1 (make-deque)
             q2 (push-front (make-deque) 10)
             q3 (push-back (make-deque) 20)]
         (fact "The push-front works correctly"
               (front (push-front q1 1)) => 1
               (front (push-front q2 11)) => 11
               (:size (push-front q1 1)) => 1
               (:size (push-front q2 11)) => 2
               )
         (fact "The push-back works correctly"
               (back (push-back q1 1)) => 1
               (back (push-back q2 11)) => 11
               (:size (push-back q1 1)) => 1
               (:size (push-front q2 11)) => 2
               )))

(facts "About Flip-Front and Flip-Back"
       (let [q4 (Deque. '(1 2 3 4) '(10 9 8 7) 8)
             q5 (Deque. '() '(5 4 3 2) 4)
             q6 (Deque. '(3 4 5 6) '() 4)]
         (fact "The flip-front works correctly"
               (:front (flip-front q4)) => '(1 2 3 4)
               (:front (flip-front q5)) => '(2 3 4 5)
               (:front (flip-front q6)) => '(3 4 5 6)
               (:back (flip-front q4)) => '(10 9 8 7)
               (:back (flip-front q5)) => '()
               (:back (flip-front q6)) => '())
         (fact "The flip-back works correctly"
               (:back (flip-back q4))=> '(10 9 8 7)
               (:back (flip-back q5))=> '(5 4 3 2)
               (:back (flip-back q6))=> '(6 5 4 3)
               (:front (flip-back q4)) => '(1 2 3 4)
               (:front (flip-back q5)) => '()
               (:front (flip-back q6)) => '()
               )))

(facts "About Front and Back"
       (let [q7 (Deque. '(1 2 3 4) '(10 9 8 7) 8)
             q8 (Deque. '() '(5 4 3 2) 4)
             q9 (Deque. '(3 4 5 6) '() 4)]
         (fact "The front works correctly"
               (front q7) => 1
               (front q8) => 2
               (front q9) => 3
               )
         (fact "The back works correctly"
               (back q7) => 10
               (back q8) => 5
               (back q9) => 6
               )))

(facts "About Pop-Front and Pop-Back"
       (let [q10 (Deque. '(1 2 3 4) '(10 9 8 7) 8)
             q11 (Deque. '() '(5 4 3 2) 4)
             q12 (Deque. '(3 4 5 6) '() 4)
             q20 (Deque. '() '() 0)]
         (fact "The pop-front works correctly"
               (:front (pop-front q10))=> '(2 3 4)
               (:front (pop-front q11))=> '(3 4 5)
               (:front (pop-front q12))=> '(4 5 6)
               (:size (pop-front q20)) => 0
               (:size (pop-front q10)) => 7
               )
         (fact "The pop-back works correctly"
               (:back (pop-back q10))=> '(9 8 7)
               (:back (pop-back q11))=> '(4 3 2)
               (:back (pop-back q12))=> '(5 4 3)
               (:size (pop-back q20)) => 0
               (:size (pop-back q10)) => 7)
         ))
