(defproject irc-puglj "0.0.1-SNAPSHOT"
  :description "Irc bot for TF2 pugs"
  :url "http://github.com/TronPaul/irc-puglj"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [puglj "0.0.1"]]
  :main ^:skip-aot irc-puglj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
