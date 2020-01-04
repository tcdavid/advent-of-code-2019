package org.tcd.advent.day4

class Day4 {
    static main(args) {
        List<Integer> numbers = (128392..643281).findAll { isValid(it) }

        println numbers.size()
        // part 1 2050
        // part 2
        // 1390
    }

    static boolean isValid(int number) {
        int digit6 = number % 10
        int digit5 = number.intdiv(10) % 10
        int digit4 = number.intdiv(100) % 10
        int digit3 = number.intdiv(1000) % 10
        int digit2 = number.intdiv(10000) % 10
        int digit1 = number.intdiv(100000) % 10

        if ((digit1 <= digit2) &&
                (digit2 <= digit3) &&
                (digit3 <= digit4) &&
                (digit4 <= digit5) &&
                (digit5 <= digit6)
        ) {
            if (((digit1 == digit2) && digit2 != digit3) ||
                    ((digit2 == digit3) && digit3 != digit4 && digit2 != digit1) ||
                    ((digit3 == digit4) && digit4 != digit5 && digit3 != digit2) ||
                    ((digit4 == digit5) && digit5 != digit6 && digit4 != digit3) ||
                    ((digit5 == digit6) && digit5 != digit4)
            ) {
                return true
            }
        }
        false
    }
}
