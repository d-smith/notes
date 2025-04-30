from flask import Flask, jsonify

# Memoization using a dictionary
factorial_cache = {}

# Define a maximum input threshold
MAX_INPUT = 1000
MAX_RESULT_DIGITS = 1000  # Restrict result size in terms of number of digits

def calculate_factorial(n):
    if not isinstance(n, int):
        raise TypeError("Factorial is only defined for integers")
    if n < 0:
        raise ValueError("Factorial is not defined for negative numbers")
    elif n == 0 or n == 1:
        return 1
    if n in factorial_cache:
        return factorial_cache[n]
    
    factorial = 1
    for i in range(2, n + 1):
        factorial *= i
        # Check if the result exceeds the size threshold
        if len(str(factorial)) > MAX_RESULT_DIGITS:
            raise OverflowError("Result exceeds the allowed size limit")
    factorial_cache[n] = factorial  # Store result in cache
    return factorial

# Flask app setup
app = Flask(__name__)

@app.route('/factorial/<int:num>', methods=['GET'])
def factorial_route(num):
    try:
        # Validate input size
        if num > MAX_INPUT:
            raise ValueError(f"Input exceeds maximum limit of {MAX_INPUT}")
        # Calculate factorial
        result = calculate_factorial(num)
        return jsonify({"number": num, "factorial": result})
    except ValueError as e:
        return jsonify({"error": str(e)}), 400
    except OverflowError as e:
        return jsonify({"error": str(e)}), 400
    except TypeError as e:
        return jsonify({"error": str(e)}), 400

if __name__ == "__main__":
    app.run(debug=True)

