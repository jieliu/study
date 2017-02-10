###############################################################
# Exports a variable CMAKE_NOOP as noop operation.
#
# NOTE: This is especially important when building third-party libraries on
# Windows; the default behavior of `ExternalProject` is to try to assume that
# third-party libraries can be configured/built/installed with CMake, so in
# cases where this isn't true, we have to "trick" CMake into skipping those
# steps by giving it a noop command to run instead.
set(CMAKE_NOOP ${CMAKE_COMMAND} -E echo)
