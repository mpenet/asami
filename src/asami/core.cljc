(ns ^{:doc "A storage implementation over in-memory indexing. Includes full query engine."
      :author "Paula Gearon"}
    asami.core
    (:require [clojure.string :as str]
              [asami.graph :as gr]
              [asami.index :as mem]
              [asami.multi-graph :as multi]
              [asami.query :as query]
              [asami.internal :as internal]
              [naga.store :as store :refer [Storage StorageType]]
              [naga.storage.store-util :as projection]
              [naga.store-registry :as registry]
              #?(:clj  [schema.core :as s]
                 :cljs [schema.core :as s :include-macros true])
              #?(:clj [clojure.core.cache :as c])))

(defn assert-data
  [graph data]
  (query/add-to-graph graph data))

(defn retract-data
  [graph data]
  (query/delete-from-graph graph data))

(s/defn q
  [query & inputs]
  (query/query-entry query mem/empty-graph inputs))
