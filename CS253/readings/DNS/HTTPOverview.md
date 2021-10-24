### 层次

![http-layers](C:\Users\A\Desktop\Githubs\oc-Rcd\CS253\readings\DNS\http-layers.png)

应用层协议，通过 TCP 发送，or TLS 加密的 TCP 连接发送，理论上可以使用任何可靠的传输协议



## 基于 HTTP 系统的组件



## Basic aspects of HTTP

Simple

Scalable

HTTP Flow

stateless, not sessionless

HTTP 是无状态的：在同一连接上连续执行的两个请求之间没有联系。对于试图与某些页面连贯交互（例如使用电子商务购物篮）的用户来说，这立即有可能成为问题。

但虽然 HTTP 本身的核心是无状态的，但 HTTP Cookie 允许使用状态会话。使用标题扩展性，HTTP Cookies 添加到工作流中，允许每个 HTTP 请求上的会话创建共享相同的上下文或相同的状态。

connections

连接在运输层得到控制，因此根本脱离了 HTTP 的范围。HTTP 不要求基础运输协议基于连接；它只要求它是*可靠的*，或不丢失消息（至少，在这种情况下，显示错误）。在互联网上最常见的两种运输协议中，TCP 是可靠的，UDP 不是。因此，HTTP 依赖于基于连接的 TCP 标准。

在客户端和服务器可以交换 HTTP 请求/响应对之前，他们必须建立 TCP 连接，这个过程需要多次往返。HTTP/1.0 的默认行为是为每个 HTTP 请求/响应对打开单独的 TCP 连接。这比在连续发送多个请求时共享单个 TCP 连接效率要低。

为了减轻这一缺陷，HTTP/1.1 引入*了管道*（证明难以实现）和*持久连接*：基础 TCP 连接可以使用[`连接`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Connection)头进行部分控制。HTTP/2 通过在单个连接上对消息进行多路复用，帮助保持连接温暖和高效，从而更进一步。

目前正在进行实验，以设计更适合 HTTP 的更好的运输协议。例如，谷歌正在尝试[奎克](https://en.wikipedia.org/wiki/QUIC)以 UDP 为基础，提供更可靠、更高效的运输协议。



## 什么可以被 HTTP 控制

以下是可与 HTTP 控制的共同功能列表：

- *[缓存](https://developer.mozilla.org/en-US/docs/Web/HTTP/Caching)*文件缓存方式可由 HTTP 控制。服务器可以指示代理和客户端缓存什么以及缓存时间。客户端可以指示中间缓存代理忽略存储的文档。
- *放松原产地约束*为了防止窥探和其他隐私侵犯，Web 浏览器强制实施网站之间的严格隔离。只有来自**同一来源**的页面才能访问网页的所有信息。虽然这种约束是服务器的负担，但 HTTP 端可以放松服务器端的严格分离，使文档成为来自不同域的信息的拼凑：甚至可能有与安全相关的理由这样做。
- *身份验证*某些页面可能受到保护，以便只有特定用户才能访问它们。基本身份验证可由 HTTP 提供，或者使用[`WWW 认证`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/WWW-Authenticate)和类似头条，或者使用[HTTP Cookie](https://developer.mozilla.org/en-US/docs/Web/HTTP/Cookies)设置特定会话。
- *[代理和隧道](https://developer.mozilla.org/en-US/docs/Web/HTTP/Proxy_servers_and_tunneling)*服务器或客户端通常位于内联网上，并将其真实 IP 地址隐藏在其他计算机上。然后，HTTP 请求通过代理来跨越此网络障碍。并非所有代理都是 HTTP 代理。例如，袜子协议在较低级别运行。其他协议（如 ftp）可由这些代理处理。
- *会话*使用 HTTP Cookie 可将请求与服务器状态链接。尽管基本 HTTP 是无状态协议，但这会创建会话。这不仅适用于电子商务购物篮，也可用于任何允许用户配置输出的网站。



## HTTP Flow

当客户端想要与服务器（包括最终服务器或中间代理）通信时，它会执行以下步骤：           

1. 打开 TCP 连接：TCP 连接用于发送请求或多个请求并接收答案。客户端可以打开新连接、重复使用现有连接或打开多个 TCP 连接到服务器。

2. 发送 HTTP 消息：HTTP 消息（在 HTTP/2 之前）是可人读的。使用 HTTP/2，这些简单的消息被封装在帧中，因此无法直接读取，但原则保持不变。例如：

   ```shell
   GET / HTTP/1.1
   Host: developer.mozilla.org
   Accept-Language: fr
   ```

3. 阅读服务器发送的响应

   ```shell
   HTTP/1.1 200 OK
   Date: Sat, 09 Oct 2010 14:28:02 GMT
   Server: Apache
   Last-Modified: Tue, 01 Dec 2009 20:18:22 GMT
   ETag: "51142bc1-7449-479b075b2891b"
   Accept-Ranges: bytes
   Content-Length: 29769
   Content-Type: text/html
   ```

   