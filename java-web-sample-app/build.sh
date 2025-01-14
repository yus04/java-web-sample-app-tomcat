#!/bin/bash  
  
# 環境変数の設定  
export JAVA_HOME=$HOME/java-web-sample-app-tomcat/jdk1.8.0_421  
export CATALINA_HOME=$HOME/java-web-sample-app-tomcat/apache-tomcat-9.0.98   
  
# ディレクトリとクラスパスを設定  
export BUILD_DIR="java-web-sample-app/build"  
export WEB_INF_DIR="$BUILD_DIR/WEB-INF"  
export SRC_DIR="java-web-sample-app/src"  
export EXAMPLE_SRC_DIR="$SRC_DIR/com/example"  
export CLASSPATH="$WEB_INF_DIR/lib/*:apache-tomcat-9.0.98/lib/servlet-api.jar"  
  
# Java コードのコンパイル
jdk1.8.0_421/bin/javac -d $WEB_INF_DIR/classes -classpath $CLASSPATH $EXAMPLE_SRC_DIR/config/DatabaseConfig.java $EXAMPLE_SRC_DIR/servlet/*.java $EXAMPLE_SRC_DIR/model/*.java  

# WebContent ディレクトリの内容を build ディレクトリにコピー  
cp -r java-web-sample-app/WebContent/* java-web-sample-app/build/  
  
# リソースファイルのコピー  
cp java-web-sample-app/src/resources/database.properties java-web-sample-app/build/WEB-INF/classes/  
  
# WAR ファイルの作成  
$JAVA_HOME/bin/jar cvf java-web-sample-app/java-web-sample-app.war -C java-web-sample-app/build .  
  
# WAR ファイルを Tomcat の webapps ディレクトリにコピー  
cp java-web-sample-app/java-web-sample-app.war $CATALINA_HOME/webapps/  
