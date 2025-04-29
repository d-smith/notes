function addNumbers(num1, num2) {
    return num1 + num2;
}

function calculateAverage(numbers) {
    const sum = numbers.reduce((acc, current) => acc + current, 0);
    return sum / numbers.length;
}