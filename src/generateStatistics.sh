#!/usr/bin/env bash

#Bash script used for compile and execute the program 1000 times. Needed for statistics.

javac Main.java

for i in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 30 40 50 60 70 80 90 100 120 140 160 180 200 250 300 400 500 1000 2000 3000 4000 5000
do
        java Main $i
done

