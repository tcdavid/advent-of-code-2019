from math import floor


def main():
    f = open("input.txt", 'r')
    lines = f.readlines()
    result = sum(map(lambda x: calculate(x), lines))
    print(result)


def calculate(val):
    return floor(int(val) / 3) - 2


main()
