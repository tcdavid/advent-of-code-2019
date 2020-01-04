def main():
    f = open("input.txt", 'r')
    lines = f.readlines()

    orbits = {}
    for line in lines:
        orbits[line[4:7]] = line[0: 3]

    totals = list(map(lambda it: orbitCount(it, orbits), orbits.keys()))
    total = sum(totals)

    # 142915
    print(total)


def orbitCount(planet, orbits):
    count = 0

    while planet != "COM":
        count = count + 1
        planet = orbits.get(planet)

    return count


main()
