# サンプルアプリの事前準備を行う
本サンプルアプリでは、Apache Tomcat、Java SE Development Kit、Microsoft JDBC ドライバー、SQL JDBC ドライバー、を利用するため、そのための事前準備を行う必要があります。手順では以下のバージョンに対応したモジュールをインストールしていますが、適宜必要なバージョンに変えて準備を行ってください。
|モジュール|バージョン|
|---|---|
|Apache Tomcat|9.0.98|
|JDK|8u421 (linux-x64)|
|Microsoft JDBC|12.8|
|SQL JDBC|3.47.2.0|

## Apache Tomcat
### Apache Tomcat のインストール
以下の Apache Tomcat のリンクから、9.0.98 > Binary Distributions > Core > tar.gz 形式のパッケージをインストールして下さい。[参考ドキュメント](https://tomcat.apache.org/download-90.cgi)

### Apache Tomcat パッケージの展開
ダウンロードしたパッケージに対して、次のコマンドを実行して展開して下さい。
展開すると、`apache-tomcat-9.0.98` のディレクトリが作成されます。
```
tar -xzf apache-tomcat-9.0.98.tar.gz
```

### 展開したパッケージの配置
展開したパッケージを利用できるように、適当な場所に配置してください。本リポジトリでは、java-web-sample-app と同じ階層に apache-tomcat-9.0.98 ディレクトリを配置しています。

## JDK
### JDK のインストール
以下の Java SE 8 Archive Downloads のリンクから、「Java SE Development Kit 8u421」の「Linux x64 Compressed Archive」で `jdk-8u421-linux-x64.tar.gz` をダウンロードしてください。ダウンロードをするには Oracle アカウントのセットアップが必要になるので、アカウントをお持ちでない場合は登録を行ってください。[参考ドキュメント](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html)

### JDK パッケージの展開
ダウンロードしたパッケージに対して、次のコマンドを実行して展開して下さい。
展開すると、`jdk1.8.0_421` のディレクトリが作成されます。
```
tar -xzf jdk-8u421-linux-x64.tar.gz
```
### JDK の存在確認
Java の実行ファイルは、`jdk1.8.0_421/bin/java` にあります。
以下のコマンドにより Java バージョンを確認できます。
```
jdk1.8.0_421/bin/java -version
```

以下のバージョンが返ってくれば、正しく実行ファイルが存在していることが確認できます。以下の結果から、Java 8 のバージョンであることがわかります。
```
java version "1.8.0_421"
Java(TM) SE Runtime Environment (build 1.8.0_421-b09)
Java HotSpot(TM) 64-Bit Server VM (build 25.421-b09, mixed mode)
```

### 展開したパッケージの配置
展開したパッケージを利用できるように、適当な場所に配置してください。本リポジトリでは、java-web-sample-app と同じ階層に jdk1.8.0_421 ディレクトリを配置しています。

## Microsoft JDBC
### Microsoft JDBC ドライバをダウンロード
以下の Microsoft 公式サイトから [Microsoft JDBC Driver 12.8 for SQL Server (tar.gz) のダウンロード](https://go.microsoft.com/fwlink/?linkid=2283563) をダウンロードします。このドライバは Azure SQL Database を利用するために必要です。[参考ドキュメント](https://learn.microsoft.com/ja-jp/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16)

### ファイルの展開
以下のコマンドを実行して、ダウンロードした JDBC ドライバを展開します。sqljdbc_12.8 ディレクトリに展開済みのディレクトリがあります。
```
tar -zxvf sqljdbc_12.8.1.0_jpn.tar.gz
```

### 展開後の jar ファイルの配置
Java 8 用の jar ファイルを lib 配下に配置します。lib 配下に配置することで、Java アプリがドライバーを利用できるようになります。
```
cp sqljdbc_12.8/jpn/jars/mssql-jdbc-12.8.1.jre8.jar java-web-sample-app/WebContent/WEB-INF/lib/
```


## SQLite JDBC
### SQLite JDBC ドライバをダウンロード
以下の MVN Repository から [SQLite JDBC » 3.47.2.0 の jar ファイル](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.47.2.0/sqlite-jdbc-3.47.2.0.jar) をダウンロードします。このドライバは SQLite を利用するために必要です。[参考ドキュメント](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.47.2.0)

### ダウンロードした jar ファイルの配置
ダウンロードした jar ファイルを lib 配下に配置します。lib 配下に配置することで、Java アプリがドライバーを利用できるようになります。
```
cp sqlite-jdbc-3.47.2.0.jar java-web-sample-app/WebContent/WEB-INF/lib/
```

## build.sh の編集
本ドキュメントと異なるバージョンのモジュールをインストールした場合、java-web-sample-app/build.sh のシェルスクリプトが実行できるようにバージョンを変更して下さい。。
