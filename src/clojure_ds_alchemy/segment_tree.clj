(ns clojure-ds-alchemy.segment-tree)

(defn build-segment-tree
  "建树"
  [arr start end]
  (if (= start end)
    {:value start :left nil :right nil}
    (let [mid (quot (+ start end) 2)
          left-child (build-segment-tree arr start mid)
          right-child (build-segment-tree arr (inc mid) end)]
      {:value (+ (:value left-child) (:value right-child))
       :left left-child
       :right right-child})))

