package org.tcd.advent.day1

class Day1Part2 {
    static main(args) {
        def file = new File("groovy/src/org/tcd/advent/day1/input.txt")
        def total = file.readLines().collect { calc(it.toInteger())}.sum()
        println total
        //3560353 part 1

        // part 2
        // 5337642
    }

    static int calc(int val) {
        def acc = 0
        def start = val
        while (calculate(start) > 0) {
            def additional = calculate(start)
            acc += additional
            start = additional
        }
        acc
    }

    static int calculate(int val) {
        def fuel = Math.floor(val / 3) - 2
        if (fuel < 1) {
            return 0
        }

        return fuel
    }
}
