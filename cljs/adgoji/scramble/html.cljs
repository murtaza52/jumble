(ns adgoji.scramble.html
  (:require [adgoji.scramble.ajax :refer [scramble]]
            [adgoji.scramble.logic :as l]
            [reagent.core :as r]))

(defn submit-button
  [app-state local-state]
  [:a.btn.btn-primary {:href "#"
                       :class (when (not (l/can-submit? local-state)) "disabled")
                       :role "button"
                       :aria-disabled (if (l/can-submit? local-state) false true)
                       :on-click #(scramble (get-in @local-state [:string :text])
                                            (get-in @local-state [:word :text])
                                            app-state)}
   "Scramble"])

(defn text-box
  [{:keys [k label-text placeholder-text state app-state]}]
  [:div.form-group
   [:label label-text]
   [:input.form-control {:id k
                         :type "text"
                         :placeholder placeholder-text
                         :value (-> @state k :text)
                         :on-change #(l/update-text % k state app-state)
                         :class (l/get-input-validation-class k state)}]
   [:small.form-text.text-muted {:class (l/get-help-text-validation-class k state)}
    "Please ensure the text contains only lower case characters."]])

(defn result-box
  [state]
  [:div.alert {:class (l/get-alert-box-class state) :role "alert"} (l/get-alert-box-text state)])

(def initial-form-state {:string {:text "" :valid? false :to-validate? false}
                         :word {:text "" :valid? false :to-validate? false}})

(defn scramble-form
  [app-state]
  (let [local-state (r/atom initial-form-state)]
    (fn []
      [:div.jumbotron.d-flex.align-items-center.justify-content-center
       [:div.container
        [:div.row
         [:div.col-xs-11
          [:form.justify-content-center
           [text-box {:state local-state
                      :label-text "String to Scramble"
                      :placeholder-text "String .."
                      :k :string
                      :app-state app-state}]
           [text-box {:state local-state
                      :label-text "Word to Match"
                      :placeholder-text "Word .."
                      :k :word
                      :app-state app-state}]
           [result-box app-state]
           [submit-button app-state local-state]]]]]])))
