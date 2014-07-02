(ns mario-bros-clj.core.desktop-launcher
  (:require [mario-bros-clj.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. mario-bros-clj "mario-bros-clj" 800 600)
  (Keyboard/enableRepeatEvents true))
