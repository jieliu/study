# Sources for a test helper binary.
###################################


# Test utilities.
#################


# All the test sources.
#######################
set(TESTS_SRC
  ${TESTS_SRC}
  demo/demo_math_test.cpp
  demo/mock_demo_math.cpp 
  )


# INCLUDE DIRECTIVES (generates, e.g., -I/path/to/thing on Linux).
##################################################################
include_directories(${TESTS_INCLUDE_DIRS})
include_directories(${TARGET_INCLUDE_DIRS})

# LINKING LIBRARIES (might generate, e.g., -L/path/to/thing on Linux).
######################################################################
link_directories(${TESTS_LIB_DIRS})


# THE TEST AND HELPER EXECUTABLEs (generates, e.g., stout-tests, etc., on Linux).
#################################$###############################################
add_executable(${TESTS_TARGET} ${TESTS_SRC})


# ADD LINKER FLAGS (generates, e.g., -lglog on Linux).
######################################################
target_link_libraries(${TESTS_TARGET} ${TESTS_LIBS})


# ADD BINARY DEPENDENCIES (tells CMake what to compile/build first).
####################################################################
add_dependencies(
  ${TESTS_TARGET}
  ${TESTS_DEPENDENCIES}   
  )


# ADD TEST TARGET (runs when you do, e.g., `make check`).
#########################################################
add_test(NAME MesosTests COMMAND ${TESTS_TARGET})


## CTest
# add_test (name_verify ${PROJECT_NAME})
# set_tests_properties (name_verify
# PROPERTIES PASS_REGULAR_EXPRESSION "Version is 0.28") 