(ns aoba-home.views
  (:require
   [reagent.core :as r]
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [aoba-home.subs :as subs]
   [aoba-home.events :as events]))

(defonce window-size
  (let [a (r/atom {:width  (.-innerWidth js/window)
                   :height (.-innerHeight js/window)})]
    (.addEventListener js/window "resize"
                       (fn [] (reset! a {:width  (.-innerWidth  js/window)
                                        :height (.-innerHeight js/window)})))
    a))

;; home

(def clojure-official
  {:url "https://clojure.org/" :name "Clojure"})

(def clojurescript-official
  {:url "https://clojurescript.org/" :name "ClojureScript"})

(def re-frame-official
  {:url "https://day8.github.io/re-frame/" :name "re-frame"})

(def quil-official
  {:url "http://quil.info/" :name "Quil"})

(def genegacha-techs
  [clojurescript-official
   re-frame-official
   quil-official])

(def generative-design-techs
  [clojure-official
   quil-official])

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

(def aoba-home-desc
  [:span "このページ。"
   [url-link "https://github.com/aoba17/aoba17.github.io" [:span "ソースコード"]]])

(def generative-design-desc
  "『Generative Design』のサンプルコードのClojure版。")

(def genegacha-desc
  [:span
   "re-frame習得のために作成。"
   [url-link "https://github.com/ring-clojure/ring" [:span "ring"]]
   "などを使用して作成していたWebアプリケーションを書き換えてSPA化した。その名の通りガチャガチャのシミュレーター。"])

(def work-list
  [{:title       "ポートフォリオ"
    :url         "#/"
    :url-target  "_self"
    :description aoba-home-desc
    :techs       aoba-home-techs}
   {:title       "generative-design-clojure"
    :url         "https://github.com/aoba17/generative-design-clojure"
    :description generative-design-desc
    :techs       generative-design-techs}
   {:title       "ジェネラティブガチャガチャシミュレーター"
    :url         "https://www.genegacha.com"
    :description genegacha-desc
    :techs       genegacha-techs}])

(def br
  [:br])

(defn home-title []
  [re-com/h-box
   :children [[re-com/title
               :label [:div
                       "小山貴史"
                       [url-link
                        "https://github.com/aoba17"
                        [:i.fab.fa-github]]]
               :level :level1
               :class "unselectable"]
              ]])

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

(defn env-panel [label desc]
  [re-com/v-box
   :gap "0.4em"
   :children[[re-com/h-box
              :align :center
              :gap "3px"
              :children [[:i.fas.fa-circle]
                         [re-com/title
                          :label label
                          :level :level3]]]
             [re-com/p {:class "description"}
              desc]]])

(defn environment []
  [:div.contents-box
   [env-panel
    "Machine"
    "個人開発では MacBook Air を使用中。"]
   [env-panel
    "Text Editer"
    "CiderやClojure LSPなどのClojure開発用パッケージを導入したSpacemacs (develop branch) を使用。"]
   [env-panel
    "Infrastructure"
    "自作アプリ公開のためのWebサーバーやCDN、ドメイン管理にAWSを使用。アプリのサーバーへのデプロイに構成管理ツールAnsibleを使用。"]
   [env-panel
    "Clojure Project"
    "主にleiningenのテンプレートプロジェクトから開発を始める事が多い。デプロイの際もleiningenを用いてプロジェクトをjarファイルに変換。"]])

;; (def graphic-list
;;   [{:src       "images/member-color.png"
;;     :thumbnail "images/member-color_tn.jpg"
;;     :title     "Hello! Plot ～メンバーカラー～"
;;     :date      "2020"
;;     :width     1750
;;     :height    1750}])

;; (defn graphic-modal
;;   ""
;;   [{:keys [src width height title date]}]
;;   [re-com/modal-panel
;;    :backdrop-on-click #(re-frame/dispatch [::events/clear :modal-image])
;;    :backdrop-color "orange"
;;    :child
;;    [re-com/v-box
;;     :align :center
;;     :children
;;     [[:img
;;       (merge
;;         {:src      src
;;          :on-click #(re-frame/dispatch [::events/clear :modal-image])}
;;         (if (>= (/ width (:width @window-size))
;;                 (/ height (:height @window-size)))
;;           {:width (str (min (* (:width @window-size) 0.9) width) "px")}
;;           {:height (str (min (* (:height @window-size) 0.85) height) "px")}))]
;;      [re-com/title :label title :level :level3]
;;      [re-com/title :label date :level :level4]]]])

;; (defn graphics []
;;   (let [modal-image (re-frame/subscribe [::subs/any-key :modal-image])]
;;     [re-com/h-box
;;      :class "contents-box"
;;      :height "96px"
;;      :children
;;      [(for [{:keys [thumbnail] :as g} graphic-list]
;;         [re-com/box
;;          :height "96px"
;;          :child [:div
;;                  [:img.thumbnail
;;                   {:src      thumbnail
;;                    :height   "96px"
;;                    :width    "auto"
;;                    :on-click #(re-frame/dispatch
;;                                 [::events/modal-image g])}]]])
;;       (when @modal-image
;;         [graphic-modal @modal-image])]]))

(defn home-panel []
  (let [service-fold?     (re-frame/subscribe [::subs/any-key :service-fold?])
        ;;graphic-fold? (re-frame/subscribe [::subs/any-key :graphic-fold?])
        environment-fold? (re-frame/subscribe [::subs/any-key :environment-fold?])]
    [re-com/v-box
     :gap "1em"
     :class "home-panel"
     :children [[home-title]
                [headline "つくったもの" service-fold? :service-fold?]
                (if-not @service-fold? [services])
                [headline "開発環境" environment-fold? :environment-fold?]
                (if-not @environment-fold? [environment])]]))

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
     :width "600px"
     :height "100%"
     :children [[panels @active-panel]]]))
