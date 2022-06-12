### ICloud Spring Cloud 示例项目
* 没有前端。
* 后端采用Spring Cloud全家桶，提供较完善的功能提供参考和学习。
* 文档中不会对使用到的技术过多介绍，阅读代码的门槛和自身积累的知识正相关。
* 为 [icloud-smart-core-transfer-service](https://github.com/yijianguanzhu/icloud-smart-core-transfer-service) 服务提供一套完整的认证流程而搭建的项目。
* 项目包含模块化分包，推荐使用[IntelliJ IDEA](https://www.jetbrains.com/idea)打开。
* 所有服务启动完成后，打开[链接](http://127.0.0.1:18100/doc.html)或`http://127.0.0.1:18100/doc.html`访问网关swagger地址，提示框账号密码输入`admin/admin`。

### 工程结构
``` 
icloud-simple-service
├── doc -- 文档
├── icloud-api-gateway -- Spring Cloud 网关
├── icloud-auth -- 认证授权服务
├── icloud-boot -- 微服务基础支撑
├    ├── icloud-client -- feign客户端
├    ├── icloud-common -- 工具类封装
├    ├── icloud-core-launch -- 启动类封装
├── icloud-services -- 业务模块
├    └── icloud-user -- 用户服务 
├── icloud-service-api -- 业务模块api
└──  └── icloud-user-api -- 用户api 
```
### 开源协议
Apache Licence 2.0 （[英文原文](http://www.apache.org/licenses/LICENSE-2.0.html)）
Apache Licence是著名的非盈利开源组织Apache采用的协议。该协议和BSD类似，同样鼓励代码共享和尊重原作者的著作权，同样允许代码修改，再发布（作为开源或商业软件）。
需要满足的条件如下：
* 需要给代码的用户一份Apache Licence
* 如果你修改了代码，需要在被修改的文件中说明。
* 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。
* 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售。

### 用户权益
* 允许免费用于学习、毕设、公司项目、私活等。
* 对未经过授权和不遵循 Apache 2.0 协议二次开源或者商业化作者将追究到底。
* 若禁止条款被发现有权追讨 **5000** 的授权费。