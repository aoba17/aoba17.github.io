(ns aoba-home.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [aoba-home.subs :as subs]
   ))


;; home

(def clojure-official {:url "https://clojure.org/" :name "Clojure"})
(def clojurescript-official {:url "https://clojurescript.org/" :name "ClojureScript"})
(def re-frame-official {:url "https://day8.github.io/re-frame/" :name "re-frame"})
(def genegacha-techs [clojurescript-official
                      re-frame-official
                      {:url "http://quil.info/" :name "Quil"}])
(def dmm-gacha-techs [clojure-official
                      clojurescript-official
                      re-frame-official
                      {:url "https://github.com/ring-clojure/ring" :name "Ring"}
                      {:url "https://affiliate.dmm.com/api/" :name "DMM Webサービス"}])
(def br [:br])

(defn home-title []
  [re-com/title
   :label "aoba17"
   :level :level1])

(defn url-link
  ""
  [url label]
  [:span
   [re-com/hyperlink-href
    :label label
    :href url
    :target "_blank"]])

(defn work-panel
  ""
  [name url techs]
  [re-com/v-box
   :gap "0.4em"
   :class "work-panel"
   :children [[url-link
               url
               [re-com/title
                :label name
                :level :level3]]
              [re-com/p {:class "description"}
               "This space is for the descriptions of the app."
               br
               "Techs :"
               (for [{:keys [url name]} techs]
                 [url-link url name])]]])

(defn headline
  ""
  [label]
  [re-com/h-box
   :align :center
   :children [[:i.fas.fa-caret-right.fa-2x]
              [re-com/title
               :label label
               :level :level2
               :class "headline"]]])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :class "home-panel"
   :children [[home-title]
              [headline "公開中のサービス"]
              [work-panel
               "ジェネラティブガチャガチャシミュレーター"
               "https://www.genegacha.com"
               genegacha-techs]
              [work-panel
               "出会って4秒でサンプル動画"
               "https://av.genegacha.com"
               dmm-gacha-techs]]])


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
