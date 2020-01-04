package org.tcd.advent.day8

class Day8 {
    static main(args) {

        int rows = 6
        int columns = 25

        String line = new File("groovy/src/org/tcd/advent/day8/input.txt").getText()

        List<String> layers = line.toList().collate(rows * columns)*.join()

        println layers.size()

        List<Counts> counts = layers.collect { layer ->
            int zeros = layer.findAll("0").size()
            int ones = layer.findAll("1").size()
            int twos = layer.findAll("2").size()
            new Counts(zeros: zeros, ones: ones, twos: twos)
        }

        List<Counts> sorted = counts.sort {a,b -> a.zeros <=> b.zeros}
        Counts first = sorted.first()

        // 1965
        println first.ones*first.twos
    }

    static class Counts {
        int zeros
        int ones
        int twos
    }
}
