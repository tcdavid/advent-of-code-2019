import math
import re
import copy
from functools import reduce


def main():
    f = open("input.txt", 'r')
    lines = f.readlines()

    input = list(map(lambda it: [int(x) for x in re.findall(r'-?\d+', it)], lines))

    positions = list(map(createPosition, input))

    moons = list(map(lambda it: Moon(it, Position(0, 0, 0), Position(0, 0, 0)), positions))

    starting = copy.deepcopy(moons)

    x = 0
    y = 0
    z = 0
    iteration = 1

    while (x == 0) or (y == 0) or (z == 0):
        for moon in moons:
            deep = copy.deepcopy(moons)
            deep.remove(moon)
            moon.updateGravity(deep)

        for moon in moons:
            moon.updateVelocity()

        for moon in moons:
            moon.updatePosition()

        if ((moons[0].position.x == starting[0].position.x) and
                (moons[1].position.x == starting[1].position.x) and
                (moons[2].position.x == starting[2].position.x) and
                (moons[3].position.x == starting[3].position.x) and
                (moons[0].speed.x == starting[0].speed.x) and
                (moons[1].speed.x == starting[1].speed.x) and
                (moons[2].speed.x == starting[2].speed.x) and
                (moons[3].speed.x == starting[3].speed.x) and (x == 0)
        ):
            print("x back to the beginnning " + str(iteration))
            x = iteration

        if ((moons[0].position.y == starting[0].position.y) and
                (moons[1].position.y == starting[1].position.y) and
                (moons[2].position.y == starting[2].position.y) and
                (moons[3].position.y == starting[3].position.y) and
                (moons[0].speed.y == starting[0].speed.y) and
                (moons[1].speed.y == starting[1].speed.y) and
                (moons[2].speed.y == starting[2].speed.y) and
                (moons[3].speed.y == starting[3].speed.y) and (y == 0)
        ):
            print("y back to the beginnning " + str(iteration))
            y = iteration

        if ((moons[0].position.z == starting[0].position.z) and
                (moons[1].position.z == starting[1].position.z) and
                (moons[2].position.z == starting[2].position.z) and
                (moons[3].position.z == starting[3].position.z) and
                (moons[0].speed.z == starting[0].speed.z) and
                (moons[1].speed.z == starting[1].speed.z) and
                (moons[2].speed.z == starting[2].speed.z) and
                (moons[3].speed.z == starting[3].speed.z) and (z == 0)
        ):
            print("z back to the beginnning " + str(iteration))
            z = iteration

        iteration = iteration + 1
        print(str(iteration))


    total = 0
    for moon in moons:
        total = total + moon.totalEnergy()

    print(x)
    print(y)
    print(z)

    cycles = [x, y, z]

    # 56344
    # 231614
    # 193052

    print(solution(cycles))

    #314917503970904

def createPosition(it):
    return Position(it[0], it[1], it[2])


class Position:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.x == other.x and self.y == other.y and self.z == other.z
        else:
            return False

    def __str__(self):
        return f"x:{self.x} y:{self.y} z:{self.z}"

    def __repr__(self):
        return f"Position({self.x},{self.y},{self.z})"


class Moon:
    def __init__(self, position, speed, gravity):
        self.position = position
        self.speed = speed
        self.gravity = gravity

    def __str__(self):
        return f"position:{self.position} speed:{self.speed} gravity:{self.gravity}"

    def __repr__(self):
        return f"Moon({self.position}, {self.speed}, {self.gravity})"

    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.position == other.position and self.speed == other.speed
        else:
            return False

    def updateGravity(self, otherMoons):
        x = 0
        y = 0
        z = 0
        for it in otherMoons:
            if it.position.x > self.position.x:
                x = x + 1

            if it.position.x < self.position.x:
                x = x - 1

            if it.position.y > self.position.y:
                y = y + 1

            if it.position.y < self.position.y:
                y = y - 1

            if it.position.z > self.position.z:
                z = z + 1

            if it.position.z < self.position.z:
                z = z - 1

        self.gravity = Position(x, y, z)

    def updateVelocity(self):
        self.speed.x += self.gravity.x
        self.speed.y += self.gravity.y
        self.speed.z += self.gravity.z

    def updatePosition(self):
        self.position.x += self.speed.x
        self.position.y += self.speed.y
        self.position.z += self.speed.z

    def potentialEnergy(self):
        return abs(self.position.x) + abs(self.position.y) + abs(self.position.z)

    def kineticEnergy(self):
        return abs(self.speed.x) + abs(self.speed.y) + abs(self.speed.z)

    def totalEnergy(self):
        return self.potentialEnergy() * self.kineticEnergy()


def solution(cycles):
    return reduce(lambda a, p: lcm(a, p), cycles, 1)


def lcm(a, b):
    return abs(a * b) // math.gcd(a, b)

main()
