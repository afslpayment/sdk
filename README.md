# sdk
sdk for payment
## 如果使用
 - 1 下载sdk
```
git clone https://github.com/afslpayment/sdk.git
```
 - 2 复制 `build/sdk-1.0-SNAPSHOT.jar` 至你们的项目`lib`并引用jar包
 - 3 实例化 `utile` 调用`signToString(String oid, String amount, String url, String notifyurl)`得到需要post的参数
 - 4 js客户端通过解析之后post 数据至`http://www.jadebirdpay.com/PayManager/Pay/sendabcpost` (html code可以查看build/index.html)