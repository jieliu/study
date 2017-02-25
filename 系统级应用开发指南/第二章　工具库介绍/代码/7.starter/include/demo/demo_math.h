#include <iostream>

using namespace std;

class DemoMath {
public:
	int addTen(int base) {
		return base + 10;
	}

	int power(int base, int m) {
		int tmp = 1;
		for(int i = 0; i < m; i++) {
			tmp = tmp * base;
		}
		return tmp;
	}
	
	int fun(int a) {
		return a;
	}
};