def main():
    f = open("input.txt", 'r')
    line = f.readline().replace("0", " ")

    rows = 6
    columns = 25
    n = rows * columns

    layers = [line[i:i + n] for i in range(0, len(line), n)]

    pict = list(map(lambda it: some(it, layers), range(0, n)))
    one = ''.join(pict)

    pictrows = [one[i:i + columns] for i in range(0, len(one), columns)]

    for it in range(rows):
        print(pictrows[it])


def some(it, layers):
    startingLayer = 0
    transparent = "2"

    while layers[startingLayer][it] == transparent:
        startingLayer = startingLayer + 1

    return layers[startingLayer][it]


main()
