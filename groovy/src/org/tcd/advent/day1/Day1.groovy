package org.tcd.advent.day1

class Day1 {
    static main(args) {

        def file = new File('groovy/src/org/tcd/advent/day1/input.txt')
        def total = file.readLines().collect { calculate(it)}.sum()
        println total
        //3560353
    }

    static int calculate(String val) {
        Math.floor(val.toInteger() / 3) - 2
    }
}
