## C++项目starter
本节综合cmake、ctest、cpack等工具，给出一个完整的工程项目结构，并结合glog、gmock、gtest、stout库给出创建cmake工程项目的完整过程。本章前几节中在介绍各个库的时候没有使用源码的方式，而是直接使用在线安装方式。在本节中使用源码的方式来说明，使用源码优点在于后续维护、代码移植能够更方便一些。建议在创建项目中，对一些关键的类库，使用源码来编译和维护。

https://github.com/3rdparty 中维护了mesos项目中使用的第三方库以及一些常用的代码库，值得大家在C++工程项目中借鉴使用。

### starter

### IDE

综合多种IDE的使用来看，qt对cmake编译系统的支持是最好的，推荐大家在开发c++项目中使用。
![QT](/assets/选区_015.png)


### 项目管理
项目开发中，对项目的开发进度进行追踪管理、以及对外部反馈的快速响应，都能极大地促进项目开发过程。
例如mesos项目实施过程：https://issues.apache.org/jira/browse/MESOS ，使用gitlab私有git仓库时也可以创建对应项目的里程碑、问题、wiki等功能。