#include <gmock/gmock.h>
#include <gtest/gtest.h>

#include "demo/demo_math.h"

class MockDemoMath : public DemoMath {
public:
  MockDemoMath();
  virtual ~MockDemoMath();

  MOCK_METHOD1(addTen, int(int base));

  MOCK_METHOD2(power, int(int base, int m));
};


TEST(DemoMathTest, funTest) {

  DemoMath demoMath;

  EXPECT_EQ(11, demoMath.addTen(1));
  EXPECT_EQ(8, demoMath.power(2, 3));

};