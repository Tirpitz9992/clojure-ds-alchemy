(ns clojure-ds-alchemy.segment-tree)

(defn build-segment-tree
  "建树"
  [arr start end]
  (if (= start end)
    {:value start :left nil :right nil}
    (let [mid (quot (+ start end) 2)
          left-child (build-segment-tree arr start mid)
          right-child (build-segment-tree arr (inc mid) end)] ;向下递归
      {:value (+ (:value left-child) (:value right-child))
       :left left-child
       :right right-child})))

(defn query-range
  "查询给定区间的和"
  [node start end qs qe]
  (if (or (< qe start) (> qs end))
    0 ;如果需要查询qe < start 就是说在查询的区间外的话
    (if (and (<= qs start) (>= qe end))
      (:value node)
      (let [mid (quot (+ start end) 2)]
        (+ (query-range (:left node) start mid qs qe) ;继续递归
           (query-range (:right node) (inc mid) end qs qe))))))

(defn update-segment-tree
  [node  ]
  ;更新线段树
  )

(let [arr [1 2 3 4 5 6]
      n (count arr)
      segment-tree (build-segment-tree arr 0 (dec n))]
  (println "segment-tree" segment-tree)
  (println "区间和 1-3" (query-range segment-tree 0 (dec n) 1 3)))