#include <iostream>
#include <cmath>
#include "config.h"
#include "./demo/demo_math.h"

using namespace std;

int main(int argc, char *argv[]) {
	cout << argv[0] << " Version is " << VERSION_MAJOR << "." << VERSION_MINOR << endl;
    cout << fun(23) << endl;
    cout << sqrt(4) << endl;
    cout << "Hello World!" << endl;
    return 0;
}
