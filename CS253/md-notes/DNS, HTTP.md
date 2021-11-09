# DNS, HTTP

### Domain Name System

### DNS Recursive Resolver

1. Client asks DNS Recursive Resolver to lookup a hostname (stanford.edu). 
2. DNS Recursive Resolver sends DNS query to Root Nameserver
   - Root Nameserver responds with IP address of TLD Nameserver (".edu" Nameserver)
3. DNS Recursive Resolver sends DNS query to TLD Nameserver
   - TLD Nameserver responds with IP address of Domain Nameserver ("stanford.edu" Nameserver)
4. DNS Recursive Resolver sends DNS query to Domain Nameserver 
   - Domain Nameserver is authoritative, so replies with server IP address.
5. DNS Recursive Resolver finally responds to Client, sending server IP address (171.67.215.200)

### DNS hijacking

Attacker changes target DNS record to point to attacker IP address. Causes all site visitors to be directed to attacker's web server

### DNS hijacking vectors

- Hijacked recursive DNS resolver (shown previously)

- Hijacked DNS nameserver

- Compromised user account at DNS provider

- Malware changes user's local DNS settings

- Hijacked router

### DNS Privacy

- Queries are in plaintext 
- ISPs have been known to sell this data

- Pro tip:

  Consider switching your DNS settings to 1.1.1.1 or another provider with a good privacy policy



#### Demo: Make an HTTP request

```shell
curl https://twitter.com
curl https://twitter.com > twitter.html
open twitter.html
```

##### HTTP request

```shell
GET / HTTP/1.1
# Method + Path + Protocol Version
Host: twitter.com
# Header Name + Header Value
User-Agent: Mozilla/5.0 ...
```

##### HTTP response

```shell
HTTP/1.1 200 OK
# Protocol Version + Status Code + Status Message
Content-Length: 9001
Content-Type: text/html; charset=UTF-8
Date: Tue, 24 Sep 2019 20:30:00 GMT
<!DOCTYPEhtml ...
```



### HTTP

- Client-server model - Client asks server for resource, server replies
- Simple - Human-readable text protocol
- Extensible - Just add HTTP headers
- Transport protocol agnostic - Only requirement is reliability
- Stateless - Two requests have no relation to each other

##### HTTP is stateless?

- Obviously, we interact with "stateful" servers all the time 
- "Stateless" means the HTTP protocol itself does not store state
- If state is desired, is implemented as a layer on top of HTTP

#### HTTP Status Codes

| 1xx  | Informational ("Hold on")      |
| ---- | ------------------------------ |
| 2xx  | Success ("Here you go")        |
| 3xx  | Redirection ("Go away")        |
| 4xx  | Client error ("You messed up") |
| 5xx  | Server error ("I messed up")   |

##### HTTP Success Codes

• 200 OK - Request succeeded

• 206 Partial Content - Request for specific byte range succeeded

###### Range Request

```shell
GET /video.mp4 HTTP/1.1
Range: bytes=1000-1499
```

###### Response

```shell
HTTP/1.1 206 Partial Content
Content-Range: bytes 1000-1499/1000000
```

##### HTTP Redirection Codes

**• 301** Moved Permanently - Resource has a new permanent URL 

**• 302** Found - Resource temporarily resides at a different URL 

**• 304** Not Modified - Resource has not been modified since last  cached

##### HTTP Client Error Codes

**• 400** Bad Request - Malformed request

**• 401** Unauthorized - Resource is protected, need to authorize

**• 403** Forbidden - Resource is protected, denying access

**• 404** Not Found - Ya'll know this one

##### HTTP Server Error Codes

• 500 Internal Server Error - Generic server error

• 502 Bad Gateway - Server is a proxy; backend server is unreachable

• 503 Service Unavailable - Server is overloaded or down for  maintenance

• 504 Gateway Timeout - Server is a proxy; backend server  responded too slowly

##### HTTP with a proxy server

• Can cache content

• Can block content (e.g. malware, adult content)

• Can modify content

• Can sit in front of many servers ("reverse proxy")

##### HTTP headers

• Let the client and the server pass additional information with an  HTTP request or response

• Essentially a map of key-value pairs

• Allow experimental extensions to HTTP without requiring protocol  changes

##### Useful HTTP request headers

• Host - The domain name of the server (e.g. example.com)

• User-Agent - The name of your browser and operating system

• Referer - The webpage which led you to this page (misspelled)

• Cookie - The cookie server gave you earlier; keeps you logged in

• Range - Specifies a subset of bytes to fetch

##### Useful HTTP request headers (pt 2)

• Cache-Control - Specifies if you want a cached response or not 

• If-Modified-Since - Only send resource if it changed recently 

• Connection - Control TCP socket (e.g. keep-alive or close) 

• Accept - Which type of content we want (e.g. text/html) 

• Accept-Encoding - Encoding algorithms we understand (e.g. gzip)

• Accept-Language - What language we want (e.g. es)

##### Demo: Make an HTTP request with  headers

```shell
curl https://twitter.com --header "Accept-Language: es" --silent | grep JavaScript

curl https://twitter.com --header "Accept-Language: ar" --silent | grep JavaScript
```

##### Demo: User-Agent Examples

##### Useful HTTP response headers

• Date - When response was sent

• Last-Modified - When content was last modified

• Cache-Control - Specifies whether to cache response or not

• Expires - Discard response from cache after this date

• Set-Cookie - Set a cookie on the client

• Vary - List of headers which affect response; used by cache

##### Useful HTTP response headers (pt 2)

• Location - URL to redirect the client to (used with 3xx responses)

• Connection - Control TCP socket (e.g. keep-alive or close)

• Content-Type - Type of content in response (e.g. text/html)

• Content-Encoding - Encoding of the response (e.g. gzip)

• Content-Language - Language of the response (e.g. ar)

• Content-Length - Length of the response in bytes

##### Demo: Implement an HTTP client

• Not magic!

• Steps:

• Open a TCP socket

• Send HTTP request text over the socket

• Read the HTTP response text from the socket
