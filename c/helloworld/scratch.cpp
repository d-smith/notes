#include <iostream>
#include <string>

int main() {
    const std::string hello = "Hello";
    const std::string message = hello + ", world" + "!";
    std::cout << message << std::endl;

    {
        const std::string s = "A string";
        std::cout << s << std::endl;
    }

     {
        const std::string s = "Another string";
        std::cout << s << std::endl;
    }

    return 0;
}