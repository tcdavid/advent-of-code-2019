def main():
    f = open("input.txt", 'r')
    lines = f.readlines()
    totalY = len(lines)
    totalX = len(lines[0]) - 1

    points = []

    for x in range(totalX):
        for y in range(totalY):
            line = lines[y]
            char = line[x]
            if char == "#":
                points.append(Point(x, y))

    # Map<Point, Integer> visiblePoints = [:]
    visiblePoints = {}

    for startingPoint in points:
        count = 0
        for otherPoint in points:
            if not startingPoint == otherPoint:
                betweens = between(startingPoint, otherPoint)
                if len(betweens) == 0:
                    count = count + 1
                else:
                    if len(intersection(points, betweens)) == 0:
                        count = count + 1

        visiblePoints[startingPoint] = count
        if count == 299:
            print(startingPoint)

    values = list(visiblePoints.values())
    values.sort()

    # 260
    print(values[0])
    print(values[-1])


def intersection(lst1, lst2):
    return list(set(lst1) & set(lst2))


# Set<Point> between(Point start, Point end)
def between(start, end):
    points = set()

    up = end.y - start.y
    over = end.x - start.x

    # skip adjacent
    if abs(over) <= 1 and abs(up) <= 1:
        return points

    if abs(over) >= abs(up):
        direction = 1
        secondDirection = 1
        if over < 0:
            direction = -1

        if up < 0:
            secondDirection = -1

        for b in range(1, abs(over)):
            nextX = start.x + (b * direction)
            nextY = start.y + abs(up) * b * secondDirection / abs(over)
            if isInteger(nextY):
                points.add(Point(nextX, int(nextY)))

    if abs(up) >= abs(over):
        direction = 1
        secondDirection = 1
        if up < 0:
            direction = -1

        if over < 0:
            secondDirection = -1

        for b in range(1, abs(up)):
            nextY = start.y + (b * direction)
            nextX = start.x + abs(over) * b * secondDirection / abs(up)
            if isInteger(nextX):
                points.add(Point(int(nextX), nextY))

    return points


def isInteger(x):
    return int(x) == x


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return f"x:{self.x} y:{self.y}"

    def __repr__(self):
        return f"Point({self.x},{self.y})"

    def __hash__(self):
        return hash(self.x) + 13 * hash(self.y)

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.x == other.x and self.y == other.y
        else:
            return False


main()
