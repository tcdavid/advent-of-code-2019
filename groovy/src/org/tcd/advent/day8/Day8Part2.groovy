package org.tcd.advent.day8

class Day8Part2 {
    static main(args) {
        final int rows = 6
        final int columns = 25
        final String transparent = "2"

        String line = new File("groovy/src/org/tcd/advent/day8/input.txt").getText().replaceAll("0", " ")
        List<String> layers = line.toList().collate(rows * columns)*.join()

        List<String> pict = (0..rows * columns - 1).collect {
            int startingLayer = 0
            while (layers[startingLayer][it] == transparent) {
                startingLayer++
            }
            layers[startingLayer][it]
        }

        List<String> pictRows = pict.collate(columns)*.join()
        (0..rows-1).each {
            println pictRows[it]
        }
    }
}
