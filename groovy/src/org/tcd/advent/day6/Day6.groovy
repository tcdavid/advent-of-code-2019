package org.tcd.advent.day6

class Day6 {
    static main(args) {
        String[] lines = new File("groovy/src/org/tcd/advent/day6/input.txt").readLines()

        Map<String, String> orbits = lines.collectEntries { [it.substring(4), it.substring(0, 3)] }
        int total = orbits.keySet().collect { orbitCount(it, orbits) }.sum()

        // 142915
        println total
    }

    static int orbitCount(String planet, Map<String, String> orbits) {
        int count = 0

        while (planet != "COM") {
            count++
            planet = orbits.get(planet)
        }

        count
    }
}
