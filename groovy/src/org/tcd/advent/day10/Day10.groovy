package org.tcd.advent.day10

import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable
import groovy.transform.ToString

class Day10 {
    static main(args) {
        String[] lines = new File("groovy/src/org/tcd/advent/day10/input.txt").readLines()
        int totalY = lines.length
        int totalX = lines[0].length()

        println totalY
        println totalX

        List<Point> points = []

        for (int x = 0; x < totalX; x++) {
            for (int y = 0; y < totalY; y++) {
                if (lines[y][x] == "#") {
                    points.add(new Point(x: x, y: y))
                }
            }
        }

        println points.size()
        Map<Point, Integer> visiblePoints = [:]

        points.each { startingPoint ->
            int count = 0
            points.each { otherPoint ->
                if (!startingPoint.equals(otherPoint)) {
                    Set<Point> betweens = between(startingPoint, otherPoint)
                    if (betweens.isEmpty()) {
                        count++
                    } else {
                        if (points.intersect(betweens).isEmpty()) {
                            count++
                        }
                    }
                }
            }

            visiblePoints.put(startingPoint, count)
            if (count == 299) {
                println startingPoint
            }
        }

        def values = visiblePoints.values().sort()

        // 260
        println values.first()
        println values.last()
        // 299

    }

    static Set<Point> between(Point start, Point end) {
        Set<Point> points = []

        int up = end.y - start.y
        int over = end.x - start.x

        // skip adjacent
        if (Math.abs(over) <= 1 && Math.abs(up) <= 1) {
            return points
        }

        if (Math.abs(over) >= Math.abs(up)) {
            int direction = 1
            int secondDirection = 1
            if (over < 0) {
                direction = -1
            }
            if (up < 0) {
                secondDirection = -1
            }
            for (int b = 1; b < Math.abs(over); b++) {
                int nextX = start.x + (b * direction)
                double nextY = start.y + Math.abs(up) * b * secondDirection / Math.abs(over)
                if (isInteger(nextY)) {
                    points.add(new Point(x: nextX, y: nextY.intValue()))
                }
            }
        }

        if (Math.abs(up) >= Math.abs(over)) {
            int direction = 1
            int secondDirection = 1
            if (up < 0) {
                direction = -1
            }
            if (over < 0) {
                secondDirection = -1
            }
            for (int b = 1; b < Math.abs(up); b++) {
                int nextY = start.y + (b * direction)
                double nextX = start.x + Math.abs(over) * b * secondDirection / Math.abs(up)
                if (isInteger(nextX)) {
                    points.add(new Point(x: nextX.intValue(), y: nextY))
                }
            }
        }

        return points
    }

    static isInteger(double variable) {
        ((variable == Math.floor(variable)) && !Double.isInfinite(variable))
    }

    @ToString
    @EqualsAndHashCode
    @Sortable
    static class Point {
        int x
        int y
    }

}
