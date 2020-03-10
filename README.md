# tmall_ssm  项目介绍                                                                                                                                                                                                                
一个完全模仿天猫的商城网站，进行前后端的交互，支付流程梳理，领会淘宝购物的整个流程。
## 演示地址
* 暂时未提供
## 技术栈
* 后台
   * Spring+SpringMVC+Mybatis+Druid+Mysql
* 前端
   * BootStrap+JQuery+CSS+JavaScript
## 功能
* 基本功能
   * 商品发布、更新
   * 商品购买
   * 权限管理
   * 购物车及支付流程
## 商城设计及应用
* 前台需求列表
 ![q01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/q01.png)
 ![q02](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/q02.png)
* 后台需求列表
 ![h01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/h01.png)
 * 数据库
 ![s01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/s01.png)
# 运行源码
## 环境准备
* JDK环境："1.8.0_131"
* 数据库：Mysql 5.5
* 操作系统：Windows
* 依赖：apache-maven-3.5.0
* 开发工具 idea
* 服务器：tomcat7+
# 项目运行配置
## 一、idea打开项目（Maven项目）
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/p01.png)
* applicationContext.xml Spring配置文件
* generatorConfig.xml 逆向工程生成实体类及Mapper的配置文件
* jdbc.properties JDBC配置文件
* log4j.properties Log4j配置文件
* springMVC.xml SpringMVC配置文件
## 二、环境配置
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/s02.png)
* 连接数据库配置
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/l01.png)
* 进行日志级别设定
## 数据库导入
* 数据库导入可使用命令行或者客户端工具进行导入---此处不做过多赘述
## tomcat部署
* 请自行学习idea如何部署tomcat
# 测试地址
## 前端访问地址
http://localhost:8080/tmall_ssm/forehome
## 后台访问地址
http://localhost:8080/tmall_ssm/admin
# 打包
## 通过Maven进行打包
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/打包01.png)
## 打包成功后会在target目录下生成一个（项目名.war）的包
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/打包02.png)
# 商城截图
## 前端页面
* 首页
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/首页1.png)
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/首页2.png)
* 登录、注册
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/登录.png)
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/注册.png)
* 产品详情
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/产品详情.png)
* 提交订单
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/fore-提交订单.png)
* 付款
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/支付.png)
* 付款成功
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/付款成功.png)
* 查看订单
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/全部订单.png)
* 同类型产品界面
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/同类型--产品界面.png)
* 购物车
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/购物车.png)
* 模态登录
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/forehome/模态登录.png)
## 后端界面
* 登录、注册
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/后台登陆.png)
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/后台注册.png)
* 分类管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/分类管理.png)
* 图片管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/图片管理.png)
* 属性管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/属性管理.png)
* 产品管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/产品管理.png)
* 属性设置
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/属性设置.png)
* 前台用户管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/前台用户管理.png)
* 后台用户管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/后台用户管理.png)
* 订单管理
![p01](https://github.com/dahanshui/Images/blob/master/tmall_ssm_Images/admin/订单管理.png)
如果你觉得还不错的话，给个星星吧！

















