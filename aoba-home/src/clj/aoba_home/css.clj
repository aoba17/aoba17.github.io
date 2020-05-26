(ns aoba-home.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  ;; [:body {:color "red"}]
  ;; [:.level1 {:color "green"}]
  [:p
   [:span {:margin-left "0.7em"}]])
