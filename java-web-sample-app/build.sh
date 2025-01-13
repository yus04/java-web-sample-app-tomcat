#!/bin/bash  
  
# 環境変数の設定  
export JAVA_HOME=$HOME/java-web-sample-app-tomcat/jdk1.8.0_421  
export CATALINA_HOME=$HOME/java-web-sample-app-tomcat/apache-tomcat-9.0.98   
  
# Java ソースコードのコンパイル  
$JAVA_HOME/bin/javac -d java-web-sample-app/build/WEB-INF/classes -classpath "java-web-sample-app/WebContent/WEB-INF/lib/*:$CATALINA_HOME/lib/servlet-api.jar" java-web-sample-app/src/com/example/*

# WebContent ディレクトリの内容を build ディレクトリにコピー  
cp -r java-web-sample-app/WebContent/* java-web-sample-app/build/  
  
# リソースファイルのコピー  
cp java-web-sample-app/src/resources/database.properties java-web-sample-app/build/WEB-INF/classes/  
  
# WAR ファイルの作成  
$JAVA_HOME/bin/jar cvf java-web-sample-app/java-web-sample-app.war -C java-web-sample-app/build .  
  
# WAR ファイルを Tomcat の webapps ディレクトリにコピー  
cp java-web-sample-app/java-web-sample-app.war $CATALINA_HOME/webapps/  
