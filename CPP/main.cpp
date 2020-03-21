#include <iostream>
using namespace std;

int main()
{
    std::cout << "hello world" << std::endl;
    int c = 1;
    for (int i = 0; i < 1000; i++)
    {
        c+=i;
    }

    cout << "c=" << c << endl;
}