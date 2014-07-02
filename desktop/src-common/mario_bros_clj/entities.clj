(ns mario-bros-clj.entities
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]
            [mario-bros-clj.utils :refer :all]))

(defn create-mario
  []
  (let [stand (texture "mario-stand.png")
        jump (texture "mario-jump.png")
        walk [(texture "mario-walk1.png")
              (texture "mario-walk2.png")
              (texture "mario-walk3.png")]]
    (assoc stand
           :stand-right stand
           :stand-left (texture stand :flip true false)
           :jump-right jump
           :jump-left (texture jump :flip true false)
           :walk-right (animation duration
                                  walk
                                  :set-play-mode (play-mode :loop-pingpong))
           :walk-left (animation duration
                                 (map #(texture % :flip true false) walk)
                                 :set-play-mode (play-mode :loop-pingpong))
           :width 1
           :height (/ 26 18)
           :x-velocity 0
           :y-velocity 0
           :x 8
           :y 5
           :player? true
           :can-jump? false
           :direction :right)))

(defn move
  [screen entity]
  (let [x-velocity (get-x-velocity entity)
        y-velocity (+ (get-y-velocity entity) gravity)
        x-change (* x-velocity (:delta-time screen))
        y-change (* y-velocity (:delta-time screen))]
    (if (or (not= 0 x-change) (not= 0 y-change))
      (assoc entity
             :x-velocity (decelerate x-velocity)
             :y-velocity (decelerate y-velocity)
             :x-change x-change
             :y-change y-change
             :x (+ (:x entity) x-change)
             :y (+ (:y entity) y-change)
             :can-jump? (if (> y-velocity 0) false (:can-jump? entity)))
      entity)))

(defn animate
  [screen {:keys [x-velocity y-velocity
                  stand-right stand-left
                  jump-right jump-left
                  walk-right walk-left] :as entity}]
  (let [direction (get-direction entity)]
    (merge entity
           (cond
             (not= y-velocity 0)
             (if (= direction :right) jump-right jump-left)
             (not= x-velocity 0)
             (if (= direction :right)
               (animation->texture screen walk-right)
               (animation->texture screen walk-left))
             :else
             (if (= direction :right) stand-right stand-left))
           {:direction direction})))

(defn prevent-move
  [screen entity]
  (let [old-x (- (:x entity) (:x-change entity))
        old-y (- (:y entity) (:y-change entity))
        entity-x (assoc entity :y old-y)
        entity-y (assoc entity :x old-x)
        up? (> (:y-change entity) 0)]
    (merge entity
           (when (get-touching-tile screen entity-x "walls")
             {:x-velocity 0 :x-change 0 :x old-x})
           (when (get-touching-tile screen entity-y "walls")
             {:y-velocity 0 :y-change 0 :y old-y :can-jump? (not up?)}))))
