# Api Documentation


<a name="overview"></a>
## 概览
Api Documentation


### 版本信息
*版本* : 1.0


### 许可信息
*许可证* : Apache 2.0  
*许可网址* : http://www.apache.org/licenses/LICENSE-2.0  
*服务条款* : urn:tos


### URI scheme
*域名* : localhost:8091  
*基础路径* : /


### 标签

* basic-error-controller : Basic Error Controller
* login-controller : Login Controller
* refactoring-perform-controller : Refactoring Perform Controller




<a name="paths"></a>
## 资源

<a name="basic-error-controller_resource"></a>
### Basic-error-controller
Basic Error Controller


<a name="errorhtmlusingpost"></a>
#### errorHtml
```
POST /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingget"></a>
#### errorHtml
```
GET /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingput"></a>
#### errorHtml
```
PUT /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingdelete"></a>
#### errorHtml
```
DELETE /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingpatch"></a>
#### errorHtml
```
PATCH /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusinghead"></a>
#### errorHtml
```
HEAD /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingoptions"></a>
#### errorHtml
```
OPTIONS /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="login-controller_resource"></a>
### Login-controller
Login Controller


<a name="loginusingpost"></a>
#### login
```
POST /login
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**creationTime**  <br>*可选*||integer (int64)|
|**Query**|**id**  <br>*可选*||string|
|**Query**|**lastAccessedTime**  <br>*可选*||integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*可选*||integer (int32)|
|**Query**|**new**  <br>*可选*||boolean|
|**Query**|**password**  <br>*必填*|password|string|
|**Query**|**servletContext.classLoader**  <br>*可选*||ref|
|**Query**|**servletContext.contextPath**  <br>*可选*||string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*可选*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*可选*||integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*可选*||integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*可选*||< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*可选*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*可选*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*可选*||< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*可选*||string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*可选*||string|
|**Query**|**servletContext.majorVersion**  <br>*可选*||integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*可选*||integer (int32)|
|**Query**|**servletContext.requestCharacterEncoding**  <br>*可选*||string|
|**Query**|**servletContext.responseCharacterEncoding**  <br>*可选*||string|
|**Query**|**servletContext.serverInfo**  <br>*可选*||string|
|**Query**|**servletContext.servletContextName**  <br>*可选*||string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*可选*||string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*可选*||string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*可选*||boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*可选*||integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*可选*||string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*可选*||string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*可选*||boolean|
|**Query**|**servletContext.sessionTimeout**  <br>*可选*||integer (int32)|
|**Query**|**servletContext.virtualServerName**  <br>*可选*||string|
|**Query**|**username**  <br>*必填*|username|string|
|**Query**|**valueNames**  <br>*可选*||< string > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< string, string > map|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/login
```


###### 请求 query
```
json :
{
  "creationTime" : 0,
  "id" : "string",
  "lastAccessedTime" : 0,
  "maxInactiveInterval" : 0,
  "new" : true,
  "password" : "string",
  "servletContext.classLoader" : "ref",
  "servletContext.contextPath" : "string",
  "servletContext.defaultSessionTrackingModes" : "string",
  "servletContext.effectiveMajorVersion" : 0,
  "servletContext.effectiveMinorVersion" : 0,
  "servletContext.effectiveSessionTrackingModes" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns" : "string",
  "servletContext.jspConfigDescriptor.taglibs[0].taglibLocation" : "string",
  "servletContext.jspConfigDescriptor.taglibs[0].taglibURI" : "string",
  "servletContext.majorVersion" : 0,
  "servletContext.minorVersion" : 0,
  "servletContext.requestCharacterEncoding" : "string",
  "servletContext.responseCharacterEncoding" : "string",
  "servletContext.serverInfo" : "string",
  "servletContext.servletContextName" : "string",
  "servletContext.sessionCookieConfig.comment" : "string",
  "servletContext.sessionCookieConfig.domain" : "string",
  "servletContext.sessionCookieConfig.httpOnly" : true,
  "servletContext.sessionCookieConfig.maxAge" : 0,
  "servletContext.sessionCookieConfig.name" : "string",
  "servletContext.sessionCookieConfig.path" : "string",
  "servletContext.sessionCookieConfig.secure" : true,
  "servletContext.sessionTimeout" : 0,
  "servletContext.virtualServerName" : "string",
  "username" : "string",
  "valueNames" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="registerusingpost"></a>
#### register
```
POST /register
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**company**  <br>*必填*|company|string|
|**Query**|**email**  <br>*必填*|email|string|
|**Query**|**password**  <br>*必填*|password|string|
|**Query**|**username**  <br>*必填*|username|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< string, string > map|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/register
```


###### 请求 query
```
json :
{
  "company" : "string",
  "email" : "string",
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="springsessionusingpost"></a>
#### springSession
```
POST /session
```


##### 参数

|类型|名称|类型|
|---|---|---|
|**Query**|**creationTime**  <br>*可选*|integer (int64)|
|**Query**|**id**  <br>*可选*|string|
|**Query**|**lastAccessedTime**  <br>*可选*|integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*可选*|integer (int32)|
|**Query**|**new**  <br>*可选*|boolean|
|**Query**|**servletContext.classLoader**  <br>*可选*|ref|
|**Query**|**servletContext.contextPath**  <br>*可选*|string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*可选*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*可选*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*可选*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*可选*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*可选*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*可选*|string|
|**Query**|**servletContext.majorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.requestCharacterEncoding**  <br>*可选*|string|
|**Query**|**servletContext.responseCharacterEncoding**  <br>*可选*|string|
|**Query**|**servletContext.serverInfo**  <br>*可选*|string|
|**Query**|**servletContext.servletContextName**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*可选*|boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*可选*|boolean|
|**Query**|**servletContext.sessionTimeout**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.virtualServerName**  <br>*可选*|string|
|**Query**|**valueNames**  <br>*可选*|< string > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< string, string > map|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/session
```


###### 请求 query
```
json :
{
  "creationTime" : 0,
  "id" : "string",
  "lastAccessedTime" : 0,
  "maxInactiveInterval" : 0,
  "new" : true,
  "servletContext.classLoader" : "ref",
  "servletContext.contextPath" : "string",
  "servletContext.defaultSessionTrackingModes" : "string",
  "servletContext.effectiveMajorVersion" : 0,
  "servletContext.effectiveMinorVersion" : 0,
  "servletContext.effectiveSessionTrackingModes" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns" : "string",
  "servletContext.jspConfigDescriptor.taglibs[0].taglibLocation" : "string",
  "servletContext.jspConfigDescriptor.taglibs[0].taglibURI" : "string",
  "servletContext.majorVersion" : 0,
  "servletContext.minorVersion" : 0,
  "servletContext.requestCharacterEncoding" : "string",
  "servletContext.responseCharacterEncoding" : "string",
  "servletContext.serverInfo" : "string",
  "servletContext.servletContextName" : "string",
  "servletContext.sessionCookieConfig.comment" : "string",
  "servletContext.sessionCookieConfig.domain" : "string",
  "servletContext.sessionCookieConfig.httpOnly" : true,
  "servletContext.sessionCookieConfig.maxAge" : 0,
  "servletContext.sessionCookieConfig.name" : "string",
  "servletContext.sessionCookieConfig.path" : "string",
  "servletContext.sessionCookieConfig.secure" : true,
  "servletContext.sessionTimeout" : 0,
  "servletContext.virtualServerName" : "string",
  "valueNames" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="sessionoutusingpost"></a>
#### sessionOut
```
POST /sessionOut
```


##### 参数

|类型|名称|类型|
|---|---|---|
|**Query**|**creationTime**  <br>*可选*|integer (int64)|
|**Query**|**id**  <br>*可选*|string|
|**Query**|**lastAccessedTime**  <br>*可选*|integer (int64)|
|**Query**|**maxInactiveInterval**  <br>*可选*|integer (int32)|
|**Query**|**new**  <br>*可选*|boolean|
|**Query**|**servletContext.classLoader**  <br>*可选*|ref|
|**Query**|**servletContext.contextPath**  <br>*可选*|string|
|**Query**|**servletContext.defaultSessionTrackingModes**  <br>*可选*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.effectiveMajorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.effectiveMinorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.effectiveSessionTrackingModes**  <br>*可选*|< enum (COOKIE, URL, SSL) > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas**  <br>*可选*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes**  <br>*可选*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns**  <br>*可选*|< string > array(multi)|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibLocation**  <br>*可选*|string|
|**Query**|**servletContext.jspConfigDescriptor.taglibs[0].taglibURI**  <br>*可选*|string|
|**Query**|**servletContext.majorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.minorVersion**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.requestCharacterEncoding**  <br>*可选*|string|
|**Query**|**servletContext.responseCharacterEncoding**  <br>*可选*|string|
|**Query**|**servletContext.serverInfo**  <br>*可选*|string|
|**Query**|**servletContext.servletContextName**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.comment**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.domain**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.httpOnly**  <br>*可选*|boolean|
|**Query**|**servletContext.sessionCookieConfig.maxAge**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.sessionCookieConfig.name**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.path**  <br>*可选*|string|
|**Query**|**servletContext.sessionCookieConfig.secure**  <br>*可选*|boolean|
|**Query**|**servletContext.sessionTimeout**  <br>*可选*|integer (int32)|
|**Query**|**servletContext.virtualServerName**  <br>*可选*|string|
|**Query**|**valueNames**  <br>*可选*|< string > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< string, string > map|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/sessionOut
```


###### 请求 query
```
json :
{
  "creationTime" : 0,
  "id" : "string",
  "lastAccessedTime" : 0,
  "maxInactiveInterval" : 0,
  "new" : true,
  "servletContext.classLoader" : "ref",
  "servletContext.contextPath" : "string",
  "servletContext.defaultSessionTrackingModes" : "string",
  "servletContext.effectiveMajorVersion" : 0,
  "servletContext.effectiveMinorVersion" : 0,
  "servletContext.effectiveSessionTrackingModes" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces" : "string",
  "servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns" : "string",
  "servletContext.jspConfigDescriptor.taglibs[0].taglibLocation" : "string",
  "servletContext.jspConfigDescriptor.taglibs[0].taglibURI" : "string",
  "servletContext.majorVersion" : 0,
  "servletContext.minorVersion" : 0,
  "servletContext.requestCharacterEncoding" : "string",
  "servletContext.responseCharacterEncoding" : "string",
  "servletContext.serverInfo" : "string",
  "servletContext.servletContextName" : "string",
  "servletContext.sessionCookieConfig.comment" : "string",
  "servletContext.sessionCookieConfig.domain" : "string",
  "servletContext.sessionCookieConfig.httpOnly" : true,
  "servletContext.sessionCookieConfig.maxAge" : 0,
  "servletContext.sessionCookieConfig.name" : "string",
  "servletContext.sessionCookieConfig.path" : "string",
  "servletContext.sessionCookieConfig.secure" : true,
  "servletContext.sessionTimeout" : 0,
  "servletContext.virtualServerName" : "string",
  "valueNames" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="refactoring-perform-controller_resource"></a>
### Refactoring-perform-controller
Refactoring Perform Controller


<a name="calculatecomplexityusingpost"></a>
#### calculateComplexity
```
POST /calculate-complexity
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**matrix**  <br>*必填*|matrix|< number (double) > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< string, number (double) > map|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/calculate-complexity
```


###### 请求 query
```
json :
{
  "matrix" : 0.0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="uploadfileusingpost"></a>
#### uploadFile
```
POST /fileUpload
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**FormData**|**file**  <br>*必填*|file|file|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[TagMatrixDTO](#tagmatrixdto)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `multipart/form-data`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/fileUpload
```


###### 请求 formData
```
json :
"file"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "matrix" : [ [ 0.0 ] ],
  "name" : "string",
  "tag" : [ "string" ]
}
```


<a name="uploadmatrixusingpost"></a>
#### uploadMatrix
```
POST /refactor
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**matrix**  <br>*必填*|matrix|< number (double) > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< [TagMatrixDTO](#tagmatrixdto) > array|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/refactor
```


###### 请求 query
```
json :
{
  "matrix" : 0.0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
[ {
  "matrix" : [ [ 0.0 ] ],
  "name" : "string",
  "tag" : [ "string" ]
} ]
```




<a name="definitions"></a>
## 定义

<a name="modelandview"></a>
### ModelAndView

|名称|说明|类型|
|---|---|---|
|**empty**  <br>*可选*|**样例** : `true`|boolean|
|**model**  <br>*可选*|**样例** : `"object"`|object|
|**modelMap**  <br>*可选*|**样例** : `{<br>  "string" : "object"<br>}`|< string, object > map|
|**reference**  <br>*可选*|**样例** : `true`|boolean|
|**status**  <br>*可选*|**样例** : `"string"`|enum (100 CONTINUE, 101 SWITCHING_PROTOCOLS, 102 PROCESSING, 103 CHECKPOINT, 200 OK, 201 CREATED, 202 ACCEPTED, 203 NON_AUTHORITATIVE_INFORMATION, 204 NO_CONTENT, 205 RESET_CONTENT, 206 PARTIAL_CONTENT, 207 MULTI_STATUS, 208 ALREADY_REPORTED, 226 IM_USED, 300 MULTIPLE_CHOICES, 301 MOVED_PERMANENTLY, 302 FOUND, 302 MOVED_TEMPORARILY, 303 SEE_OTHER, 304 NOT_MODIFIED, 305 USE_PROXY, 307 TEMPORARY_REDIRECT, 308 PERMANENT_REDIRECT, 400 BAD_REQUEST, 401 UNAUTHORIZED, 402 PAYMENT_REQUIRED, 403 FORBIDDEN, 404 NOT_FOUND, 405 METHOD_NOT_ALLOWED, 406 NOT_ACCEPTABLE, 407 PROXY_AUTHENTICATION_REQUIRED, 408 REQUEST_TIMEOUT, 409 CONFLICT, 410 GONE, 411 LENGTH_REQUIRED, 412 PRECONDITION_FAILED, 413 PAYLOAD_TOO_LARGE, 413 REQUEST_ENTITY_TOO_LARGE, 414 URI_TOO_LONG, 414 REQUEST_URI_TOO_LONG, 415 UNSUPPORTED_MEDIA_TYPE, 416 REQUESTED_RANGE_NOT_SATISFIABLE, 417 EXPECTATION_FAILED, 418 I_AM_A_TEAPOT, 419 INSUFFICIENT_SPACE_ON_RESOURCE, 420 METHOD_FAILURE, 421 DESTINATION_LOCKED, 422 UNPROCESSABLE_ENTITY, 423 LOCKED, 424 FAILED_DEPENDENCY, 425 TOO_EARLY, 426 UPGRADE_REQUIRED, 428 PRECONDITION_REQUIRED, 429 TOO_MANY_REQUESTS, 431 REQUEST_HEADER_FIELDS_TOO_LARGE, 451 UNAVAILABLE_FOR_LEGAL_REASONS, 500 INTERNAL_SERVER_ERROR, 501 NOT_IMPLEMENTED, 502 BAD_GATEWAY, 503 SERVICE_UNAVAILABLE, 504 GATEWAY_TIMEOUT, 505 HTTP_VERSION_NOT_SUPPORTED, 506 VARIANT_ALSO_NEGOTIATES, 507 INSUFFICIENT_STORAGE, 508 LOOP_DETECTED, 509 BANDWIDTH_LIMIT_EXCEEDED, 510 NOT_EXTENDED, 511 NETWORK_AUTHENTICATION_REQUIRED)|
|**view**  <br>*可选*|**样例** : `"[view](#view)"`|[View](#view)|
|**viewName**  <br>*可选*|**样例** : `"string"`|string|


<a name="tagmatrixdto"></a>
### TagMatrixDTO

|名称|说明|类型|
|---|---|---|
|**matrix**  <br>*可选*|**样例** : `[ [ 0.0 ] ]`|< < number (double) > array > array|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**tag**  <br>*可选*|**样例** : `[ "string" ]`|< string > array|


<a name="view"></a>
### View

|名称|说明|类型|
|---|---|---|
|**contentType**  <br>*可选*|**样例** : `"string"`|string|





