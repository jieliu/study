
include(ExternalProject)

# Define build/patch/configure commands for third-party libs.
#############################################################
if (NOT WIN32)
  set(GLOG_CONFIG_CMD  ${GLOG_ROOT}/src/../configure --prefix=${GLOG_LIB_ROOT})
  set(GLOG_BUILD_CMD   make libglog.la)
  set(GLOG_INSTALL_CMD make install)
  PATCH_CMD(${3RDPARTY_SRC}/glog-0.3.3.patch GLOG_PATCH_CMD)
  
  set(GFLAGS_BUILD_CMD   make)  
  set(GFLAGS_INSTALL_CMD make install)
 
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

