(ns irc-puglj.core-test
  (:require [clojure.test :refer :all]
            [irc-puglj.core :refer :all]))

(def nick "name")

(deftest add-test
  (testing "Add command adds player to state"
    (is (= [nil {:game nil :lobby {:captains [] :pool {:scout #{"name"}}}}] (privmsg-in-channel base-state nick "!add scout")))))
