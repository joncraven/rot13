(ns rot13.core
  (:gen-class))

(defmacro unless [condition & body]
  `(if (not ~condition)
     (do ~@body)))

(defn rot13-char [chr]
  "Rot13 the given character if alphabetic, return the character otherwise."
  (let [ch (int chr)]
    (cond (and (>= ch (int \A))
               (<= ch (int \Z)))
          (char (+ (mod (+ (- (int ch)
                              (int \A))
                           13)
                        26)
                   (int \A)))
          (and (>= ch (int \a))
               (<= ch (int \z)))
          (char (+ (mod (+ (- (int ch)
                              (int \a))
                           13)
                        26)
                   (int \a)))
          :else chr)))

(defn rot13-str [s]
  (apply str (map rot13-char s)))

(defn usage []
  (println "Usage: rot13-util <inputfile> <outputfile>"))

(defn -main 
  "Usage: rot13-util <inputfile> <outputfile>. Writes the contents of
  input into output using the Caesar cypher."
  [& args]
  (unless (= (count args) 2)
    (do (usage)
        (System/exit 1)))
  (let [[input output] args]
    (try
      (when (.exists (java.io.File. output))
        (println "Error: Output file already exists.")
        (System/exit 2))
      (with-open [reader (clojure.java.io/reader input)
                  writer (clojure.java.io/writer output)]
          (doseq [line (line-seq reader)]
            (.write writer (str (rot13-str line) \newline))))
        (catch java.io.FileNotFoundException _
          (println "Error: Input file cannot be opened.")
          (System/exit 3)))))
