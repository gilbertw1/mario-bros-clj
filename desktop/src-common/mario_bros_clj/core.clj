(ns mario-bros-clj.core
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]
            [mario-bros-clj.entities :refer :all]
            [mario-bros-clj.utils :refer :all]))

(declare main-screen)

(def the-game (atom nil))

(defn update-screen!
  [screen entities]
  (doseq [entity entities]
    (when (:player? entity)
      (position! screen (:x entity) (/ vertical-tiles 2))
      (when (< (:y entity) (- (:height entity)))
        (set-screen! @the-game main-screen))))
  entities)

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (->> (orthogonal-tiled-map "level.tmx" (/ 1 16))
         (update! screen :camera (orthographic) :renderer))
    (create-mario))
  
  :on-render
  (fn [screen entities]
    (clear! 0.5 0.5 1 1)
    (->> entities
         (map (fn [entity]
                (->> entity
                     (move screen)
                     (prevent-move screen)
                     (animate screen))))
         (render! screen)
         (update-screen! screen)))
  
  :on-resize
  (fn [screen entities]
    (height! screen vertical-tiles)))

(defgame mario-bros-clj
  :on-create
  (fn [this]
    (reset! the-game this)
    (set-screen! this main-screen)))