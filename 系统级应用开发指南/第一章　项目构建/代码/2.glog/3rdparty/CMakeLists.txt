
include(ExternalProject)

# DEPENDENCIES
#
# Downloads, configures, and compiles the third-party libraries
###############################################################################
if (NOT WIN32)
  EXTERNAL("glog" ${GLOG_VERSION} "${3RDPARTY_BIN}")
elseif (WIN32)
  # Glog 0.3.3 does not compile out of the box on Windows. Therefore, we
  # require 0.3.4.
  EXTERNAL("glog" "0.3.4" "${3RDPARTY_BIN}")

  # NOTE: We expect cURL and zlib exist on Unix (usually pulled in with a
  # package manager), but Windows has no package manager, so we have to go
  # get it.
  EXTERNAL("curl" ${CURL_VERSION} "${3RDPARTY_BIN}")

  EXTERNAL("zlib" ${ZLIB_VERSION} "${3RDPARTY_BIN}")
endif (NOT WIN32)

# Intermediate convenience variables for oddly-structured directories.
set(GLOG_LIB_ROOT     ${GLOG_ROOT}-lib/lib)

# Define sources of third-party dependencies.
#############################################
set(UPSTREAM_URL ${3RDPARTY_DEPENDENCIES})
set(REBUNDLED_DIR ${CMAKE_CURRENT_SOURCE_DIR})

if (REBUNDLED) 
  set(GLOG_URL        ${REBUNDLED_DIR}/glog-${GLOG_VERSION}.tar.gz) 
else (REBUNDLED) 
  set(GLOG_URL        ${UPSTREAM_URL}/glog-${GLOG_VERSION}.tar.gz)
endif (REBUNDLED)

# Define build/patch/configure commands for third-party libs.
#############################################################
if (NOT WIN32)
  set(GLOG_CONFIG_CMD  ${GLOG_ROOT}/src/../configure --prefix=${GLOG_LIB_ROOT})
  set(GLOG_BUILD_CMD   make libglog.la)
  set(GLOG_INSTALL_CMD make install)
  PATCH_CMD(${3RDPARTY_SRC}/glog-0.3.3.patch GLOG_PATCH_CMD)
  
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