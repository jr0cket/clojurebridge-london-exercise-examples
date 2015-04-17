(ns clojure-bridge-exercises.exercises)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Introduction to Programming with Clojure


;; Some really basic concepts

;; Everything is a list in Clojure.  List stands for List Processing.  So what is a list and does it work

;; Really simplistic example - a shopping list

(shopping-in-tesco "eggs" "bacon" "cheese" "cerial" "milk")

;; We have a list of things we want to buy.  the first item in the list defines how we are going to get these things



;; EXERCISE: Basic arithmetic

;; - Take your height in feet and inches and convert it to inches using arithmetic in Clojure.
;; - Then convert that to centimeters. There are 2.54 centimeters in an inch.
;; - Lastly, ask two people near you for their height in centimeters. Find the average of your heights.

;; - Bonus: Convert that average back to feet and inches. The feet and the inches will be separate numbers. (quot x y) will give you the whole number part when dividing two numbers. (mod x y) will give you the remainder when dividing two numbers.

;; Define a persons height in feet and inches using a map and Clojure keywords for the keys
(def john {:feet 6 :inches 2})

;; Or to give this data more contenxt, an allow for further definitions of data for john, lets put the feet and inches data as the value for the height key
(def john {:height {:feet 6 :inches 2}})



(defn convert-feet-and-inches-to-feet
  "Convert feet and inches values to feet"
  [feet inches]
  (+ (* feet 12) inches))

(convert-feet-and-inches-to-feet (:feet (john :height))
                                 (:inches (john :height)))


(defn convert-inches-to-centimeters
  "Convert inches to centimeters"
  [inches]
  (* inches 2.54))

(convert-inches-to-centimeters 74)


;; This function could also be written using a local symbol for the conversion multiplier (a local symbol is like a local variable, but immutable of course because this is Clojure)
(defn convert-inches-to-centimeters-alternative
  "Convert inches to centimeters - using let to define a local symbol"
  [inches]
  (let [centimeter-multiplier 2.54]
    (* inches centimeter-multiplier)))

(convert-inches-to-centimeters-alternative 74)



;; The hight for two other people
(def Anna  {:height {:feet 5 :inches 10}})
(def Bruce {:height {:feet 6 :inches 8}})


;; Calculate the average hight for all three people, John, Anna, Bruce

;; Steps to implement:
;; convert each person to centimeters < just start with Centimeters
;; calculate the average
;; convert back to feet & inches

;; clojure-bridge-exercises.core> (def heights [10 20 30])
;; #'clojure-bridge-exercises.core/heights
;; clojure-bridge-exercises.core> (count heights)
;; 3
;; clojure-bridge-exercises.core> (apply + heights)
;; 60


(defn average-height-in-centimeters
  "Given peoples height in centimeters, return the average height in centimeters"
  [heights]
  (/ (apply + heights) (count heights)))

(average-height-in-centimeters [180 220 140])

;; If the average is not a whole nuber, we get an interesting result
(average-height-in-centimeters [180 220 160])

;; Lets tweak the values and get a decimal number
(average-height-in-centimeters [180 220 160.0])


;; clojure-bridge-exercises.core> (quot 15 7)
;; 2
;; clojure-bridge-exercises.core> (mod 15 7)
;; 1

(defn convert-centimeters-to-feet-and-inches
  "Convert values in centimeters to feet and inches"
  [value]
  (let [feet (quot (/ value 2.54) 12)
        inches (mod (/ value 2.54) 12)]
    {:feet feet :inches inches}))

(defn average-height-in-feet-given-centimeters
  "Calculate the average hight of three people"
  [people]
  (convert-centimeters-to-feet-and-inches (average-height-in-centimeters people)))

(average-height-in-feet-given-centimeters [160 180 200])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Data structures

;; EXERCISE: Make a vector

;; Make a vector of the high temperatues for the next 7 days in the town where you live. Then use the nth function to get the high temperature for next Tuesday.


(def weather-temperatures-forecast [5 7 13 12 11 12 9])

(nth weather-temperatures-forecast 1)

;; We could make this clearer with a nice little map to represent days of the week

(def days-of-the-week {:monday 0
                                :tuesday 1
                                :wednesday 2
                                :thursday 3
                                :friday 4
                                :saturday 5
                                :sunday 6})

(nth weather-temperatures-forecast (days-of-the-week :tuesday))

;; or even fancier with the threading macro

(->
 weather-temperatures-forecast
 (nth (days-of-the-week :tuesday)))


;; Making a longer range forcast by conjugating (joining) more temperatures to our forcast data structure

(conj weather-temperatures-forecast 13 15 19 20 22 21 24)

;; So what does the forcast data structure look like now?

weather-temperatures-forecast

;; Should we wish to keep this longer forcast, we can assign a name to it (makeing a clojure symbol)

(def long-range-weather-forecast
  (conj weather-temperatures-forecast 13 15 19 20 22 21 24))

;; Now we can access the full long range forecast
long-range-weather-forecast

;; Although underlying data structures cannont be changed in Clojure (they are immutable),
;; you can change names and point them to new data structures

;; So if we want it to look like we have updated our original forcast data, then we can point the original
;; name to the new data structure we are creating
(def weather-temperatures-forecast
  (conj weather-temperatures-forecast 13 15 19 20 22 21 24))

;; When we re-evaluate the weather-temperature-forecast it has all the new values
weather-temperatures-forecast

