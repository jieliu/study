include(CTest)

# TEST TARGET
########################
set(
  TESTS_TARGET test-target
  CACHE STRING "Target we use to refer to tests for the mesos"
  )

set(
  TEST_HELPER_TARGET test-helper
  CACHE STRING "Test helper target to run tests that require a subprocess"
  )

# COMPILER CONFIGURATION.
#########################
# NOTE: On Windows, these paths should be Windows-style, with '\' characters
# separating path components. Unfortunately, CMake does not escape these
# slashes in path strings, so when we pass them as preprocessor flags,
# a string like `C:\src` will look to the standard Windows API like a
# string with an escaped '\s' character.
#
# On the other hand, Windows APIs are happy to take Unix-style paths as
# arguments. So, to unblock making the agent tests work, we simply use
# Unix paths here.

add_definitions(-DSOURCE_DIR="${CURRENT_CMAKE_SOURCE_DIR}")
add_definitions(-DBUILD_DIR="${CURRENT_CMAKE_BUILD_DIR}")

add_definitions(-DPKGLIBEXECDIR="${PKG_LIBEXEC_INSTALL_DIR}")
add_definitions(-DTESTLIBEXECDIR="${TEST_LIB_EXEC_DIR}")
add_definitions(-DPKGMODULEDIR="${PKG_MODULE_DIR}")
add_definitions(-DSBINDIR="${S_BIN_DIR}")