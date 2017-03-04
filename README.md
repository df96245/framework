# Java项目实用基础框架
## 学习和做项目不断积累的搭建的框架，结合mybatis通用Mapper插件，整合出通用Service框架，只需要继承对应的Service类和Dao接口，便可以实现快速开发，无需进行基础普通的Sql编写

* 1 框架是基于Spring Framework基础上搭建的一个Java基础开发平台，以Spring MVC为控制层，MyBatis为数据访问层，Apache Shiro为权限授权层，Ehcahe对常用数据进行缓存，redis 进行会话缓存和权限缓存,使用
* 2 mybatis使用了mybatis通用Mapper插件，极大方便了开发速度 [This link](http://git.oschina.net/free/Mapper)
* 3 Shiro的默认过滤器会为我们重定向到相对应的路径，这里基本全部改写成返回状态码和状态信息

## 技术选型

* 核心框架：Spring Framework 4.3.5
* 安全框架：Apache Shiro 1.2
* 视图框架：Spring MVC 4.3.5
* 任务调度：Spring Task 4.3.5
* 持久层框架：MyBatis 3.2
* 数据库连接池：commons-dbcp 1.4
* 缓存框架：Ehcache 2.6、Redis
* 日志管理：log4j2 slf4j

## 文件目录说明
**源码主目录: scau.zzf**

**base:主要存放基础类目录，不需要经常更改的类文件,这里对部分文件做主要说明**
> common:常用的通用继承类

>> BaseController ：每个Controller 继承的抽象类

>> IBaseMapper ：每个Dao接口 继承的接口

>> IBaseService ：每个Service接口继承的接口

>> Unique ：每个实体类继承的父类

> shiro：存放改写后的shiro过滤器

> dictionary :数据字典

> service :存放Service接口，每个接口需继承 IBaseService

>>Imp: 存放Service实现类，这里主要是用于一些较复杂的比如表关联操作，mybatis通用Mapper无法实现的SQL，需要自己手写的方法，便在此实现

> task : 存放实现任务调度的类，相对应需要修改spring-task.xml配置

> web : Controller层

**资源主目录: resources**

>config :存放配置文件

>>spring

## 如何上手

* 推荐使用IntellJ IDEA

* 修改config/properties的数据库文件，安装redis，然后修改redis.conf中的密码选项，redis.conf默认没有密码选项，若不进行修改，则需注释spring-redis.xml和spring-shiro.xml的中redis配置的密码选项

* 建立对应的实体类（继承Unique） Dao（继承IBaseMapper） Service (继承IBaseService(T)) Impl （继承AbstractBaseService和实现对应的Service接口）

* 已IUserSerive为例子，通过自动注入，IUserService就可以使用定义和实现在AbstractBaseService中的各个方法

## 版权声明
iBase4J使用 [Apache License 2.0][] 协议.

##交流
由于本人技术有限,有不足的地方还请各位提出来,或者加QQ593178092 进行交流