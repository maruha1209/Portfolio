# Name: Tweet Application

twitterを模倣した、短文投稿アプリケーションです。

# DEMO

![](https://gyazo.com/8680b3a306c991ae4a55e2d4bf6238ae.gif)

# Features

* アカウントを登録し、ツイート(短文)を投稿できます。
* アカウントやツイートを検索し、閲覧、フォローできます。
* フォローしたユーザーのツイートが表示されるタイムラインを構築できます。

# Requirement

* Java(Java11)
* DB（MySQL 8.0）
* AP(Tomcat9)
* Eclipse 2020
* javax.servlet-api (4.0.1)
* mysql-connector-java (8.0.23)
* hibernate-core (5.4.28.Final)
* lombok (1.18.16)
* taglibs-standard-impl (1.2.5)
* javax.servlet.jsp.jstl-api (1.2.1)

# Usage

GitHubからプロジェクトをclone（ダウンロード）します。

<pre>$ git clone https://github.com/TmoyukiShirai/p_m.git</pre><br>
Eclipseにインポートします。<br>
Tomcatの構成済みのリソースに追加します。<br>
以下のファイルの接続タブを表示します。<br>
　　/patisserie_manager/src/META-INF/persistence.xml<br>
URL・ユーザー・パスワードをお使いのMySQLの情報に変更し、保存します。

# Author

* Haruma Takesue
