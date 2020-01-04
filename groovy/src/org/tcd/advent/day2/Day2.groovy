package org.tcd.advent.day2

class Day2 {

    static int ADD = 1
    static int MULT = 2
    static int STOP = 99

    static main(args) {
        def file = new File("groovy/src/org/tcd/advent/day2/input.txt")
        String[] lines = file.readLines()
        int[] ops = lines[0].split(",").collect { it.toInteger() }

        ops[1] = 12
        ops[2] = 2

        int position = 0
        int op = ops[position]
        println op

        while (op != STOP) {
            int arg1 = ops[position + 1]
            int arg2 = ops[position + 2]
            int arg3 = ops[position + 3]

            switch (op) {
                case ADD:
                    ops[arg3] = ops[arg1] + ops[arg2]
                    break

                case MULT:
                    ops[arg3] = ops[arg1] * ops[arg2]
                    break

            }
            position += 4
            op = ops[position]
            println op
        }

        println "done"
        println ops[0]
        // 3790645
    }
}

