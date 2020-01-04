def main():
    f = open("input.txt", 'r')
    lines = f.readlines()

    orbits = {}
    for line in lines:
        orbits[line[4:7]] = line[0: 3]

    santaPath = orbitPath("SAN", orbits)
    youPath = orbitPath("YOU", orbits)

    index = 0
    current = santaPath[index]
    while not current in youPath:
        index = index + 1
        current = santaPath[index]

    youIndex = youPath.index(current)
    sum = youIndex + index

    # 283
    print(sum)


def orbitPath(planet, orbits):
    path = []

    while planet != "COM":
        planet = orbits.get(planet)
        path.append(planet)

    return path


main()
