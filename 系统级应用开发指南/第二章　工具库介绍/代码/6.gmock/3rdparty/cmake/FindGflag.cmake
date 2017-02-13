# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

###############################################################
# IMPORTANT NOTE: I (hausdorff) have copied a *lot* of stuff in this file from
#                 code I found at in the Akumuli[1] project. The original version
#                 of this file was also released under the Apache 2.0 license, and
#                 licensed to the ASF, making it fully compatible with the Mesos
#                 project.
#  [1] https://github.com/akumuli/Akumuli/blob/master/cmake/FindAPR.cmake
# This module helps to find the Apache Portable Runtime (APR) and APR-Util
# packages.
#
# USAGE: to use this module, add the following line to your CMake project:
#   find_package(Apr)
#############################################################################


if (GFLAG_FOUND)
  return()
endif (GFLAG_FOUND)

unset(GFLAG_LIB)
unset(GFLAG_INCLUDE_DIR)
unset(GFLAG_LIBS)

# NOTE: If this fails, stderr is ignored, and the output variable is empty.
# This has no deleterious effect our path search.
execute_process(
  COMMAND brew --prefix gflags
  OUTPUT_VARIABLE GFLAG_PREFIX
  OUTPUT_STRIP_TRAILING_WHITESPACE)

set(POSSIBLE_GFLAG_INCLUDE_DIRS
  ${POSSIBLE_GFLAG_INCLUDE_DIRS} 
  /usr/local/include/gflags 
  /usr/include/gflags
)

set(GFLAG_LIB_NAMES ${GFLAG_LIB_NAMES} gflags)

set(POSSIBLE_GFLAG_LIB_DIRS
  ${POSSIBLE_GFLAG_LIB_DIRS} 
  /usr/local/gflags/lib
  /usr/local/lib
  /usr/lib
  )

# SEARCH FOR GFLAG LIBRARIES.
###########################
find_path(GFLAG_INCLUDE_DIR gflags.h ${POSSIBLE_GFLAG_INCLUDE_DIRS})

find_library(
  GFLAG_LIB
  NAMES ${GFLAG_LIB_NAMES}
  HINTS ${POSSIBLE_GFLAG_LIB_DIRS}
  )

# Did we find the include directory?
string(
  COMPARE NOTEQUAL
  "GFLAG_INCLUDE_DIR-NOTFOUND"
  ${GFLAG_INCLUDE_DIR} # Value set to GFLAG_INCLUDE_DIR-NOTFOUND if not found.
  GFLAG_INCLUDE_DIR_FOUND
  )

# Did we find the library?
string(
  COMPARE NOTEQUAL
  "GFLAG_LIB-NOTFOUND"
  ${GFLAG_LIB} # Value set to `GFLAG_LIB-NOTFOUND` if not found.
  GFLAG_LIB_FOUND
  )

# GFLAG is considered "found" if we've both an include directory and an GFLAG binary.
if ("${GFLAG_LIB_FOUND}" AND "${GFLAG_INCLUDE_DIR_FOUND}")
  set(GFLAG_LIBS ${GFLAG_LIB})
  set(GFLAG_FOUND 1)
else ("${GFLAG_LIB_FOUND}" AND "${GFLAG_INCLUDE_DIR_FOUND}")
  set(GFLAG_FOUND 0)
endif ("${GFLAG_LIB_FOUND}" AND "${GFLAG_INCLUDE_DIR_FOUND}")

# Results.
if (GFLAG_FOUND)
  if (NOT GFLAG_FIND_QUIETLY)
    message(STATUS "Found GFLAG headers: ${GFLAG_INCLUDE_DIR}")
    message(STATUS "Found GFLAG library: ${GFLAG_LIBS}")
  endif (NOT GFLAG_FIND_QUIETLY)
else (GFLAG_FOUND)
  if (Gflag_FIND_REQUIRED)
    message(FATAL_ERROR "Could not find GFLAG library")
  endif (Gflag_FIND_REQUIRED)
endif (GFLAG_FOUND)

# (Deprecated declarations.)
set(NATIVE_GFLAG_INCLUDE_PATH ${GFLAG_INCLUDE_DIR} )
get_filename_component(NATIVE_GFLAG_LIB_PATH ${GFLAG_LIB} PATH)

# Export libraries variables.
mark_as_advanced(
  GFLAG_LIB
  GFLAG_INCLUDE_DIR
  )
