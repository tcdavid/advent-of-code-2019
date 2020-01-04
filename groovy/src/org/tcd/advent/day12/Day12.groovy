package org.tcd.advent.day12

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable
import groovy.transform.ToString

class Day12 {

    static main(args) {
        String[] lines = new File("groovy/src/org/tcd/advent/day12/input.txt").readLines()

        def input = lines.collect { it.replaceAll("[^\\d-,]", "") }

        List<Position> positions = input.collect {
            def split = it.split(",")
            new Position(x: split[0].toInteger(), y: split[1].toInteger(), z: split[2].toInteger())
        }

        List<Moon> moons = positions.collect {
            new Moon(position: it, speed: new Speed(), gravity: new Speed())
        }

        List<Moon> starting = moons.collect { it.clone() }

        int x, y, z
        int it = 1

        while ((x == 0 || y == 0 || z == 0)) {
            moons.each { it.updateGravity(moons - it) }
            moons.each { it.updateVelocity() }
            moons.each { it.updatePosition() }


            if ((moons[0].position.x == starting[0].position.x) &&
                    (moons[1].position.x == starting[1].position.x) &&
                    (moons[2].position.x == starting[2].position.x) &&
                    (moons[3].position.x == starting[3].position.x) &&
                    (moons[0].speed.x == starting[0].speed.x) &&
                    (moons[1].speed.x == starting[1].speed.x) &&
                    (moons[2].speed.x == starting[2].speed.x) &&
                    (moons[3].speed.x == starting[3].speed.x) && x == 0

            ) {
                println "x back to the beginnning ${it}"
                x = it
            }

            if ((moons[0].position.y == starting[0].position.y) &&
                    (moons[1].position.y == starting[1].position.y) &&
                    (moons[2].position.y == starting[2].position.y) &&
                    (moons[3].position.y == starting[3].position.y) &&
                    (moons[0].speed.y == starting[0].speed.y) &&
                    (moons[1].speed.y == starting[1].speed.y) &&
                    (moons[2].speed.y == starting[2].speed.y) &&
                    (moons[3].speed.y == starting[3].speed.y) && y == 0

            ) {
                println "y back to the beginnning ${it}"
                y = it
            }

            if ((moons[0].position.z == starting[0].position.z) &&
                    (moons[1].position.z == starting[1].position.z) &&
                    (moons[2].position.z == starting[2].position.z) &&
                    (moons[3].position.z == starting[3].position.z) &&
                    (moons[0].speed.z == starting[0].speed.z) &&
                    (moons[1].speed.z == starting[1].speed.z) &&
                    (moons[2].speed.z == starting[2].speed.z) &&
                    (moons[3].speed.z == starting[3].speed.z) && z == 0

            ) {
                println "z back to the beginnning ${it}"
                z = it
            }
            it++

        }

        int total = moons*.totalEnergy().sum()
        println total

        println " ${x} ${y} ${z}"

        // 8960
        //  println total

        //  56344 231614 193052
        def cycles = [x, y, z]
        // 314,917,503,970,904

        // 314917503970904
        println solution(cycles)
    }

    @AutoClone
    @ToString
    @EqualsAndHashCode
    @Sortable
    static class Position {
        int x
        int y
        int z
    }

    @AutoClone
    @ToString
    @EqualsAndHashCode
    @Sortable
    static class Speed {
        int x = 0
        int y = 0
        int z = 0
    }

    @AutoClone
    @ToString
    @EqualsAndHashCode(excludes = "gravity")
    @Sortable
    static class Moon {
        Position position
        Speed speed
        Speed gravity

        def updateGravity(List<Moon> otherMoons) {
            int x = 0
            int y = 0
            int z = 0
            otherMoons.each {
                if (it.position.x > position.x) {
                    x++
                }
                if (it.position.x < position.x) {
                    x--
                }
                if (it.position.y > position.y) {
                    y++
                }
                if (it.position.y < position.y) {
                    y--
                }
                if (it.position.z > position.z) {
                    z++
                }
                if (it.position.z < position.z) {
                    z--
                }
            }
            gravity = new Speed(x: x, y: y, z: z)

        }

        def updateVelocity() {
            speed.x += gravity.x
            speed.y += gravity.y
            speed.z += gravity.z
        }

        def updatePosition() {
            position.x += speed.x
            position.y += speed.y
            position.z += speed.z
        }

        int potentialEnergy() {
            position.x.abs() + position.y.abs() + position.z.abs()
        }

        int kineticEnergy() {
            speed.x.abs() + speed.y.abs() + speed.z.abs()
        }

        int totalEnergy() {
            potentialEnergy() * kineticEnergy()
        }
    }

    static solution(List<Integer> cycles) {
        cycles.inject(56344, { sum, value -> lcm(sum, value) })
    }

    /* Found on: https://stackoverflow.com/questions/47047682/least-common-multiple-of-an-array-values-using-euclidean-algorithm */

    static long gcd(long m, long n) {
        m = m.abs()
        n = n.abs()
        n == 0 ? m : m % n == 0 ? n : gcd(n, m % n)
    }

    static long lcm(long m, long n) {
        Math.abs(m * n) / gcd(m, n)
    }
}
