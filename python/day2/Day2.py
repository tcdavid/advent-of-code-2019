def main():
    ADD = 1
    MULT = 2
    STOP = 99

    f = open("input.txt", 'r')
    line = f.readline()
    ops = list(map(int, line.split(",")))
    ops[1] = 12
    ops[2] = 2

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

    print("done")
    print(ops[0])
    # 3790645


main()
