(ns aoba-home.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  ;; [:body {:color "red"}]
  ;; [:.level1 {:color "green"}]
  [:p
   [:span {:margin-left "0.7em"}]]
  [:i {:padding-top "0.4em"
       :padding-left "0.3em"}]
  [:.home-panel {:margin "1em"}]
  [:.headline {:margin-left "0.5em"}]
  [:.work-panel {:margin-left "3em"}]
  [:.description {:margin-left "0.5em"}]
  )
