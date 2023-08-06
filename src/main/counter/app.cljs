(ns counter.app
  (:require [reagent.core :as r])
  (:require [reagent.dom :as rdom]))

(defonce counters (r/atom []))

(defn vec-delete [v i]
  (into (subvec v 0 i) (subvec v (inc i))))

(defn init []
  (println "App initialisation"))

(defn Counter [i counter]
  [:div
   counter
   " "
   [:button
    {:onClick (fn [] (swap! counters update i inc))}
    "+1"]
   [:button
    {:onClick (fn [] (swap! counters update i dec))}
    "-1"]
   [:button
    {:onClick (fn [] (swap! counters vec-delete i))}
    "X"]])

(defn Application []
  [:div
   (for [[i counter] (map vector (range)  @counters)]
     ^{:key (str i)} [Counter i counter])
   [:button {:onClick (fn [] (swap! counters conj 0))} "Add counter"]])

(rdom/render [Application] (js/document.getElementById "app"))
