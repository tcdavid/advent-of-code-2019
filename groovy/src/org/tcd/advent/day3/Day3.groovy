package org.tcd.advent.day3

import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable
import groovy.transform.ToString

import java.awt.Point

class Day3 {

    static String UP = "U"
    static String DOWN = "D"
    static String RIGHT = "R"
    static String LEFT = "L"

    static main(args) {
        def file = new File("groovy/src/org/tcd/advent/day3/input.txt")
        String[] lines = file.readLines()

        List<Position> line1 = positionsForLine(lines[0])
        List<Position> line2 = positionsForLine(lines[1])

        List<Position> common = line1.intersect(line2)

        println line1.size()
        println line2.size()
        println common.size()

        List<Position> sorted = common.sort { distance(it) }

        // 245
        println distance(sorted.first())

        def sortedDistances = common.collect { calculateDistance(it, line1, line2) }.sort()

        // 48262
        println sortedDistances.first()

    }

    static int calculateDistance(Position point, List<Position> line1, List<Position> line2) {
        int index1 = line1.findIndexOf { it == point }
        int index2 = line2.findIndexOf { it == point }
        index1 + 1 + index2 + 1
    }

    static List<Position> positionsForLine(String line) {
        List<Movement> moves = line.split(",").collect {
            new Movement(direction: it.take(1), distance: it.substring(1).toInteger())
        }

        println moves.size()
        List<Position> positions = [new Position(x: 0, y: 0)]

        moves.each {
            positions.addAll(positions.last().move(it))
        }

        positions.tail()
    }

    static int distance(Position position) {
        Math.abs(position.x) + Math.abs(position.y)
    }

    static class Movement {
        String direction
        int distance
    }

    @ToString
    @EqualsAndHashCode
    @Sortable
    static class Position {
        int x
        int y

        List<Position> move(Movement movement) {
            switch (movement.direction) {
                case UP:
                    return (y + 1..y + movement.distance).collect { new Position(x: x, y: it) }
                    break
                case DOWN:
                    return (y - 1..y - movement.distance).collect { new Position(x: x, y: it) }
                    break
                case LEFT:
                    return (x - 1..x - movement.distance).collect { new Position(x: it, y: y) }
                    break
                case RIGHT:
                    return (x + 1..x + movement.distance).collect { new Position(x: it, y: y) }
                    break
            }

            throw new Exception("what went wrong?")
        }
    }
}
