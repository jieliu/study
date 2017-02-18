
include(ExternalProject)

# Define build/patch/configure commands for third-party libs.
#############################################################
if (NOT WIN32)
  set(GLOG_CONFIG_CMD  ${GLOG_ROOT}/src/../configure --prefix=${GLOG_LIB_ROOT})
  set(GLOG_BUILD_CMD   make libglog.la)
  set(GLOG_INSTALL_CMD make install)
  PATCH_CMD(${3RDPARTY_SRC}/glog-0.3.3.patch GLOG_PATCH_CMD)
  
  set(GFLAGS_BUILD_CMD   make)  
  set(GFLAGS_INSTALL_CMD ${CMAKE_NOOP})
 
elseif (WIN32)
  set(GLOG_INSTALL_CMD ${CMAKE_NOOP})

endif (NOT WIN32)

# Third-party libraries. Tell the build system how to pull in and build third-
# party libraries at compile time, using the ExternalProject_Add macro.
##############################################################################
ExternalProject_Add(
  ${GLOG_TARGET}
  PREFIX            ${GLOG_CMAKE_ROOT}
  CMAKE_ARGS        -DBUILD_SHARED_LIBS=OFF -DCMAKE_CXX_FLAGS_DEBUG=${CMAKE_CXX_FLAGS_DEBUG}
  PATCH_COMMAND     ${GLOG_PATCH_CMD}
  CONFIGURE_COMMAND ${GLOG_CONFIG_CMD}
  BUILD_COMMAND     ${GLOG_BUILD_CMD}
  INSTALL_COMMAND   ${GLOG_INSTALL_CMD}
  URL               ${GLOG_URL}
  DOWNLOAD_NAME     glog-${GLOG_VERSION}.tar.gz
)

ExternalProject_Add(
  ${GFLAGS_TARGET}
  PREFIX            ${GFLAGS_CMAKE_ROOT}
  BUILD_COMMAND     ${GFLAGS_BUILD_CMD} 
  INSTALL_COMMAND   ${GFLAGS_INSTALL_CMD} 
  URL               ${GFLAGS_URL}
  DOWNLOAD_NAME     gflags-${GFLAGS_VERSION}.tar.gz
)

# DEPENDENCIES FOR THE PROCESS LIBRARY TESTS AND STOUT TESTS.
#
# Downloads, configures, and compiles the third-party libraries for the process
# library tests (located in `3rdparty/`).
###############################################################################

if (NOT WIN32)
  set(GMOCK_INSTALL_CMD mkdir -p ${GMOCK_ROOT}-lib/lib && cp -r ${GMOCK_ROOT}-build/. ${GMOCK_ROOT}-lib/lib && cp -r ${GMOCK_ROOT}-build/gtest/. ${GMOCK_ROOT}-lib/lib)
elseif (WIN32)
  set(GMOCK_INSTALL_CMD ${CMAKE_NOOP})
endif (NOT WIN32)

# NOTE: An implicit consequence of the following code is that on non-Windows
# platforms, gmock and gtest are assumed to be CMake projects, and are thus
# configured and built using default CMake commands. The reason is that on
# non-Windows platforms, we choose to set `GMOCK_CONFIG_CMD` and
# `GMOCK_BUILD_CMD` with stub commands, which cause CMake to "fall back" to
# trying to build them with CMake.
ExternalProject_Add(
  ${GMOCK_TARGET}
  PREFIX            ${GMOCK_CMAKE_ROOT}
  CMAKE_ARGS        -DBUILD_SHARED_LIBS=FALSE
  CONFIGURE_COMMAND ${GMOCK_CONFIG_CMD}
  BUILD_COMMAND     ${GMOCK_BUILD_CMD}
  INSTALL_COMMAND   ${GMOCK_INSTALL_CMD}
  URL               ${GMOCK_URL}
  )