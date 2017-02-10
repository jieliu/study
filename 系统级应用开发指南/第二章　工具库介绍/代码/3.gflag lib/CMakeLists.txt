# THE HEllOWORLD PROJECT.
####################
cmake_minimum_required(VERSION 2.8)

project(glog-example)

set(VERSION_MAJOR 0)
set(VERSION_MINOR 28)
set(PATCH_VERSION 0)
set(PACKAGE_VERSION  ${VERSION_MAJOR}.${VERSION_MINOR}.${PATCH_VERSION})
set(PACKAGE_SOVERSION 0)

# CMAKE MODULE SETUP.
#####################
# Paths that are searched when `include(...)` is called.
list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/cmake)
list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/3rdparty/cmake)

# Macros.
include(Common)
include(PatchCommand)
include(Versions)
include(External)
include(VsBuildCommand)

# Configuration.
include(ProjectConfigure)
configure_file (
  "${PROJECT_SOURCE_DIR}/config.h.in"
  "${PROJECT_BINARY_DIR}/config.h"
)
include_directories("${PROJECT_BINARY_DIR}")

# SUBDIRECTORIES.
#################
add_subdirectory(./src) 
add_subdirectory(./3rdparty)

# INCLUDE DIRECTIVES FOR TARGET (generates, e.g., -I/path/to/thing on Linux)
###########################################################################
include_directories(${TARGET_INCLUDE_DIRS})

# LINKING LIBRARIES BY DIRECTORY (might generate, e.g., -L/path/to/thing on Linux)
###########################################################################
link_directories(${TARGET_LIB_DIRS})

# Generate all binaries in the same folder.
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/bin)
if (WIN32)
  SET(CMAKE_RUNTIME_OUTPUT_DIRECTORY_DEBUG ${PROJECT_SOURCE_DIR}/bin)
  SET(CMAKE_RUNTIME_OUTPUT_DIRECTORY_RELEASE ${PROJECT_SOURCE_DIR}/bin)
endif (WIN32)

# Generate all libraries in the same folder to conform with the Automake build.
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/src/.libs)
if (WIN32)
  SET(CMAKE_LIBRARY_OUTPUT_DIRECTORY_DEBUG ${CMAKE_BINARY_DIR}/src/.libs)
  SET(CMAKE_LIBRARY_OUTPUT_DIRECTORY_RELEASE ${CMAKE_BINARY_DIR}/src/.libs)
endif (WIN32)

# TESTS.
#################
#add_custom_target(
#  check ${STOUT_TESTS_TARGET}
#  COMMAND ${PROCESS_TESTS_TARGET}
#  COMMAND ${MESOS_TESTS_TARGET}
#  DEPENDS ${STOUT_TESTS_TARGET} ${PROCESS_TESTS_TARGET}  ${MESOS_TESTS_TARGET}
#  )

# TARGET.
#################
set(TARGET_LIBS
  ${TARGET_LIBS}
  m
  mathfunction
  gflags
)

aux_source_directory(./src SRC_LIST)
add_executable(${PROJECT_NAME} ${SRC_LIST})

add_dependencies(${PROJECT_NAME} ${TARGET_DEPENDENCIES})

# ADD LINKER FLAGS (generates, e.g., -lglog on Linux).
target_link_libraries(${PROJECT_NAME} ${TARGET_LIBS})

set_target_properties(
  ${PROJECT_NAME} PROPERTIES
  VERSION ${PACKAGE_VERSION}
  SOVERSION ${PACKAGE_SOVERSION}
)
