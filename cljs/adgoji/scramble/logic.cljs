(ns adgoji.scramble.logic)

(defn get-value
  [e]
  (-> e .-target .-value))

(defn validate
  [v]
  (if (re-matches #"[a-z]+" v)
    true
    false))

(defn can-submit?
  [state]
  (and (get-in @state [:string :valid?])
       (get-in @state [:word :valid?])))

(defn update-text
  [e k state app-state]
  (let [v (get-value e)]
    (swap! app-state dissoc :scramble? :error)
    (swap! state assoc-in [k :text] v)
    (swap! state assoc-in [k :valid?] (validate v))
    (swap! state assoc-in [k :to-validate?] true)))

(defn get-validation-class
  [valid-class invalid-class]
  (fn [k state]
    (when (get-in @state [k :to-validate?])
      (case (get-in @state [k :valid?])
        false invalid-class
        true valid-class))))

(def get-input-validation-class (get-validation-class "is-valid" "is-invalid"))

(def get-help-text-validation-class (get-validation-class nil "alert-danger"))

(defn get-alert-box-class
  [state]
  (cond
    (:error @state) "alert-danger"
    (true? (:scramble? @state)) "alert-success"
    (false? (:scramble? @state)) "alert-dark"
    :else "alert-info"))

(defn get-alert-box-text
  [state]
  (cond
    (:error @state) (:error @state)
    (true? (:scramble? @state)) "Bravo ! The scrambled string contains the word !!"
    (false? (:scramble? @state)) "Nice try, however the scrambled string does not contain the word."
    :else "ReSuLt - SuBmIt To ScRaMbLe AnD ChEcK"))
