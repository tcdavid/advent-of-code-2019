def main():
    numbers = list(filter(isValid, range(128392, 643281 + 1)))
    print(len(numbers))


def isValid(number):
    digit6 = number % 10
    digit5 = (number // 10) % 10
    digit4 = (number // 100) % 10
    digit3 = (number // 1000) % 10
    digit2 = (number // 10000) % 10
    digit1 = (number // 100000) % 10

    if ((digit1 <= digit2) and
            (digit2 <= digit3) and
            (digit3 <= digit4) and
            (digit4 <= digit5) and
            (digit5 <= digit6)):
        return (((digit1 == digit2) and digit2 != digit3) or
                ((digit2 == digit3) and digit3 != digit4 and digit2 != digit1) or
                ((digit3 == digit4) and digit4 != digit5 and digit3 != digit2) or
                ((digit4 == digit5) and digit5 != digit6 and digit4 != digit3) or
                ((digit5 == digit6) and digit5 != digit4))


main()
