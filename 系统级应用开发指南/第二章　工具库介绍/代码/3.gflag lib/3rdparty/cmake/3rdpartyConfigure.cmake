message(STATUS "************************************************************")
message(STATUS "*******************3rd party Configure**********************")
message(STATUS "************************************************************")

# DEFINE DIRECTORY STRUCTURE FOR THIRD-PARTY LIBS.
##################################################
set(3RDPARTY_SRC ${CMAKE_SOURCE_DIR}/3rdparty)
set(3RDPARTY_BIN ${CMAKE_BINARY_DIR}/3rdparty)

# DEPENDENCIES
#
# Downloads, configures, and compiles the third-party libraries
###############################################################################

# Binary lib
find_package(Gflag REQUIRED)

# Source lib
if (NOT WIN32)
  EXTERNAL("glog" ${GLOG_VERSION} "${3RDPARTY_BIN}")
elseif (WIN32)
  # Glog 0.3.3 does not compile out of the box on Windows. Therefore, we require 0.3.4.
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

# Convenience variables for `lib` directories of built third-party dependencies.
if (WIN32)
  set(GLOG_LIB_DIR        ${GLOG_ROOT}-build/${CMAKE_BUILD_TYPE})
else (WIN32)
  set(GLOG_LIB_DIR        ${GLOG_LIB_ROOT}/lib)
endif (WIN32)

# Convenience variables for "lflags", the symbols we pass to CMake to generate
# things like `-L/path/to/glog` or `-lglog`.
set(GLOG_LFLAG        glog)

# Convenience variables for include directories of third-party dependencies.
if (WIN32)
  set(GLOG_INCLUDE_DIR     ${GLOG_ROOT}/src/windows)
else (WIN32)
  set(GLOG_INCLUDE_DIR     ${GLOG_LIB_ROOT}/include)
endif (WIN32)

# DEFINE LIBRARY DEPENDENCIES. Tells the process library build targets
# download/configure/build all third-party libraries before attempting to build.
################################################################################
set(TARGET_DEPENDENCIES
  ${TARGET_DEPENDENCIES}
  ${GLOG_TARGET}
)

# Target lib
set(TARGET_LIBS
  ${TARGET_LIBS}
  ${GLOG_LFLAG}
)

# Target include dirs
set(TARGET_INCLUDE_DIRS
  ${TARGET_INCLUDE_DIRS}
  ${GLOG_INCLUDE_DIR}
)

# Target include lib dirs
set(TARGET_LIB_DIRS
  ${GLOG_LIB_DIRS}
  ${GLOG_LIB_DIR}
)

message(STATUS "TARGET_LIBS: ${TARGET_LIBS}")

message(STATUS "************************************************************")