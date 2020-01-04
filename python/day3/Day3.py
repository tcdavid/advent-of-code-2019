def main():
    f = open("input.txt", 'r')
    lines = f.readlines()
    line1 = positionsForLine(lines[0])
    line2 = positionsForLine(lines[1])

    common = intersection(line1, line2)
    common.sort(key=distance)

    # 245
    print(distance(common[0]))

    distances = list(map(lambda it: calculateDistance(it, line1, line2), common))
    distances.sort()

    # 48262
    print(distances[0])


def distance(position):
    return abs(position.x) + abs(position.y)


def calculateDistance(point, line1, line2):
    index1 = line1.index(point)
    index2 = line2.index(point)
    return index1 + 1 + index2 + 1


def positionsForLine(line):
    moves = list(map(lambda it: Movement(it[0], int(it[1:])), line.split(",")))

    positions = [Position(0, 0)]

    for move in moves:
        positions.extend(positions[-1].move(move))

    return positions[1:]


def intersection(lst1, lst2):
    return list(set(lst1) & set(lst2))


class Position:
    UP = "U"
    DOWN = "D"
    RIGHT = "R"
    LEFT = "L"

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return f"x:{self.x} y:{self.y}"

    def __repr__(self):
        return f"Position({self.x},{self.y})"

    def move(self, movement):
        if movement.direction == Position.UP:
            return list(map(lambda it: Position(self.x, it), range(self.y + 1, self.y + movement.distance + 1)))
        elif movement.direction == Position.DOWN:
            return list(map(lambda it: Position(self.x, it), range(self.y - 1, self.y - (movement.distance + 1), -1)))
        elif movement.direction == Position.LEFT:
            return list(map(lambda it: Position(it, self.y), range(self.x - 1, self.x - (movement.distance + 1), -1)))
        elif movement.direction == Position.RIGHT:
            return list(map(lambda it: Position(it, self.y), range(self.x + 1, self.x + movement.distance + 1)))

        raise Exception("what went wrong?")

    def __hash__(self):
        return hash(self.x) + 13 * hash(self.y)

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.x == other.x and self.y == other.y
        else:
            return False


class Movement:
    def __init__(self, direction, distance):
        self.direction = direction
        self.distance = distance

    def __repr__(self):
        return f"Movement({self.direction},{self.distance})"


main()
