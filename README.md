https://github.com/sirairpower/interview

# Subject
原則
1.可用 memory 做資料暫存。
* 2. java 8 以上語法為主。
* 3.題目如看不懂可以交卷時自行說明調整 補充的部分。
* 4.請考慮 Thread safe 的問題
* 5. api 請依照 Restful 設計
1.請參考 https://spring.io/quickstart 建立一個 hello world.
2.請設計新增 刪除 修改 查詢 單一以及分頁 會員功能 api 。
3.請設計一個訂單訂購 api ，會員可以訂購產品。
4.請設計一個訂單查詢 api ，會員可以根據訂單編號做 分頁查詢。


# Interview Job
java version : 11
spring boot version : 2.6
Database : H2 in-memory DB
Swagger : 2.9
Test engine :  junit 5
預設 User / Password : howard / fjdksl

用maven打包後，命令列執行java -jar {your_path}/interview/target/interview-0.0.1-SNAPSHOT.jar

Console 會印出連結，(預設帳密howard / fjdksl)成功登入後會導頁至 swagger-ui portal page，

既可以進行測試。

# Howard 目前接案任務(~2022)
## FashionGuide 維護

目前極少時間在維護Fashionguide 前後台功能，平台一年多無新功能需求，已趨於穩定。

主機架在AWS，有使用EC2 ,RDS , SQS , SMS ...等服務。

 網站部落格使用PHP，FG多個domain , 用Nginx在前置做HA及反向代理。

https://www.fashionguide.com.tw/

## 品效數據 Wordpress 電商平台維護。

主機架在cloudways，專門for php的saas平台。

用spring boot 寫一些維護上下架ＲＰＡ 程式，用java selenium。

https://heroin.asia

敝人不常整理作品，如有需要，可以再行討論製作，謝謝。
