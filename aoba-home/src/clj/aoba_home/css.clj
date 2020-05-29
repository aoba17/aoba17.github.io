(ns aoba-home.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  ;; [:body {:color "red"}]
  ;; [:.level1 {:color "green"}]
  [:p
   [:span {:margin-left "0.7em"}]]
  [:i {:padding-top  "11px"
       :padding-left "8px"}]
  [:i.fa-caret-right {:padding-left "15px"}]
  [:.home-panel {:margin "1em"}]
  [:.headline {:margin-left "0.5em"}]
  [:.work-panel {:margin-left "3em"}]
  [:.description {:margin-left "0.5em"}]
  )
