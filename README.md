mario-bros-clj
==============

A mario bros game written in clojure using play-clj that can run on desktop, android, or ios.



Prerequisites
-------------

Install leiningen

    $ brew install leiningen

Download robovm (ios)

    $ wget http://download.robovm.org/robovm-0.0.14.tar.gz

Configure robovm path (ios/project.clj)

    :robovm-path "path/to/robovm"



Running
-------

Desktop
    
    $ cd desktop
    $ lein run

Android

    $ cd android
    $ lein droid build
    $ lein droid install
    $ lein droid apk
    $ lein droid run

IOS

    $ cd ios
    $ lein fruit doall



Structure
---------

* `desktop/resources` Images, audio, and other files
* `desktop/src-common` Cross-platform game code
* `desktop/src` Desktop-specific code
* `android/src` Android-specific code
* `ios/src` iOS-specific code



Credit
------

Mario Bros images ripped by A. J. Nitro

Original level tile map from photonstorm/phaser-examples