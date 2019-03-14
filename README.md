# Pigeon说明文档

## Pigeon的简介
Pigeon是基于信鸽设计一个微型推送服务的示例，它是使用Spring Boot + 信鸽服务端SDK + 信鸽Android SDK来搭建的微型的推送服务。本仓库中包括有服务端和客户端的完整示例代码，还有信鸽Java服务端文档和SDK的文件目录。

相关阅读：[《基于信鸽设计一个微型推送服务》](https://www.jianshu.com/p/ada6abb078db)

## Pigeon的目录
- **server** : 服务端的源码，使用IDEA开发工具打开。
- **client** : 客户端的源码，使用Android Studio开发工具打开。
- **push-sdk** : 该文件目录中包含了信鸽Java服务端的SDK，开发文档和信鸽官方提供的示例源码

## 准备工作
一、使用git工具克隆该个仓库到本地。
```git
git clone https://github.com/mailhu/pigeon.git
```
二、前往 [腾讯移动推送 | 信鸽](https://xg.qq.com) 平台注册一个帐号，然后创建一个推送应用，为了确保本示例的Android项目可以顺利运行，请按下图所示填写应用的信息。
![](https://github.com/mailhu/pigeon/blob/master/image/config.png)

## 服务端
一、使用IDEA打开server目录，若你在信鸽平台上已成创建了一个应用，请把该应用的accessId和secretKey填写到服务端的项目示例中，填写的位置在PushService.java文件的如下两行。
```java
//填写你信鸽提供给你的accessId
private final static long ACCESS_ID = 0;

//填写你信鸽提供给你的secretKey
private final static String SECRET_KEY = "";
```
二、然后debug启动项目或者编译打包再启动项目。

## 移动端
一、使用Android Studio打开client目录，再打开app模块下的build.gradle文件，然后把信鸽accessId和accessKey填写到如下位置。
```gradle
//填写accessid和和accesskey
manifestPlaceholders = [
    XG_ACCESS_ID:"注册应用的accessid",
    XG_ACCESS_KEY:"注册应用的accesskey",
]
```
二、打开AndroidManifest.xml文件，然后把信鸽accessId和accessKey填写到如下位置。
```xml
<!-- 【必须】 请将YOUR_ACCESS_ID修改为APP的AccessId，“21”开头的10位数字，中间没空格 -->
<meta-data
    android:name="XG_V2_ACCESS_ID"
    android:value="YOUR_ACCESS_ID" />

<!-- 【必须】 请将YOUR_ACCESS_KEY修改为APP的AccessKey，“A”开头的12位字符串，中间没空格 -->
<meta-data
    android:name="XG_V2_ACCESS_KEY"
    android:value="YOUR_ACCESS_KEY" />
```
三、打开MainActivity.java文件，把下面的IP地址改为你服务端本机的IP地址。
```java
private final static String URL = "http://192.168.43.188:8080/api/mailbox";
```
四、编译Android的项目示例，然后安装到手机上即可。

## 测试与测试效果
如果是服务器是公网IP，可以直接测试本推送服务，若不是服务器是使用本地的电脑，请确保手机和电脑连接到同一个WiFi中才可以成功测试推送服务。注意：无论公网IP还是私网IP，服务器都要在防火墙中打开8080端口。
![](https://github.com/mailhu/pigeon/blob/master/image/login.gif)

![](https://github.com/mailhu/pigeon/blob/master/image/user_1.gif)
![](https://github.com/mailhu/pigeon/blob/master/image/user_2.gif)



## License
```
MIT License

Copyright (c) 2019 张观湖

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
