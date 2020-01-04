from math import atan2, pi


def main():
    f = open("input.txt", 'r')
    lines = f.readlines()
    totalY = len(lines)
    totalX = len(lines[0]) - 1

    print(totalY)
    print(totalX)

    points = []

    for x in range(totalX):
        for y in range(totalY):
            line = lines[y]
            char = line[x]
            if char == "#":
                points.append(Point(x, y))

    print(len(points))

    # Map<Point, List<Point>> visiblePoints = [:]
    visiblePoints = {}

    for startingPoint in points:
        visPoints = []
        for otherPoint in points:
            if not startingPoint == otherPoint:
                betweens = between(startingPoint, otherPoint)
                if len(betweens) == 0:
                    visPoints.append(otherPoint)
                else:
                    if len(intersection(points, betweens)) == 0:
                        visPoints.append(otherPoint)

        if len(visPoints) > 0:
            visiblePoints[startingPoint] = visPoints

    first = findMost(visiblePoints)
    print(first)

    visible = visiblePoints[first]
    print(len(visible))

    # Map<Point, Double>
    angles = {}
    for point in visible:
        angles[point] = angleBetween(first.x, first.y, point.x, point.y)

    anglesSorted = {k: v for k, v in sorted(angles.items(), key=lambda item: item[1])}

    # Point(26, 26)
    print(list(anglesSorted.keys())[0])

    lucky200 = list(anglesSorted.keys())[:200][-1]
    print(lucky200)
    # Point(14, 19)
    print(lucky200.x * 100 + lucky200.y)
    # 1419


def intersection(lst1, lst2):
    return list(set(lst1) & set(lst2))


def findMost(visiblePoints):
    mostCount = 0
    mostPoint = None
    for key in visiblePoints.keys():
        currentCount = len(visiblePoints[key])
        if currentCount > mostCount:
            mostCount = currentCount
            mostPoint = key

    return mostPoint


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


def angleBetween(x1, y1, x2, y2):
    # Math.atan2(x2-x1, -(y2-y1)) % (2*Math.PI)
    value = atan2(x2 - x1, -(y2 - y1))
    if value < 0:
        value = value + 2 * pi
    return value


main()
