# java-web-sample-app-tomcat
本リポジトリは、Tomcat をアプリケーションサーバーとして利用して動かす Java Web アプリケーションのサンプルコードです。

## 事前準備
本リポジトリを利用するために、以下の事前準備を行ってください。
- Oracle アカウントのセットアップ (手順省略)
- Java のインストールとパッケージの展開 (以下手順)
- Tomcat のインストールとパッケージの展開 (以下手順)

### Java のインストール
以下の Java SE 8 Archive Downloads のリンクから、「Java SE Development Kit 8u421」の「Linux x64 Compressed Archive」で `jdk-8u421-linux-x64.tar.gz` をダウンロードしてください。(downloaded 配下にあります。)
https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html

### Java パッケージの展開
ダウンロードしたパッケージに対して、次のコマンドを実行して展開して下さい。
展開すると、`jdk1.8.0_421` のディレクトリが作成されます。
```
tar -xzf jdk-8u421-linux-x64.tar.gz
```

### Java 実行ファイルの実行
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

### Tomcat のインストール
以下の Apache Tomcat のリンクから、9.0.98 > Binary Distributions > Core > tar.gz 形式のパッケージをインストールして下さい。(downloaded 配下にあります。)
https://tomcat.apache.org/download-90.cgi

### Tomcat パッケージの展開
ダウンロードしたパッケージに対して、次のコマンドを実行して展開して下さい。
展開すると、`apache-tomcat-9.0.98` のディレクトリが作成されます。
```
tar -xzf apache-tomcat-9.0.98.tar.gz
```

### Tomcat 実行の準備
Tomcat の実行のために、以下のコマンドを実行して `JAVA_HOME` と `CATALINA_HOME` を設定します。
```
export JAVA_HOME=<path to jkd1.8.0_421>
```

以下は `JAVA_HOME` の例になります。
```
export JAVA_HOME=/home/yu/java-web-sample-app-tomcat/jdk1.8.0_421
```

以下は `CATALINA_HOME` の例になります。
```
export CATALINA_HOME=/home/yu/java-web-sample-app-tomcat/apache-tomcat-9.0.98
```

### Tomcat の実行
以下のコマンドを実行し、Tomcat を実行してください。`Tomcat started.` とターミナルに出力されていれば正しく実行されています。
```
apache-tomcat-9.0.98/bin/startup.sh
```

(任意) Tomcat を停止したい場合は、以下のコマンドを実行してください。
```
apache-tomcat-9.0.98/bin/shutdown.sh
```

(任意) Tomcat をフォアグラウンドで実行してログを出力したい場合
```
apache-tomcat-9.0.98/bin/catalina.sh run
```

Tomcat を実行した後、ブラウザで以下の URL にアクセスすることで Tomcat の初期画面が表示されます。
```
http://localhost:8080/
```

## Java Web アプリのビルド

```
cd <path to java-web-sample-app-tomcat>
```

```
jdk1.8.0_421/bin/javac -d java-web-sample-app/build/WEB-INF/classes -classpath "java-web-sample-app/WebContent/WEB-INF/lib/*:apache-tomcat-9.0.98/lib/servlet-api.jar" java-web-sample-app/src/com/example/config/DatabaseConfig.java java-web-sample-app/src/com/example/servlet/*.java java-web-sample-app/src/com/example/model/*.java
```
上記 のコマンドを実行した build 済みのファイルは build/WEB-INF/classes/com/example/servlet 配下に保存済みです。

### コマンドの解説
- -d オプションで出力先ディレクトリを指定
- -classpath オプションでコンパイル時のクラスパスを指定

## リソースファイルのコピー

```
cp -r java-web-sample-app/WebContent/* java-web-sample-app/build/
```

## データベース情報のコピー
```
cp java-web-sample-app/src/resources/database.properties java-web-sample-app/build/WEB-INF/classes/
```

## war ファイルの作成

```
jdk1.8.0_421/bin/jar cvf java-web-sample-app/java-web-sample-app.war -C java-web-sample-app/build .  
```

### コマンドの解説
- jar コマンドは、基本的に ZIP アーカイブを作成するツール
- WAR ファイルは ZIP 形式であるため、jar コマンドで作成可能
- c オプションはアーカイブの作成、v オプションは詳細な情報の表示、f オプションは出力ファイル名の指定を意味
- -C java-web-sample-app/build . の部分で、jar コマンドに対してディレクトリを変更し、その場所からファイルをアーカイブするよう指示

## war ファイルの配置
```
cp java-web-sample-app/java-web-sample-app.war apache-tomcat-9.0.98/webapps/
```

## Tomcat の起動
略

## アプリケーションへのアクセス
```

```

## その他
Tomcat web アプリケーションマネージャの利用
起動しているアプリケーションを把握することが可能
Tomcat を起動した後、ブラウザで以下の URL にアクセス
```
http://localhost:8080/manager/html
```


## SQLite JDBC ドライバをダウンロード
以下の MVN Repository から [SQLite JDBC » 3.47.2.0 の jar ファイル](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.47.2.0/sqlite-jdbc-3.47.2.0.jar) をダウンロードします。このドライバは SQLite を利用するために必要です。
https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.47.2.0

### ダウンロードした jar ファイルの配置
```
cp sqlite-jdbc-3.47.2.0.jar java-web-sample-app/WebContent/WEB-INF/lib/
```

## Microsoft JDBC ドライバをダウンロード
以下の Microsoft 公式サイトから [Microsoft JDBC Driver 12.8 for SQL Server (tar.gz) のダウンロード](https://go.microsoft.com/fwlink/?linkid=2283563) をダウンロードします。このドライバは Azure SQL Database を利用するために必要です。
https://learn.microsoft.com/ja-jp/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16

### ファイルの展開
以下のコマンドを実行して、ダウンロードした JDBC ドライバを展開します。sqljdbc_12.8 ディレクトリに展開済みのディレクトリがあります。
```
tar -zxvf sqljdbc_12.8.1.0_jpn.tar.gz
```

### 展開後の jar ファイルの配置
Java 8 用の jar ファイルを lib 配下に配置します。
```
cp sqljdbc_12.8/jpn/jars/mssql-jdbc-12.8.1.jre8.jar java-web-sample-app/WebContent/WEB-INF/lib/
```

## データベースの切り替え
このサンプルアプリでは、SQLite と Azure SQL Database を利用できます。
java-web-sample-app/src/resources/database.properties の `db.type` を以下のように変更することで切り替えが可能です。
- SQLite を利用する場合：sqlite
- Azure SQL Database を利用する場合：azure

## その他
- JavaFX の WebView コンポーネントでウェブコンテンツを表示するためのネイティブライブラリである、libjfxwebkit.so は 100 MB を超える巨大ファイルであるが、JSP/サーブレットを使用した Web アプリケーションには直接関係がないため、jdk1.8.0_421/jre/lib/amd64 配下から削除しました。また、downloded/jdk-8u421-linux-x64.tar のファイルも libjfxwebkit.so を削除して軽量化しています。

## Azure SQL Database への接続
### Azure SQL Database の作成
Azure ポータルから、Azure SQL Database を作成します。

java-web-sample-app/src/resources/database.properties 内の Azure SQL Database 用設定を変更

「SQL 認証を使用する」を選択。

また、ネットワークはパブリックネットワークアクセスを許可し、自身のクライアント IPv4 アドレスをファイアーウォール規則に登録してください。

### database.properties ファイルの変更
#### azure.jdbc.url の変更
Azure ポータルで、作成済みの Azure SQL Database の画面を開き、設定 > 接続文字列 > JDBC > JDBC (SQL 認証) から接続文字列をコピーし、`{your_password_here}` を設定済みパスワードに変更して database.properties ファイルを設定

#### azure.jdbc.driver
com.microsoft.sqlserver.jdbc.SQLServerDriver
が登録済みドライバーです。

https://learn.microsoft.com/ja-jp/sql/connect/jdbc/using-the-jdbc-driver?view=sql-server-ver16

#### azure.db.user
以下の形式で設定
azure.db.user=<your-username>@<your-server-name>

<your-username> には、
Azure SQL Database 作成時に入力したサーバー管理者ログインを設定。

<your-server-name> には、
Azure SQL Database 作成時入力したサーバー名を設定。

サーバー名は以下の形式となっていますが、そのうちの <your-server-name> をサーバー名として使用します。
<your-server-name>.database.windows.net

#### azure.db.pass
Azure SQL Database 作成時に入力したパスワードを設定。
