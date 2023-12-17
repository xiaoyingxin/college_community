# community
#### 介绍
校园论坛：集成注册登录、发帖回复、评论、点赞、关注、私信、系统通知、统计网络访问量等功能
#### 软件架构

项目整体架构图：
![屏幕截图](https://foruda.gitee.com/images/1702783110096305421/1af8be3c_11918105.png#id=JQwwo&originHeight=567&originWidth=1532&originalType=binary&ratio=1&rotation=0&showTitle=true&status=done&style=none&title=%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE "屏幕截图")
项目主要是基于spring开发，使用到了技术如下：
**SpringBoot** 整体编码开发。
**SpringMVC**：前后端交互  **MyBatis**：数据库交互  **SpringSecurity**: 权限认证，只有登录用户才能看个人信息，管理员删帖、查看日活、项目监控等等。
**Spring Email**：发送电子邮件 注册用户、 更改密码发送邮件验证。
**Redis**：存放token信息，用于登录后不同界面显示用户消息验证，保证验证码，存储点赞信息等等。
**Kafka**：消息队列，阻塞队列，用来用户关注点赞发帖后，服务器发送站内消息；存储elasticsearch搜索信息，上传照片等等。
**Elasticsearch: **搜索引擎，搜索帖子时使用，它可以实时搜索，全文搜索，复杂查询。
**Quartz**: 定时调度，在这里用于服务器定时计算帖子分数。
**Caffeine**: 缓存，内存缓存，缓存帖子信息，再次查询，大大提升了网站性能。
#### 项目特点

- 使用二级缓存，大大提升了网站性能，Caffeine缓存帖子，网站吞吐量由JMeter计算9.2/sec提升到160/sec
- 使用前缀树对帖子内容进行屏蔽词屏蔽
#### 项目难点
权限管理


