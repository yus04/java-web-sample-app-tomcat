# java-web-sample-app-tomcat
本リポジトリは、Tomcat をアプリケーションサーバーとして利用して動かす Java Web アプリケーションのサンプルコードです。

## 事前準備
本リポジトリを利用するために、以下の事前準備を行ってください。
- Oracle アカウントのセットアップ (手順省略)
- Java のインストールとパッケージの展開 (以下手順)
- Tomcat のインストールとパッケージの展開 (以下手順)

### Java のインストール
以下の Java SE 8 Archive Downloads のリンクから、「Java SE Development Kit 8u421」の「Linux x64 Compressed Archive」で `jdk-8u421-linux-x64.tar.gz` をダウンロードしてください。
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
以下の Apache Tomcat のリンクから、9.0.98 (バージョン) > Binary Distributions > Core > tar.gz 形式のパッケージをインストールして下さい。
https://tomcat.apache.org/download-90.cgi

### Tomcat パッケージの展開
ダウンロードしたパッケージに対して、次のコマンドを実行して展開して下さい。
展開すると、`apache-tomcat-9.0.98` のディレクトリが作成されます。
```
tar -xzf apache-tomcat-9.0.98.tar.gz
```

### Tomcat 実行の準備
Tomcat の実行のために、以下のコマンドを実行して `JAVA_HOME` を設定します。
```
export JAVA_HOME=<path to jkd1.8.0_421>
```

以下は `JAVA_HOME` の例になります。
```
export JAVA_HOME=/home/yu/java-web-sample-app-tomcat/jdk1.8.0_421
```

### Tomcat の実行
以下のコマンドを実行し、Tomcat を実行してください。`Tomcat started.` とターミナルに出力されていれば正しく実行されています。
```
apache-tomcat-9.0.98/bin/startup.sh
```

Tomcat を実行した後、ブラウザで以下の URL にアクセスすることで Tomcat の初期画面が表示されます。
```
http://localhost:8080/
```
