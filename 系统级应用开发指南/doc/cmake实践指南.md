# CMake实践指南

前面几章已经废话了那么多，说明CMake的使用以及CMake常见的语法，并且针对Mesos代码结构大致讲解了下Mesos项目中如何使用CMake的。这一节中，将围绕Mesos代码中CMake来说明下，自己在创建项目时使用CMake的基本方法，并按照Mesos源码的样本给出一个最佳的实践指南。

## 从C++代码结构说起

基本的CPP工程代码结构如下，头文件、源码、第三方库文件以及编译文件。

```
include   //头文件
src       //源码
bin       //可执行文件目录
3rdparty  //第三方库
CMakeLists.txt 
build
```

对于复杂的项目中，可能还会用到CMake的一些宏文件和配置文件，本身项目中也会增加一些编译时的配置文件，可以在代码中直接使用。

```
include   //头文件
src       //源码
bin       //可执行文件目录
3rdparty  //第三方库
CMakeLists.txt 
build
cmake
config.h.in
```

## Mesos源码构建分步骤讲解

基础的结构如上所示，分别看一下CMakeLists.txt编写的模版格式：
    # THE MESOS PROJECT.
    ####################
    cmake_minimum_required(VERSION 2.8.10)

    project(Mesos)
    set(MESOS_MAJOR_VERSION 1)
    set(MESOS_MINOR_VERSION 2)
    set(MESOS_PATCH_VERSION 0)
    set(PACKAGE_VERSION
      ${MESOS_MAJOR_VERSION}.${MESOS_MINOR_VERSION}.${MESOS_PATCH_VERSION})

    set(MESOS_PACKAGE_VERSION ${PACKAGE_VERSION})
    set(MESOS_PACKAGE_SOVERSION 0)


    # CMAKE MODULE SETUP.
    #####################
    # Paths that are searched when `include(...)` is called.
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/3rdparty/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/3rdparty/libprocess/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/3rdparty/stout/cmake)
    list(
      APPEND
      CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/3rdparty/libprocess/cmake/macros)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/src/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/src/examples/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/src/master/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/src/slave/cmake)
    list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/src/tests/cmake)

    # Macros.
    include(Common)
    include(External)
    include(PatchCommand)
    include(Versions)
    include(VsBuildCommand)

    # Configuration.
    include(MesosConfigure)


    # SUBDIRECTORIES.
    #################
    add_subdirectory(3rdparty)
    add_subdirectory(src)


    # TESTS.
    ########
    add_custom_target(
      check ${STOUT_TESTS_TARGET}
      COMMAND ${PROCESS_TESTS_TARGET}
      COMMAND ${MESOS_TESTS_TARGET}
      DEPENDS ${STOUT_TESTS_TARGET} ${PROCESS_TESTS_TARGET}  ${MESOS_TESTS_TARGET}
      )

    # TARGET.
    ########

    # LINKING LIBRARIES BY DIRECTORY (might generate, e.g., -L/path/to/thing on Linux
    link_directories(${PROCESS_LIB_DIRS})

    # THE PROCESS LIBRARY (generates, e.g., libprocess.so, etc., on Linux).
    if (WIN32)
      add_library(${PROCESS_TARGET} STATIC ${PROCESS_SRC})
    else (WIN32)
      add_library(${PROCESS_TARGET} SHARED ${PROCESS_SRC})

      set_target_properties(
        ${PROCESS_TARGET} PROPERTIES
        POSITION_INDEPENDENT_CODE TRUE
        )
    endif (WIN32)

    set_target_properties(
      ${PROCESS_TARGET} PROPERTIES
      VERSION ${PROCESS_PACKAGE_VERSION}
      SOVERSION ${PROCESS_PACKAGE_SOVERSION}
      )

    add_dependencies(${PROCESS_TARGET} ${PROCESS_DEPENDENCIES})

    # ADD LINKER FLAGS (generates, e.g., -lglog on Linux)
    target_link_libraries(${PROJECT_NAME} ${PROCESS_LIBS})
    add_executable(${PROJECT_NAME} ${SRC_LIST})

## Mesos依赖库组织

从Mesos源码中可以看到，Mesos代码开发维护时，使用第三方库时，基本将用到的库源码都在代码中维护下来了。在很多项目中，通常通过在线安装的方式来预先安装好第三方库的头文件和链接库，经常编译一个项目要把第三方库装好，都要费很长时间，特别是依赖多、各个系统版本不一致的情况下。

Mesos源码的第三方库都在源码文件目录下，编译时将所有的源文件重新编译，减少了开发人员维护第三方库的代价。Mesos中如何来组织第三方库的依赖关系呢？我们以glog库为例来说明，只说明编译时的依赖关系如何组织。

阅读时可参考代码：

```
include //头文件
src //源码
bin //可执行文件目录
3rdparty //第三方库
  CMakeList.txt
  cmake
    Versions.cmake
    3rdpartyConfigure.cmake
  glog-0.3.3.tar.gz
  glog-0.3.3.patch
tests
CMakeLists.txt
build
support
m4
docs
cmake
  Common.cmake 
  PatchCommand.cmake
  CompliationConfigure.cmake //编译时相关配置信息
  ProjectConfigure.cmake  //项目相关的全局配置信息
  External.cmake //第三方库管理
config.h.in  //源码配置信息
```
Mesos中代码的组织、依赖关系管理按照CMakeList.txt文件来维系的。整个工程项目中使用到的宏文件、以及cmake脚本文件，都保存在cmake子目录下。

基本的依赖组织遵循以下几个步骤：
- 1、定义第三方库相关的变量信息。
- 2、ExternalProject_Add确定第三方库的编译方法
- 3、使用确定库与库之间的依赖关系，确保先使用的库先编译，并且添加第三方库include目录和lib目录。

下面分别看一下每一步骤中用的cmake文件。
1、定义ExternalProject_Add需要的参数信息，以及第三方库基本信息。其中第三方库基本信息，在External函数（External.cmake宏文件中定义）中定义。
```
###############################################################
# EXTERNAL defines a few variables that make it easy for us to track the
# directory structure of a dependency. In particular, if our library's name is
# boost, we will define the following variables:
#
#   BOOST_VERSION    (e.g., 1.53.0)
#   BOOST_TARGET     (a target folder name to put dep in e.g., boost-1.53.0)
#   BOOST_CMAKE_ROOT (where to have CMake put the uncompressed source, e.g.,
#                     build/3rdparty/boost-1.53.0)
#   BOOST_ROOT       (where the code goes in various stages of build, e.g.,
#                     build/.../boost-1.53.0/src, which might contain folders
#                     build-1.53.0-build, -lib, and so on, for each build step
#                     that dependency has)
function(EXTERNAL
  LIB_NAME
  LIB_VERSION
  BIN_ROOT)

  string(TOUPPER ${LIB_NAME} LIB_NAME_UPPER)

  # Names of variables we will set in this function.
  set(VERSION_VAR    ${LIB_NAME_UPPER}_VERSION)    # e.g., BOOST_VERSION
  set(TARGET_VAR     ${LIB_NAME_UPPER}_TARGET)     # e.g., BOOST_TARGET
  set(CMAKE_ROOT_VAR ${LIB_NAME_UPPER}_CMAKE_ROOT) # e.g., BOOST_CMAKE_ROOT
  set(ROOT_VAR       ${LIB_NAME_UPPER}_ROOT)       # e.g., BOOST_ROOT

  # Generate data that we will put in the above variables.
  # NOTE: bundled packages are untar'd into the BIN_ROOT, which is why we're
  #       pointing the source root into BIN_ROOT rather than SRC_ROOT.
  # TODO(hausdorff): SRC_DATA doesn't work for HTTP, LIBEV, GMOCK, or GTEST.
  set(VERSION_DATA    ${LIB_VERSION})
  set(TARGET_DATA     ${LIB_NAME}-${VERSION_DATA})
  set(CMAKE_ROOT_DATA ${BIN_ROOT}/${TARGET_DATA})
  set(ROOT_DATA       ${CMAKE_ROOT_DATA}/src/${TARGET_DATA})

  # Finally, EXPORT THE ABOVE VARIABLES. We take the data variables we just
  # defined, and export them to variables in the parent scope.
  #
  # NOTE: The "export" step is different from the "define the data vars" step
  #       because an expression like ${VERSION_VAR} will evaluate to
  #       something like "BOOST_VERSION", not something like "1.53.0". That
  #       is: to get the version in the parent scope we would do something
  #       like ${BOOST_VERSION}, which might evaluate to something like
  #       "1.53.0". So in this function, if you wanted to generate (e.g.) the
  #       target variable, it is not sufficient to write
  #       "${LIB_NAME}-${VERSION_VAR}", because this would result in
  #       something like "boost-BOOST_VERSION" when what we really wanted was
  #       "boost-1.53.0". Hence, these two steps are different.
  set(${VERSION_VAR}    # e.g., 1.53.0
    ${VERSION_DATA}
    PARENT_SCOPE)

  set(${TARGET_VAR}     # e.g., boost-1.53.0
    ${TARGET_DATA}
    PARENT_SCOPE)

  set(${CMAKE_ROOT_VAR} # e.g., build/3rdparty/boost-1.53.0
    ${CMAKE_ROOT_DATA}
    PARENT_SCOPE)

  set(${ROOT_VAR}       # e.g., build/.../boost-1.53.0/src
    ${ROOT_DATA}
    PARENT_SCOPE)
endfunction()

```
External函数定义第三方库源码目录、编译目录、编译目标等信息。编译参数信息需要自己根据第三方库编译信息来编写。例如glog库的编译：
```
set(GLOG_CONFIG_CMD  ${GLOG_ROOT}/src/../configure --prefix=${GLOG_LIB_ROOT})
set(GLOG_BUILD_CMD   make libglog.la)
set(GLOG_INSTALL_CMD make install)
PATCH_CMD(${3RDPARTY_SRC}/glog-0.3.3.patch GLOG_PATCH_CMD)
```
2、ExternalProject_Add确定第三方库的编译方法
```
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
```
> 在很多场景下INSTALL_COMMAND都为空，这意味着项目中不需要install对应的lib库。

3、添加第三方库依赖关系，以及编译最终目标时include目录、lib目录、链接库信息。
```
# DEFINE LIBRARY DEPENDENCIES. Tells the process library build targets download/configure/build all third-party libraries before attempting to build.
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

# Target lib dirs
set(TARGET_LIB_DIRS
  ${TARGET_LIB_DIRS}
  ${GLOG_LIB_DIR}
)
```
添加依赖关系
```
add_dependencies(${PROJECT_NAME} ${TARGET_DEPENDENCIES})
```
