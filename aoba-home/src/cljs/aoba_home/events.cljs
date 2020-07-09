(ns aoba-home.events
  (:require
   [re-frame.core :as re-frame]
   [aoba-home.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(re-frame/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(re-frame/reg-event-db
  ::set-active-panel
  (fn-traced [db [_ active-panel]]
             (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
  ::toggle
  (fn [db [_ flag]]
    (assoc db flag (not (flag db)))))

(re-frame/reg-event-db
  ::modal-image
  (fn-traced [db [_ image]]
             (assoc db :modal-image image)))

(re-frame/reg-event-db
  ::clear
  (fn-traced [db [_ key]]
             (assoc db key nil)))
