### 入门
###### 背景
- 网站规模扩大，常规垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，需要一个治理系统确保架构有条不紊
- 水平应用架构(扩展):增加机器集群
- 垂直应用架构：将应用拆分成互不相干的几个应用，提升效率，MVC框架
- 分布式服务架构：垂直应用之间会有交互，需要将核心业务抽取，更细粒度的拆分服务，需要分布式服务框架(RPC)来提高业务复用
- 流动计算架构：基于访问压力实时管理集群容量，提高集群利用率，SOA
###### 需求
- 大规模服务化之前，也有其他RPC框架(如RMI、Hessian)，也可通过F5等硬件进行负载均衡，但当服务扩大，管理URL地址困难，F5单点压力增大
- 需要一个服务注册中心，动态的注册和发现服务，消费方获取提供方地址列表
- 需要软负载均衡和Failover(失效转移)
- 展示出服务应用间的依赖关系
- 统计调用量，响应时间，作为容量规划的参考指标
- 可以动态调整权重
###### 架构
- Provider：暴露服务的服务提供方
- Consumer：调用远程服务的服务消费方
- Registry：服务注册与发现的注册中心
- Monitor：统计服务的调用次数和调用时间的监控中心
- Container：服务运行容器
- 连通性、健壮性、升级性
### 快速开始
- 见代码quickstart
### 成熟度
- 查阅文档
### 配置
- 方法级别有限，接口级别次之，全局配置再次之
- 如果级别一样，则消费方有限，提供方次之
- 其中服务提供方配置，通过URL经由注册中心传递给消费方
- 建议由服务提供方设置超时，因为服务提供方更清楚一个方法执行时间
- 理论上消费方配置可只在接口级别配置，其余配置缺省提供
###### XML配置
- 在项目中配置dubbo应用名称、注册中心、协议、暴露服务、引用服务等
###### 动态配置中心
- 利用配置中心实现外部化配置统一管理
- 外部配置优先级高于本地配置
- 外部配置分全局和应用两个级别
- zookeeper为例，默认所有配置都在dubbo.config节点下，dubbo.config.dubbo全局节点，dubbo.config.application应用节点
- namespace用于不同配置的环境隔离
- dubbo.properties存储具体配置内容
- apollo为例，namespace区分全局和应用级别
- 原理：将配置文件从远程拉取到本地，然后和本地的配置做一次融合
- dubbo提供了api让用户将自己组织好的配置塞给Dubbo框架
- zookeeper和apollo都还包含服务治理规则：覆盖规则，标签路由，条件路由
###### 属性配置
- 使用.properties配置文件
- 优先级：-D启动参数 > XML > Properties
###### 自动加载环境变量
- 从2.7.3版本开始，dubbo会自动从约定key中读取配置，已key-value的形式写入到URL中
- dubbo.labels和dubbo.env.keys
###### API配置
- 使用Dubbo提供的API进行dubbo配置i，仅适用于OpenAPI, ESB, Test, Mock 等系统集成
###### 注解配置
- 2.6.3及以上版本支持
###### 配置加载流程
- 支持多层级的配置，按照预定优先级实现各层级的覆盖，最终所有配置汇总到数据总线URL后，驱动后序的服务暴露、引用等流程
- 配置来源(优先级从高到低)：JVM -D参数、外部化配置、ServiceConfig等变成接口采集的配置、本地配置文件
- 配置级别
```
# 应用级别
dubbo.{config-type}[.{config-id}].{config-item}={config-item-value}
# 服务级别
dubbo.service.{interface-name}[.{method-name}].{config-item}={config-item-value}
dubbo.reference.{interface-name}[.{method-name}].{config-item}={config-item-value}
# 多配置项
dubbo.{config-type}s.{config-id}.{config-item}={config-item-value}
```
### 用法示例
###### 启动时检查
- 
