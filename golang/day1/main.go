package main

import (
	"bufio"
	"os"
	"strconv"
)

func main() {
	modules, err := readLines("day1/input.txt")
	check(err)
	fuelPart1 := 0
	fuelPart2 := 0
	for _, module := range modules {
		mass, err := strconv.Atoi(module)
		check(err)
		fuelPart1 += calculate(mass)
		fuelPart2 += calc(mass)
	}

	// part 1 - 3560353
	println(fuelPart1)

	// part 2 - 5337642
	println(fuelPart2)
}

func calculate(mass int) int {
	return (mass / 3) - 2
}

func calc(mass int) int {
	fuel := 0
	start := mass
	for calculate(start) > 0 {
		additional := calculate(start)
		fuel += additional
		start = additional
	}
	return fuel
}

func readLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}

func check(err error) {
	if err != nil {
		panic(err)
	}
}
