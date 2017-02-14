# 序列化protobuf

protobuf使用自定义的语法格式来定义结构化数据的基本格式，用于消息格式的序列化和反序列化。使用时，先编写对应的proto文件，并将其编译成源码。

## 基本概念
* .proto文件：数据协议，用于基本的消息通信格式
* modifiers，对应结构话对象属性的更改状态
   
   required 不可以增加或删除的字段，必须初始化
   
   optional 可选字段，可删除，可以不初始化
   
   repeated 可重复字段，生成的是List

* Message，在proto文件里，数据的协议时以Message的形式表现的。
* Build,生成具体的java类时，例如Person.java，同时会存在build方法，用于消息对象的初始化。

## 使用入门

###　安装对应开发库

可以通过源码安装，也可以直接安装对应的二进制包。开发者使用时需要安装对应的开发库和proto编译器，用于将对应的proto文件编译成c++/java/python的代码。
```
aptitude search libprotobuf
aptitude search protobufc
sudo apt-get install libprotobuf-dev
sudo apt-get install compiler
```
### proto文件

proto文件的编写要遵循特定的语法规范格式，proto文件的基本格式：

```
//License

package mesos;
message message1 { 

}

/**
* 这是注释
**/
message message2 {

}
```
### message语法



### 代码风格
3.1   消息与字段名
使用骆驼风格的大小写命名，即单词首字母大写，来做消息名。使用GNU的全部小写，使用下划线分隔的方式定义字段名:
message SongServerRequest {
    required string song_name=1;
}
使用这种命名方式得到的名字如下:
```
C++:
    const string& song_name() {...}
    void set_song_name(const string& x) {...}

Java:
    public String getSongName() {...}
    public Builder setSongName(String v) {...}
```

3.2   枚举
使用骆驼风格做枚举名，而用全部大写做值的名字:
```
enum Foo {
    FIRST_VALUE=1;
    SECOND_VALUE=2;
}
```
每个枚举值最后以分号结尾，而不是逗号。

3.3   服务
如果你的 .proto 文件定义了RPC服务，你可以使用骆驼风格:
```
service FooService {
    rpc GetSomething(FooRequest) returns (FooResponse);
}
```

```
/**
 * Describes a command, executed via: '/bin/sh -c value'. Any URIs specified
 * are fetched before executing the command.  If the executable field for an
 * uri is set, executable file permission is set on the downloaded file.
 * Otherwise, if the downloaded file has a recognized archive extension
 * (currently [compressed] tar and zip) it is extracted into the executor's
 * working directory. This extraction can be disabled by setting `extract` to
 * false. In addition, any environment variables are set before executing
 * the command (so they can be used to "parameterize" your command).
 */
message CommandInfo {
  message URI {
    required string value = 1;
    optional bool executable = 2;

    // In case the fetched file is recognized as an archive, extract
    // its contents into the sandbox. Note that a cached archive is
    // not copied from the cache to the sandbox in case extraction
    // originates from an archive in the cache.
    optional bool extract = 3 [default = true];

    // If this field is "true", the fetcher cache will be used. If not,
    // fetching bypasses the cache and downloads directly into the
    // sandbox directory, no matter whether a suitable cache file is
    // available or not. The former directs the fetcher to download to
    // the file cache, then copy from there to the sandbox. Subsequent
    // fetch attempts with the same URI will omit downloading and copy
    // from the cache as long as the file is resident there. Cache files
    // may get evicted at any time, which then leads to renewed
    // downloading. See also "docs/fetcher.md" and
    // "docs/fetcher-cache-internals.md".
    optional bool cache = 4;
  }

  repeated URI uris = 1;

  optional Environment environment = 2;

  // There are two ways to specify the command:
  // 1) If 'shell == true', the command will be launched via shell
  //		(i.e., /bin/sh -c 'value'). The 'value' specified will be
  //		treated as the shell command. The 'arguments' will be ignored.
  // 2) If 'shell == false', the command will be launched by passing
  //		arguments to an executable. The 'value' specified will be
  //		treated as the filename of the executable. The 'arguments'
  //		will be treated as the arguments to the executable. This is
  //		similar to how POSIX exec families launch processes (i.e.,
  //		execlp(value, arguments(0), arguments(1), ...)).
  // NOTE: The field 'value' is changed from 'required' to 'optional'
  // in 0.20.0. It will only cause issues if a new framework is
  // connecting to an old master.
  optional bool shell = 6 [default = true];
  optional string value = 3;
  repeated string arguments = 7;

  // Enables executor and tasks to run as a specific user. If the user
  // field is present both in FrameworkInfo and here, the CommandInfo
  // user value takes precedence.
  optional string user = 5;
}
```

### 入门示例

编写对应的proto文件
```
package message; 
message helloworld 
 { 
    required int32     id = 1;  // ID 
    required string    str = 2;  // str 
    optional int32     opt = 3;  //optional field 
 }
```
将对应的文件编译成源码
```
基本格式
protoc -I=$SRC_DIR --cpp_out=$DST_DIR $SRC_DIR/addressbook.proto
具体
g++ test.cpp helloword.pb.cc -lprotobuf
```
## 高级话题



reference：

http://www.ibm.com/developerworks/cn/linux/l-cn-gpb/

http://blog.csdn.net/menuconfig/article/details/12837173