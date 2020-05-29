(ns aoba-home.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:background-color :FloralWhite}]
  [:.level3 {:color :DarkOliveGreen}]
  [:p {:border-left  "solid"
       :border-color :DarkOrange
       :padding-left "1em"}
   [:span {:margin-left "0.7em"}]]
  [:.home-panel {:margin "1em"}]
  [:.headline {:margin-left "0.5em"}]
  [:.contents-box {:margin-left "3em"}]
  [:.description {:margin-left "0.5em"}]
  [:.unselectable {:user-select "none"}
   [:i {:padding-top  "8px"
        :padding-left "8px"}]
   [:i.fa-caret-right {:padding-left "15px"
                       :color        :Orange}]
   [:i.fa-caret-down {:color :Tomato}]]
  [:a :a:hover
   {:color :DarkOliveGreen}
   [:i {:color :DarkSlateGrey}]]
  [:i.fas.fa-circle :i.fas.fa-circle:hover
   {:margin-top  "3.7px"
    :margin-left "2px"
    :color       :YellowGreen}])
