(defproject mario-bros-clj "0.0.1-SNAPSHOT"
  :description "mario bros clj"
  :dependencies [[com.badlogicgames.gdx/gdx "1.2.0"]
                 [com.badlogicgames.gdx/gdx-backend-robovm "1.2.0"]
                 [com.badlogicgames.gdx/gdx-box2d "1.2.0"]
                 [com.badlogicgames.gdx/gdx-bullet "1.2.0"]
                 [org.clojure/clojure "1.6.0"]
                 [play-clj "0.3.8"]]
  :plugins [[lein-fruit "0.2.0"]]
  :source-paths ["src/clojure" "../desktop/src-common"]
  :java-source-paths ["src/java"]
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :ios {:robovm-path "path/to/robovm"
        :robovm-opts ["-forcelinkclasses" "mario_bros_clj.**:clojure.**:com.badlogic.**:play_clj.**"
                      "-libs" "libs/libObjectAL.a:libs/libgdx.a:libs/libgdx-box2d.a:libs/libgdx-bullet.a"
                      "-frameworks" "UIKit:OpenGLES:QuartzCore:CoreGraphics:OpenAL:AudioToolbox:AVFoundation"
                      "-resources" "../desktop/resources/**"]}
  :aot :all
  :main mario_bros_clj.core.IOSLauncher)
