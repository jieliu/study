#include <iostream>
#include <cmath>

#include <gflags/gflags.h>
#include <glog/logging.h>

#include "config.h"
#include "./demo/demo_math.h"

using namespace std;

DEFINE_int32(port, 0, "What port to listen on");

int main(int argc, char *argv[]) {

    //glog
    google::InitGoogleLogging(argv[0]);
    FLAGS_log_dir = "./log";

    //gflags 	
    std::string usage("This program does nothing.  Sample usage:\n");
    usage += std::string(argv[0])+" --port 1234 \n or :\n -flagfile=foo.conf";
    google::SetUsageMessage(usage);
    gflags::SetVersionString("1.0.0");

    google::ParseCommandLineFlags (&argc, &argv, false);
    cout << "FLAGS_port:" << FLAGS_port << endl;
	
    LOG(INFO) << "google log : test INFO!!";
    LOG(WARNING) << "google log : WARNING!!";

    cout << argv[0] << " Version is " << VERSION_MAJOR << "." << VERSION_MINOR << endl;
    cout << fun(23) << endl;
    cout << sqrt(4) << endl;
    cout << "Hello World!" << endl;


    //shutdown
    google::ShutdownGoogleLogging(); //与google::InitGoogleLogging(argv[0]);成对使用，不然会内存泄漏
    google::ShutDownCommandLineFlags();
    return 0;
}

