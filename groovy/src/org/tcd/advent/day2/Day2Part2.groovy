package org.tcd.advent.day2

class Day2Part2 {

    static int ADD = 1
    static int MULT = 2
    static int STOP = 99

    static int DESIRED = 19690720

    static main(args) {

        for (noun in 0..99) {
            for (verb in 0..99) {
                def file = new File("groovy/src/org/tcd/advent/day2/input.txt")
                String[] lines = file.readLines()
                int[] ops = lines[0].split(",").collect { it.toInteger() }

                ops[1] = noun
                ops[2] = verb

                int position = 0
                int op = ops[position]

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
                }

                if (ops[0] == DESIRED) {
                    println noun
                    println verb
                    println "found"

                    println 100 * noun + verb

                    //6577
                    System.exit(1)
                }
            }
        }
    }
}

