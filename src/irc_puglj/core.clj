(ns irc-puglj.core
  (:require [puglj.lobby :as lobby]
            [puglj.game :as game])
  (:gen-class))

(def base-state {:game nil :lobby {:captains [] :pool {}}})

(def state (atom base-state))

(defn privmsg-in-channel
  [state nick msg]
  (let [[command arg-str] (clojure.string/split msg #" " 2)]
    (case command
      "!add"
      [nil (update-in state [:lobby :pool] lobby/add-player nick (set (map (comp keyword clojure.string/lower-case) (clojure.string/split arg-str #" " 2))))]
      "!remove"
      [nil state]
      "!need"
      [nil state]
      "!list"
      [nil state]
      "!what-is"
      [nil state]
      "!pick"
      [nil state])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
