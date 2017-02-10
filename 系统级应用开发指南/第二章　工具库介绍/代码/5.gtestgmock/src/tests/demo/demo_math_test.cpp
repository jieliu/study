#include <gtest/gtest.h>

#include "demo/demo_math.h"

class FunTest: public testing::Test {
protected:
    virtual void SetUp() {
       str = "hello world";
    }

    virtual void TearDown() {
        str = "";
    }

    string str;
};

TEST_F(FunTest, testfun1) {

	ASSERT_STREQ("hello world", str.c_str());
}

TEST(demo_math, testfun2) {

	ASSERT_EQ(fun(21) ,21);
}