#include <iostream>
#include <cmath>

#include <gflags/gflags.h>
#include <glog/logging.h>

#include "config.h"
#include "option.h"

#include "demo/demo_math.h"

using namespace std;

int main(int argc, char *argv[]) {

    //glog
    google::InitGoogleLogging(argv[0]);
    FLAGS_log_dir = "./log";

    //gflags 	
    mainOption(argc, argv);
    google::ParseCommandLineFlags (&argc, &argv, false);
    cout << "FLAGS_port:" << FLAGS_port << endl;
	
    LOG(INFO) << "google log : test INFO!!";
    LOG(WARNING) << "google log : WARNING!!";

    cout << argv[0] << " Version is " << VERSION_MAJOR << "." << VERSION_MINOR << endl;

    DemoMath demoMath;    
    cout << demoMath.addTen(23) << endl;

    cout << sqrt(4) << endl;
    cout << "Hello World!" << endl;

    //shutdown
    google::ShutdownGoogleLogging(); //与google::InitGoogleLogging(argv[0]);成对使用，不然会内存泄漏
    google::ShutDownCommandLineFlags();
    return 0;
}

