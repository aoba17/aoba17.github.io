(ns aoba-home.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [aoba-home.subs :as subs]
   ))


;; home

(defn home-title []
  [re-com/title
   :label "aoba17"
   :level :level1])

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn url-link
  ""
  [url label]
  [:span
   [re-com/hyperlink-href
    :label label
    :href url
    :target "_blank"]])

(def br [:br])

(def clojure-official {:url "https://clojure.org/" :name "Clojure"})
(def clojurescript-official {:url "https://clojurescript.org/" :name "ClojureScript"})
(def re-frame-official {:url "https://day8.github.io/re-frame/" :name "re-frame"})

(def dmm-gacha-techs [clojure-official
                      clojurescript-official
                      re-frame-official
                      {:url "https://github.com/ring-clojure/ring" :name "Ring"}
                      {:url "https://affiliate.dmm.com/api/" :name "DMM Webサービス"}])

(defn work-panel
  ""
  [name url techs]
  [re-com/v-box
   :gap "1em"
   :class "work-panel"
   :children [[url-link
               url
               [re-com/title
                :label name
                :level :level3]]
              [re-com/p
               "This space is for the descriptions of the app."
               br
               "Techs :"
               (for [{:keys [url name]} techs]
                 [url-link url name])]]])


(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :class "home-panel"
   :children [[home-title]
              [re-com/title
               :label "公開中のサービス"
               :level :level2]
              [work-panel
               "出会って4秒でサンプル動画"
               "https://av.genegacha.com"
               dmm-gacha-techs]
              [link-to-about-page]]])


;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title]
              [link-to-home-page]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
