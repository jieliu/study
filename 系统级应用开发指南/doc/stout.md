# stout

参考文件：https://github.com/3rdparty/stout

使用时只需要将源码文件放到include文件目录下。部分库依赖第三方库:
- Boost
- Google's glog
- Google's protobuf
- Google's gmock/gtest

stout库包括以下几大类功能：
- 基础类库：Duration、Error、None、Nothing、Option、Owned、Result、Try、Stopwatch、UUID
- 集合：cache、hashmap、hashset、multihashmap
- 名空间：fs、gzip、JSON、lambda、net、os、path、protobuf(Requires protobuf
)、strings
- 异常：Exceptions
- 其他：copy、EXIT、fatal、gtest、numify、preprocessor、stringify

## 基础类库
