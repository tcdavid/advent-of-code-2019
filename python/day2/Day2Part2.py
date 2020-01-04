import sys


def main():
    ADD = 1
    MULT = 2
    STOP = 99
    DESIRED = 19690720

    for noun in range(0, 99):
        for verb in range(0, 99):
            f = open("input.txt", 'r')
            line = f.readline()
            ops = list(map(int, line.split(",")))
            ops[1] = noun
            ops[2] = verb

            position = 0
            op = ops[position]
            print(op)

            while op != STOP:
                arg1 = ops[position + 1]
                arg2 = ops[position + 2]
                arg3 = ops[position + 3]

                if op == ADD:
                    ops[arg3] = ops[arg1] + ops[arg2]
                elif op == MULT:
                    ops[arg3] = ops[arg1] * ops[arg2]

                position += 4
                op = ops[position]
                print(op)

            if ops[0] == DESIRED:
                print(noun)
                print(verb)
                print("found")

                print(100 * noun + verb)

                # 6577
                sys.exit()


main()
