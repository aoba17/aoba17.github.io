(ns aoba-home.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [aoba-home.subs :as subs]
   [aoba-home.events :as events]
   ))


;; home

(def clojure-official
  {:url "https://clojure.org/" :name "Clojure"})

(def clojurescript-official
  {:url "https://clojurescript.org/" :name "ClojureScript"})

(def re-frame-official
  {:url "https://day8.github.io/re-frame/" :name "re-frame"})

(def genegacha-techs
  [clojurescript-official
   re-frame-official
   {:url "http://quil.info/" :name "Quil"}])

(def dmm-gacha-techs
  [clojure-official
   clojurescript-official
   re-frame-official
   {:url "https://github.com/ring-clojure/ring" :name "Ring"}
   {:url "https://affiliate.dmm.com/api/" :name "DMM Webサービス"}])

(def aoba-home-techs
  [clojurescript-official
   re-frame-official])

(defn url-link
  ""
  [url label & [target]]
  [:span.url
   [re-com/hyperlink-href
    :label label
    :href url
    :target (or target "_blank")]])

(def genegacha-desc
  "ガチャガチャのシミュレーターとジェネラティブアートのマリアージュ。")

(def dmm-desc
  "FANZAのアダルトビデオの無料サンプル動画を漁るときに使われる18禁サイト。")

(def aoba-home-desc
  [:span "このページ。ソースコードは"
   [url-link "https://github.com/aoba17/aoba17.github.io" [:span "こちら"]]])

(def work-list
  [{:title       "aoba-home"
    :url         "#/"
    :url-target  "_self"
    :description aoba-home-desc
    :techs       aoba-home-techs}
   {:title       "出会って4秒でサンプル動画"
    :url         "https://av.genegacha.com"
    :description dmm-desc
    :techs       dmm-gacha-techs}
   {:title       "ジェネラティブガチャガチャシミュレーター"
    :url         "https://www.genegacha.com"
    :description genegacha-desc
    :techs       genegacha-techs}])

(def br
  [:br])

(defn home-title []
  [re-com/title
   :label "aoba17"
   :level :level1
   :class "unselectable"])

(defn work-panel
  ""
  [name url url-target description techs]
  [re-com/v-box
   :gap "0.4em"
   :children [[re-com/h-box
               :align :center
               :gap "3px"
               :children [[:i.fas.fa-circle]
                          [url-link
                           url
                           [re-com/title
                            :label name
                            :level :level3]
                           url-target]]]
              [re-com/p {:class "description"}
               description
               br
               "Techs :"
               (for [{:keys [url name]} techs]
                 [url-link url name])]]])

(defn headline
  ""
  [label fold? toggle-key]
  [re-com/h-box
   :align :center
   :class "unselectable"
   :attr {:on-click #(re-frame/dispatch [::events/toggle toggle-key])}
   :children [[:i.fas.fa-2x
               {:class (if @fold?
                         "fa-caret-right"
                         "fa-caret-down")}]
              [re-com/title
               :label label
               :level :level2
               :class "headline"]]])

(defn services
  ""
  []
  [:div.contents-box
   (for [{:keys [title url url-target description techs]} work-list]
     [work-panel title url url-target description techs])])

(defn sns-accounts
  ""
  []
  [re-com/h-box
   :class "contents-box"
   :gap "0.5em"
   :children [[url-link "https://twitter.com/takafumi_oy" [:i.fab.fa-twitter.fa-3x]]
              [url-link "https://github.com/aoba17" [:i.fab.fa-github.fa-3x]]
              [url-link "https://www.instagram.com/dope_oyakata/" [:i.fab.fa-instagram.fa-3x]]
              ;;[:i.fab.fa-youtube.fa-3x]
              ]])

(defn graphics
  ""
  []
  [:div.contents-box
   [:div "aaaa"]])

(defn home-panel []
  (let [service-fold? (re-frame/subscribe [::subs/any-key :service-fold?])
        sns-fold?     (re-frame/subscribe [::subs/any-key :sns-fold?])
        graphic-fold? (re-frame/subscribe [::subs/any-key :graphick-fold?])]
    [re-com/v-box
     :gap "1em"
     :class "home-panel"
     :children [[home-title]
                [headline "ぼくの各種アカウント" sns-fold? :sns-fold?]
                (if-not @sns-fold? [sns-accounts])
                ;; [headline "ぼくがつくったグラフィック" graphic-fold? :graphick-fold?]
                ;; (if-not @graphic-fold? [graphics])
                [headline "ぼくがつくったWebサービス" service-fold? :service-fold?]
                (if-not @service-fold? [services])]]))

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
    :home-panel  [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
