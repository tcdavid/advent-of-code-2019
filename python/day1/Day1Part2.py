from math import floor


def main():
    f = open("input.txt", 'r')
    lines = f.readlines()
    result = sum(map(calc, lines))
    print(result)


def calculate(val):
    return floor(int(val) / 3) - 2


def calc(val):
    acc = 0
    start = val
    while calculate(start) > 0:
        additional = calculate(start)
        acc += additional
        start = additional
    return acc


main()
