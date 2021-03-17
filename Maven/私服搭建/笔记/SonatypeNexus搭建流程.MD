#### 下载地址
http://www.sonatype.org/nexus/go
#### 安装配置：windows版本
```
打开zip解压文件下的 ../nexus-3.14.0-04-win64/nexus-3.14.0-04/etc/nexus-default.properties。如下属性可以自定义修改。
application-host : Nexus服务监听的主机
application-port: Nexus服务监听的端口
nexus-context-path : Nexus服务的上下文路径

将bin目录配置环境变量

安装：以管理员身份打开cmd
nexus.exe /install

启动
nexus.exe /start

登录
http://localhost:8081/

用户名admin，密码根据提示查看即可

查看仓库
![image](https://user-images.githubusercontent.com/34163407/111490092-b014ab00-8775-11eb-8ae1-60a69a31ea4d.png)
```
#### 配置setting文件
```
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
		  
  <localRepository>D:\codeTool\apache-maven-3.6.3\localRepository</localRepository>
  <pluginGroups>
  </pluginGroups>
  <proxies>
  </proxies>

 <servers>
    <!--第一个nexus-xu要和下面的mirror中的id一致，代表拉取是也需要进行身份校验-->
    <server>
      <id>nexus</id>
      <username>admin</username>
      <password>www123321</password>
    </server>
     <server>
      <!--这两个前面讲过，是jar上传时候进行的验证，id对应的是pom中id属性的值-->
      <id>releases</id>
      <username>admin</username>
      <password>www123321</password>
    </server>
     <server>
      <id>snapshots</id>
      <username>admin</username>
      <password>www123321</password>
    </server>
  </servers>
<!--
  <mirrors>
    <mirror>
        <id>nexus</id>
        <name>internal nexus repository</name>
        <url>http://localhost:8081/repository/maven-central/</url>
        <mirrorOf>*</mirrorOf>
    </mirror>
	    <mirror>
        <id>releases</id>
        <name>releases nexus repository</name>
        <url>http://localhost:8081/repository/maven-releases/</url>
        <mirrorOf>*</mirrorOf>
    </mirror>
	    <mirror>
        <id>snapshots</id>
        <name>snapshots nexus repository</name>
        <url>http://localhost:8081/repository/maven-snapshots/</url>
        <mirrorOf>*</mirrorOf>
    </mirror>
  </mirrors>
 --> 

  <profiles>
<profile>
  <!--ID用来确定该profile的唯一标识-->
           <id>jdk-1.8</id>
           <activation>
               <activeByDefault>true</activeByDefault>
               <jdk>1.8</jdk>
           </activation>
           <properties>
               <maven.compiler.source>1.8</maven.compiler.source>
               <maven.compiler.target>1.8</maven.compiler.target>
               <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
           </properties>
   </profile>

    <profile>
  <id>nexus</id>
   <!-- 远程仓库列表 -->
  <repositories>
    <repository>
      <id>nexus</id>
      <name>Nexus Central</name>
     <!-- 虚拟的URL形式,指向镜像的URL-->
      <url>http://localhost:8081/repository/maven-central/</url>
      <layout>default</layout>
     <!-- 表示可以从这个仓库下载releases版本的构件-->  
      <releases>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </releases>
     <!-- 表示可以从这个仓库下载snapshot版本的构件 -->  
      <snapshots>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
	<!--
	<repository>
      <id>releases</id>
      <name>Nexus releases</name>
      <url>http://localhost:8081/repository/maven-releases/</url>
      <layout>default</layout>
      <releases>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </releases>
      <snapshots>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
	<repository>
      <id>snapshots</id>
      <name>Nexus snapshots</name>
      <url>http://localhost:8081/repository/maven-snapshots/</url>
      <layout>default</layout>
      <releases>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </releases> 
      <snapshots>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
	-->
  </repositories>
   <!-- 插件仓库列表 -->
  <pluginRepositories>
    <pluginRepository>
      <id>nexus</id>
      <name>Nexus Central</name>
      <url>http://localhost:8081/repository/maven-central/</url>
      <layout>default</layout>
      <snapshots>
        <enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
      </snapshots>
      <releases>
         <enabled>true</enabled>
		 <updatePolicy>always</updatePolicy>
      </releases>
    </pluginRepository>
  </pluginRepositories>
   </profile>
  </profiles>

  <activeProfiles>
     <!--需要激活 <profile>中的ID才生效-->  
    <activeProfile>nexus</activeProfile>
    <activeProfile>jdk-1.8</activeProfile>
  </activeProfiles>
</settings>
```
#### 配置pom文件
```
    <distributionManagement>
        <repository>
            <!--id的名字可以任意取，但是在setting文件中的属性<server>的ID与这里一致-->
            <id>releases</id>
            <!--指向仓库类型为host(宿主仓库）的储存类型为Release的仓库-->
            <url>http://localhost:8081/repository/maven-releases/</url>
        </repository>
        <snapshotRepository>
            <id>snapshots</id>
            <!--指向仓库类型为host(宿主仓库）的储存类型为Snapshot的仓库-->
            <url>http://localhost:8081/repository/maven-snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
```

参考地址：
```
https://www.cnblogs.com/qingbaizhinian/p/12872453.html
https://www.cnblogs.com/kevingrace/p/6201984.html
https://www.cnblogs.com/hujunzheng/p/9807646.html
https://blog.csdn.net/qq_30764991/article/details/80051603
https://www.cnblogs.com/qdhxhz/p/9808642.html
```
