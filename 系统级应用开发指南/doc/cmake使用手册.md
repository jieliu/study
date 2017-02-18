# CMake使用手册
cmake变量使用${}方式取值,但是在IF控制语句中是直接使用变量名。特别注意的是，环境变量使用$ENV{}方式取值,使用SET(ENV{VAR} VALUE)赋值。

CMake指令大小写无关的，指令的基本形式是：
```
指令(参数1 参数2…)
```
参数使用括弧括起,参数之间使用空格或分号分开。下面会从变量和指令来详细介绍CMake语法。

## cmake中的变量

### 内置变量
```
  PROJECT_SOURCE_DIR 工程的根目录
   
  PROJECT_BINARY_DIR 运行cmake命令的目录,通常是${PROJECT_SOURCE_DIR}/build
   
  CMAKE_INCLUDE_PATH 环境变量,非cmake变量
   
  CMAKE_LIBRARY_PATH 环境变量
   
  CMAKE_CURRENT_SOURCE_DIR 当前处理的CMakeLists.txt所在的路径
   
   CMAKE_CURRENT_BINARY_DIR target编译目录, 使用ADD_SURDIRECTORY(src bin)可以更改此变量的值

  CMAKE_CURRENT_LIST_FILE 输出调用这个变量的CMakeLists.txt的完整路径
  
  CMAKE_CURRENT_LIST_LINE 输出这个变量所在的行
  
  CMAKE_MODULE_PATH 定义自己的cmake模块所在的路径
SET(CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/cmake),然后可以用INCLUDE命令来调用自己的模块

  EXECUTABLE_OUTPUT_PATH 重新定义目标二进制可执行文件的存放位置
  
  LIBRARY_OUTPUT_PATH 重新定义目标链接库文件的存放位置
  
  PROJECT_NAME 返回通过PROJECT指令定义的项目
  
  CMAKE_ALLOW_LOOSE_LOOP_CONSTRUCTS 用来控制IF ELSE语句的书写方式
系统信息
  CMAKE_MAJOR_VERSION cmake主版本号,如2.8.6中的2
  
  CMAKE_MINOR_VERSION cmake次版本号,如2.8.6中的8
  
  CMAKE_PATCH_VERSION cmake补丁等级,如2.8.6中的6
  
  CMAKE_SYSTEM 系统名称,例如Linux-2.6.22
  
  CAMKE_SYSTEM_NAME 不包含版本的系统名,如Linux
  
  CMAKE_SYSTEM_VERSION 系统版本,如2.6.22
  
  MAKE_SYSTEM_PROCESSOR 处理器名称,如i686
  
  UNIX 在所有的类UNIX平台为TRUE,包括OS X和cygwin
  
  WIN32 在所有的win32平台为TRUE,包括cygwin
开关选项

  BUILD_SHARED_LIBS 控制默认的库编译方式。如果未进行设置,使用ADD_LIBRARY时又没有指定库类型,默认编译生成的库都是静态库 （可在t3中稍加修改进行验证）
  
  CMAKE_C_FLAGS 设置C编译选项
  
  CMAKE_CXX_FLAGS 设置C++编译选项
```
### 开关选项
```
CMAKE_ALLOW_LOOSE_LOOP_CONSTRUCTS,用来控制 IF ELSE 语句的书写方式。

BUILD_SHARED_LIBS这个开关用来控制默认的库编译方式,如果不进行设置,使用 ADD_LIBRARY 并没有指定库类型的情况下,默认编译生成的库都是静态库。如果 SET(BUILD_SHARED_LIBS ON)后,默认生成的为动态库。

CMAKE_C_FLAGS设置 C 编译选项,也可以通过指令 ADD_DEFINITIONS()添加。

CMAKE_CXX_FLAGS设置 C++编译选项,也可以通过指令 ADD_DEFINITIONS()添加。
```
### 自定义变量
基本变量 set(name value)

环境变量 set(env{name} value)
 
### cmake常用命令

部分常用命令列表：
```
  ● PROJECT
  　PROJECT(projectname [CXX] [C] [Java])，指定工程名称,并可指定工程支持的语言。
  ● SET
SET(VAR [VALUE] [CACHE TYPE DOCSTRING [FORCE]])
定义变量(可以定义多个VALUE,如SET(SRC_LIST main.c util.c reactor.c))
  ● MESSAGE
  MESSAGE([SEND_ERROR | STATUS | FATAL_ERROR] “message to display” …)
  向终端输出用户定义的信息或变量的值
    SEND_ERROR, 产生错误,生成过程被跳过
    STATUS, 输出前缀为—的信息
    FATAL_ERROR, 立即终止所有cmake过程
  ● ADD_EXECUTABLE
    ADD_EXECUTABLE(bin_file_name ${SRC_LIST})生成可执行文件
  ● ADD_LIBRARY
      ADD_LIBRARY(libname [SHARED | STATIC | MODULE] [EXCLUDE_FROM_ALL] SRC_LIST)
      生成动态库或静态库
        SHARED 动态库
        STATIC 静态库
        MODULE 在使用dyld的系统有效,若不支持dyld,等同于SHARED
        EXCLUDE_FROM_ALL 表示该库不会被默认构建
  ● SET_TARGET_PROPERTIES
        设置输出的名称,设置动态库的版本和API版本
  ● ADD_SUBDIRECTORY
  ADD_SUBDIRECTORY(src_dir [binary_dir] [EXCLUDE_FROM_ALL])
向当前工程添加存放源文件的子目录,并可以指定中间二进制和目标二进制的存放位置
    EXCLUDE_FROM_ALL含义：将这个目录从编译过程中排除
  ● INCLUDE_DIRECTORIES
INCLUDE_DIRECTORIES([AFTER | BEFORE] [SYSTEM] dir1 dir2 … )
  向工程添加多个特定的头文件搜索路径。
  ● LINK_DIRECTORIES
    LINK_DIRECTORIES(dir1 dir2 …)
    添加非标准的共享库搜索路径
  ● TARGET_LINK_LIBRARIES
    TARGET_LINK_LIBRARIES(target lib1 lib2 …)
为target添加需要链接的共享库
  ● ADD_DEFINITIONS
    向C/C++编译器添加-D定义
    ADD_DEFINITIONS(-DENABLE_DEBUG -DABC),参数之间用空格分隔
  ● ADD_DEPENDENCIES
    ADD_DEPENDENCIES(target-name depend-target1 depend-target2 …)
定义target依赖的其他target,确保target在构建之前,其依赖的target已经构建完毕
  ● AUX_SOURCE_DIRECTORY
AUX_SOURCE_DIRECTORY(dir VAR)
发现一个目录下所有的源代码文件并将列表存储在一个变量中
把当前目录下的所有源码文件名赋给变量DIR_HELLO_SRCS
  ● INCLUDE
INCLUDE(file [OPTIONAL]) 用来载入CMakeLists.txt文件
INCLUDE(module [OPTIONAL])用来载入预定义的cmake模块
OPTIONAL参数的左右是文件不存在也不会产生错误
可以载入一个文件,也可以载入预定义模块（模块会在CMAKE_MODULE_PATH指定的路径进行搜索）
载入的内容将在处理到INCLUDE语句时直接执行
  ● FIND_
      ○ FIND_FILE(<VAR> name path1 path2 …)
VAR变量代表找到的文件全路径,包含文件名
      ○ FIND_LIBRARY(<VAR> name path1 path2 …)
VAR变量代表找到的库全路径,包含库文件名
FIND_LIBRARY(libX X11 /usr/lib)
					IF (NOT libx)
    						MESSAGE(FATAL_ERROR "libX not found")
					ENDIF(NOT libX)

      ○ FIND_PATH(<VAR> name path1 path2 …)
VAR变量代表包含这个文件的路径
      ○ FIND_PROGRAM(<VAR> name path1 path2 …)
VAR变量代表包含这个程序的全路径
      ○ FIND_PACKAGE(<name> [major.minor] [QUIET] [NO_MODULE] [[REQUIRED | COMPONENTS] [componets …]])
用来调用预定义在CMAKE_MODULE_PATH下的Find<name>.cmake模块,你也可以自己定义Find<name>
模块,通过SET(CMAKE_MODULE_PATH dir)将其放入工程的某个目录供工程使用
```
基本语法规则：
```
  ● IF
语法：
IF (expression)
    COMMAND1(ARGS ...)
    COMMAND2(ARGS ...)
    ...
ELSE (expression)
    COMMAND1(ARGS ...)
    COMMAND2(ARGS ...)
    ...
ENDIF (expression) # 一定要有ENDIF与IF对应

IF语句中的逻辑判断
  IF (not exp), 与上面相反
  IF (var1 AND var2)
  IF (var1 OR var2)
  IF (COMMAND cmd) 如果cmd确实是命令并可调用,为真
  IF (EXISTS dir) IF (EXISTS file) 如果目录或文件存在,为真
  IF (file1 IS_NEWER_THAN file2),当file1比file2新,或file1/file2中有一个不存在时为真,文件名需使用全路径
  IF (IS_DIRECTORY dir) 当dir是目录时,为真
  IF (DEFINED var) 如果变量被定义,为真
  IF (var MATCHES regex) 此处var可以用var名,也可以用${var}
  IF (string MATCHES regex)
当给定的变量或者字符串能够匹配正则表达式regex时为真。比如：
  IF ("hello" MATCHES "ell")
    MESSAGE("true")
  ENDIF ("hello" MATCHES "ell")

数字比较表达式
  IF (variable LESS number)
  IF (string LESS number)
  IF (variable GREATER number)
  IF (string GREATER number)
  IF (variable EQUAL number)
  IF (string EQUAL number)
按照字母表顺序进行比较
  IF (variable STRLESS string)
  IF (string STRLESS string)
  IF (variable STRGREATER string)
  IF (string STRGREATER string)
  IF (variable STREQUAL string)
  IF (string STREQUAL string)
一个小例子,用来判断平台差异：
  IF (WIN32)
      MESSAGE(STATUS “This is windows.”)
  ELSE (WIN32)
      MESSAGE(STATUS “This is not windows”)
  ENDIF (WIN32)

  ● WHILE
语法：
WHILE(condition)
    COMMAND1(ARGS ...)
    COMMAND2(ARGS ...)
    ...
ENDWHILE(condition)

其真假判断条件可以参考IF指令
  ● FOREACH
FOREACH指令的使用方法有三种形式：
      a. 列表
语法：
FOREACH(loop_var arg1 arg2 ...)
     COMMAND1(ARGS ...)
     COMMAND2(ARGS ...)
 ...
ENDFOREACH(loop_var)

示例：
AUX_SOURCE_DIRECTORY(. SRC_LIST)
FOREACH(F ${SRC_LIST})
     MESSAGE(${F})
ENDFOREACH(F)

      b. 范围
语法：
FOREACH(loop_var RANGE total)
    COMMAND1(ARGS ...)
    COMMAND2(ARGS ...)
    ...
ENDFOREACH(loop_var)

示例：
从0到total以１为步进
FOREACH(VAR RANGE 10)
   MESSAGE(${VAR})
ENDFOREACH(VAR)
输出：
012345678910
      c. 范围和步进
语法：
FOREACH(loop_var RANGE start stop [step])
    COMMAND1(ARGS ...)
    COMMAND2(ARGS ...)
    ...
ENDFOREACH(loop_var)
FOREACH(A RANGE 5 15 3)
    MESSAGE(${A})
ENDFOREACH(A)
```