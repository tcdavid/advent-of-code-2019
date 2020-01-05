package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

const (
	ADD     = 1
	MULT    = 2
	STOP    = 99
	DESIRED = 19690720
)

func main() {
	part1(getOps())
	part2()
}

func getOps() (ops []int) {
	lines, err := readLines("day2/input.txt")
	check(err)

	opsStrings := strings.Split(lines[0], ",")

	for _, str := range opsStrings {
		op, err := strconv.Atoi(str)
		check(err)
		ops = append(ops, op)
	}
	return
}

func part2() {
	for noun := 0; noun <= 99; noun++ {
		for verb := 0; verb <= 99; verb++ {
			ops := getOps()

			ops[1] = noun
			ops[2] = verb

			position := 0
			op := ops[position]

			for op != STOP {
				arg1 := ops[position+1]
				arg2 := ops[position+2]
				arg3 := ops[position+3]

				switch op {
				case ADD:
					ops[arg3] = ops[arg1] + ops[arg2]
					break

				case MULT:
					ops[arg3] = ops[arg1] * ops[arg2]
					break

				}
				position += 4
				op = ops[position]
			}

			if ops[0] == DESIRED {
				println(100*noun + verb)
				//6577
				os.Exit(1)
			}
		}
	}
}

func part1(ops []int) {
	ops[1] = 12
	ops[2] = 2

	position := 0
	op := ops[position]

	for op != STOP {
		arg1 := ops[position+1]
		arg2 := ops[position+2]
		arg3 := ops[position+3]

		switch op {
		case ADD:
			ops[arg3] = ops[arg1] + ops[arg2]
			break

		case MULT:
			ops[arg3] = ops[arg1] * ops[arg2]
			break
		}
		position += 4
		op = ops[position]
	}
	
	println(ops[0])
	// 3790645
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
