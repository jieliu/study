#include <iostream>

#include <gflags/gflags.h>

using namespace std;


DEFINE_int32(port, 2181, "What port to listen on");

bool ValidatePort(const char* flagname, int32_t value) {
    if (value > 0 && value < 32768)   // value is ok
        return true;
    cout << "Invalid value for --" << flagname << " " << (int)value << endl;
    return false;
}

int mainOption(int argc, char *argv[]) {
    std::string usage("This program does nothing.  Sample usage:\n");
    usage += std::string(argv[0])+" --port 1234 \n or :\n -flagfile=foo.conf";
    google::SetUsageMessage(usage);

    google::RegisterFlagValidator (&FLAGS_port, ValidatePort);
}
