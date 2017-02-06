cmake_minimum_required(VERSION 2.8)

project(helloworld)

set(VERSION_MAJOR 0)
set(VERSION_MINOR 28)
set(PATCH_VERSION 0)
set(PACKAGE_VERSION  ${VERSION_MAJOR}.${VERSION_MINOR}.${PATCH_VERSION})
set(PACKAGE_SOVERSION 0)

# Configuration.
configure_file (
  "${PROJECT_SOURCE_DIR}/config.h.in"
  "${PROJECT_BINARY_DIR}/config.h"
)
include_directories("${PROJECT_BINARY_DIR}")

# SUBDIRECTORIES.
#################
include_directories ("${PROJECT_SOURCE_DIR}/src/demo")
add_subdirectory (./src/demo) 

# TARGET.
#################
aux_source_directory(./src SRC_LIST)
add_executable(${PROJECT_NAME}-${PACKAGE_VERSION} ${SRC_LIST})
target_link_libraries (${PROJECT_NAME}-${PACKAGE_VERSION} m)
target_link_libraries (${PROJECT_NAME}-${PACKAGE_VERSION} mathfunction)
