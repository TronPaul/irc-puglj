(ns irc-puglj.core-test
  (:require [clojure.test :refer :all]
            [irc-puglj.core :refer :all]))

(def nick "name")

(deftest add-test
  (testing "Add command adds player to state"
    (is (= [nil {:game nil :lobby {:captains #{} :pool {:scout #{nick}}}}]
          (privmsg-in-channel base-state nick "!add scout")))))

;todo move captain state down to puglj.lobby
(deftest add-captain-test
  (testing "Add command lets players add as captain"
    (is (= [nil {:game nil :lobby {:captains #{nick} :pool {:scout #{nick}}}}]
          (privmsg-in-channel base-state nick "!add scout captain")))))

(deftest add-captain-without-class-test
  (testing "Cannot add as only a captain"
    (is (thrown? AssertionError (privmsg-in-channel base-state nick "!add captain")))))

(deftest remove-test
  (testing "Remove removes player from lobby"
    (is (= [nil base-state]
          (privmsg-in-channel {:game nil :lobby {:captains #{} :pool {:scout #{nick}}}} nick "!remove")))))
