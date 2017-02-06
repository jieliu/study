# THE HEllOWORLD PROJECT.
####################
cmake_minimum_required(VERSION 2.8)

project(helloworld)

set(MAJOR_VERSION 0)
set(MINOR_VERSION 29)
set(PATCH_VERSION 0)
set(PACKAGE_VERSION  ${MAJOR_VERSION}.${MINOR_VERSION}.${PATCH_VERSION})
set(PACKAGE_VERSION ${PACKAGE_VERSION})
set(PACKAGE_SOVERSION 0)

# SOURCE DIR AND SUB DIR
####################
aux_source_directory(./src SRC_LIST)

# TARGET
####################
add_executable(${PROJECT_NAME}-${PACKAGE_VERSION} ${SRC_LIST})
