#include <iostream>
#include <cmath>

#include <glog/logging.h>

#include "config.h"
#include "./demo/demo_math.h"

using namespace std;

int main(int argc, char *argv[]) {

	google::InitGoogleLogging(argv[0]);
	FLAGS_log_dir = "./log";
	
	LOG(INFO) << "google log : test INFO!!";
	LOG(WARNING) << "google log : WARNING!!";

	cout << argv[0] << " Version is " << VERSION_MAJOR << "." << VERSION_MINOR << endl;
    cout << fun(23) << endl;
    cout << sqrt(4) << endl;
    cout << "Hello World!" << endl;

    google::ShutdownGoogleLogging(); //与google::InitGoogleLogging(argv[0]);成对使用，不然会内存泄漏
    return 0;
}
