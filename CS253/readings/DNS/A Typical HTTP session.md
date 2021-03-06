# A Typical HTTP session

#### session consists of 3 phases

1. 客户端建立 TCP 连接（或如果传输层不是 TCP 的适当连接）。
2. 客户发送请求，等待答案。
3. 服务器处理请求，发回其答案，提供状态代码和适当的数据。

截至 HTTP/1.1，连接在完成第三阶段后不再关闭，客户现在获得进一步请求：这意味着第二和第三阶段现在可以执行任意次数。

#### 建立连接

在客户端服务器协议中，是客户端建立了连接。在 HTTP 中打开连接意味着在底层传输层中启动连接，通常这是 TCP。

对于计算机上的 HTTP 服务器来说，使用 TCP 的默认端口是端口 80。其他端口也可使用，如 8000 或 8080。要取的页面的 URL 包含域名和端口号，但如果是 80，则可以省略后者。

**注意：**客户端服务器模型不允许服务器在没有明确请求的情况下向客户端发送数据。为了解决这个问题，Web 开发人员使用几种技术：通过[`XMLHTTP`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest) 要求定期 ping 服务器，[`获取 （）` ](https://developer.mozilla.org/en-US/docs/Web/API/fetch)API，使用[网络插座 API](https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API)或类似的协议。



#### 发送客户请求

建立连接后，用户代理可以发送请求（用户代理通常是 Web 浏览器，但可以是任何其他内容，例如爬行器）。

客户请求由文本指令组成，由 CRLF（运输返回，然后是线路馈送）分开，分为三个块：

1. 第一行包含一个请求方法，其参数后面是：
   - 文档的路径，作为没有协议或域名的绝对 URL
   - HTTP 协议版本
2. 后续行表示 HTTP 头，为服务器提供有关何种类型的数据是合适的（例如，什么语言、什么 MIME 类型）或其他更改其行为的数据的信息（例如，如果已缓存，则不发送答案）。这些 HTTP 头组成一个以空线结尾的块。
3. 最后一个块是可选的数据块，其中可能包含主要由 POST 方法使用的进一步数据。



##### 示例请求

##### 请求方法

服务器响应的结构