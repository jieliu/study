include(CheckCXXCompilerFlag)

# SYSTEM CONFIGURATION INFORMATION.
###################################
string(TOUPPER "${CMAKE_SYSTEM_NAME}"      OS_NAME)
string(TOUPPER "${CMAKE_SYSTEM_VERSION}"   OS_VER)
string(TOUPPER "${CMAKE_SYSTEM_PROCESSOR}" SYS_ARCH)

# CMAKE CONFIGURE LOGO.
#######################
message(STATUS "************************************************************")
message(STATUS "********* Beginning CMake configuration step *********")
message(STATUS "************************************************************")
message(STATUS "INSTALLATION PREFIX: ${CMAKE_INSTALL_PREFIX}")
message(STATUS "MACHINE SPECS:")
message(STATUS "    Hostname: ${HOSTNAME}")
message(STATUS "    OS:       ${OS_NAME}(${OS_VER})")
message(STATUS "    Arch:     ${SYS_ARCH}")
message(STATUS "    BitMode:  ${BIT_MODE}")
message(STATUS "    BuildID:  ${BUILDID}")
message(STATUS "************************************************************")

# SET UP TESTING INFRASTRUCTURE.
################################
enable_testing()

# CONFIGURE COMPILER.
#####################
include(CompilationConfigure)

# THIRD-PARTY CONFIGURATION.
############################
# NOTE: The third-party configuration variables exported here are used
# throughout the project, so it's important that this config script goes here.
include(3rdpartyConfigure)