(ns irc-puglj.core
  (:require [puglj.lobby :as lobby]
            [puglj.game :as game])
  (:gen-class))

(def base-state {:game nil :lobby {:captains #{} :pool {}}})

(def state (atom base-state))

(defn do-add
  [state nick classes]
  (update-in state [:lobby :pool] lobby/add-player nick classes))

(defn privmsg-in-channel
  [state nick msg]
  (let [[command arg-str] (clojure.string/split msg #" " 2)]
    (case command
      "!add"
      (let [args-kw-set (set (map (comp keyword clojure.string/lower-case) (clojure.string/split arg-str #" ")))]
        (if (contains? args-kw-set :captain)
          (do (assert (< 1 (count args-kw-set)))
            [nil (update-in (do-add state nick (disj args-kw-set :captain)) [:lobby :captains] conj nick)])
          [nil (do-add state nick args-kw-set)]))
      "!remove"
      [nil (update-in state [:lobby :pool] lobby/remove-player nick)]
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
