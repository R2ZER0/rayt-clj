(ns rayt.core
  (:require [clojure.string :as string])
  (:gen-class))

; Vector functions

(def zerov [0.0 0.0 0.0])

(defn vecbinop [f] 
	(fn [a b]
		(+ 	(f (nth a 0) (nth b 0))
	   		(f (nth a 1) (nth b 1))
	   		(f (nth a 2) (nth b 2)))))

(defn vecunwrap [f]
	(fn [v]
		(f (nth v 0) (nth v 1) (nth v 2))))

(defn vecbinunwrap [f]
	(fn [a b]
		(f (nth a 0) (nth a 1) (nth a 2) (nth b 0) (nth b 1) (nth b 2))))

(defn vecscalarop [f] (fn [v s] (map v #(f % s))))

(def *** (vecbinop *))
(def *div* (vecbinop /))
(def *+* (vecbinop +))
(def *-* (vecbinop -))
(def ** (vecscalarop *))
(def *div (vecscalarop /))
(def *+ (vecscalarop +))
(def *- (vecscalarop -))

(defn *x [v] (nth v 0))
(defn *y [v] (nth v 1))
(defn *z [v] (nth v 2))

(def cross (vecbinunwrap 
	(fn [a1 a2 a3 b1 b2 b3] 
		[
			(- (* a2 b3) (* a3 b2))
			(- (* a3 b1) (* a1 b3))
			(- (* a1 b2) (* a2 b1))
		])))
(def *x* cross)

(def makeunit (vecunwrap
	(fn [a b c]
		(let [factor (Math/sqrt (+ (* a a) (* b b) (* c c)))]
			(map #(/ % factor) [a b c])))))

; The actual raytracer

(defn raytrace [vwidth vheight] (
	(let [bottom-left-corner [-2.0 -1.0 1.0]
		  xv (/) ])))

; PPM Output

(defn makeppm [width height colours]
	(str "P3\n" width " " height "\n255\n"
		(string/join \newline (map (fn [rgb]
				(str (*x rgb) \space (*y rgb) \space (*z rgb)))
			colours))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println "Dot " (*** [1 2 3] [4 5 6]))
  (println "Cross " (*x* [1 2 3] [4 5 6]))
  (println (makeppm 2 2 [[1 1 1] [2 2 2] [3 3 3] [4 4 4]])))
