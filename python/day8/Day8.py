def main():
    f = open("input.txt", 'r')
    line = f.readline()

    rows = 6
    columns = 25
    n = rows * columns

    layers = [line[i:i + n] for i in range(0, len(line), n)]
    print(layers)

    counts = list(map(findcounts, layers))

    counts.sort(key=lambda x: x.zeros)
    first = counts[0]

    # 1965
    print(first.ones * first.twos)


def findcounts(layer):
    zeros = layer.count("0")
    ones = layer.count("1")
    twos = layer.count("2")
    return Counts(zeros, ones, twos)


class Counts:
    def __init__(self, zeroes, ones, twos):
        self.zeros = zeroes
        self.ones = ones
        self.twos = twos


main()
