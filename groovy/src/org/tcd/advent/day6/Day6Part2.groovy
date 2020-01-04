package org.tcd.advent.day6

class Day6Part2 {

    static main(args) {
        String[] lines = new File("groovy/src/org/tcd/advent/day6/input.txt").readLines()

        Map<String, String> orbits = lines.collectEntries { [it.substring(4), it.substring(0, 3)] }

        List<String> santaPath = orbitPath("SAN", orbits)
        List<String> youPath = orbitPath("YOU", orbits)

        int index = 0
        String current = santaPath[index]
        while (!youPath.contains(current)) {
            index ++
            current = santaPath[index]
        }

        int youIndex = youPath.indexOf(current)
        int sum = youIndex + index

        // 283
        println sum

    }

    static List<String> orbitPath(String planet, Map<String, String> orbits) {
        List<String> path = []

        while (planet != "COM") {
            planet = orbits.get(planet)
            path.add(planet)
        }

        path
    }
}
