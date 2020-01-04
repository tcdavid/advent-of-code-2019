package org.tcd.advent.day10

import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable
import groovy.transform.ToString

class Day10Part2 {
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

        Map<Point, List<Point>> visiblePoints = [:]

        points.each { startingPoint ->
            List<Point> visPoints = []
            points.each { otherPoint ->
                if (!startingPoint.equals(otherPoint)) {
                    Set<Point> betweens = between(startingPoint, otherPoint)
                    if (betweens.isEmpty()) {
                        visPoints.add(otherPoint)
                    } else {
                        if (points.intersect(betweens).isEmpty()) {
                            visPoints.add(otherPoint)
                        }
                    }
                }
            }
            if (visPoints.size() > 0) {
                visiblePoints.put(startingPoint, visPoints)
            }
        }

        def sorted = visiblePoints.sort { a, b -> b.value.size() <=> a.value.size() }
        Point first = sorted.keySet().first()
        println first
        List<Point> visible = sorted.get(first)
        println visible.size()

        Map<Point, Double> angles = visible.collectEntries { [it, angleBetween(first.x, first.y, it.x, it.y)] }

        def anglesSorted = angles.sort { a, b -> a.value <=> b.value }

        //org.tcd.advent.day10.Day10Part2$Point(26, 26
        println anglesSorted.keySet().first()

        def lucky200 = anglesSorted.keySet().take(200).last()
        println lucky200
        // org.tcd.advent.day10.Day10Part2$Point(14, 19)
        println (lucky200.x*100 + lucky200.y)
        // 1419
    }

    @ToString
    @EqualsAndHashCode
    @Sortable
    static class Point {
        int x
        int y
    }

    static isInteger(double variable) {
        ((variable == Math.floor(variable)) && !Double.isInfinite(variable))
    }

    static Set<Point> between(Point start, Point end) {
        Set<Point> points = []

        int up =  end.y - start.y
        int over =  end.x - start.x

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

    static double angleBetween(int x1, int y1, int x2, int y2) {
        //Math.atan2(x2-x1, -(y2-y1)) % (2*Math.PI)
        def value = Math.atan2(x2-x1, -(y2-y1))
        if (value < 0) {
            value = value + 2*Math.PI
        }

        value
    }
}

