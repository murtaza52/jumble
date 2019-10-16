;;; Directory Local Variables
;;; For more information see (info "(emacs) Directory Variables")

((clojure-mode
  (cider-clojure-cli-global-options . "-A:test:dev")))

((clojurescript-mode
  (cider-default-cljs-repl . shadow)
  (cider-shadow-default-options . "app")))
