def calculate_factorial(n):
    if n < 0:
        raise ValueError("Factorial is not defined for negative numbers")
    elif n == 0 or n == 1:
        return 1
    else:
        factorial = 1
        for i in range(2, n + 1):
            factorial *= i
        return factorial

# Example usage
if __name__ == "__main__":
    try:
        number = int(input("Enter a non-negative integer: "))
        print(f"The factorial of {number} is {calculate_factorial(number)}")
    except ValueError as e:
        print(e)
