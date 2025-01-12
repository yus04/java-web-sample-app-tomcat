package com.example.config;  
  
import java.util.Properties;  
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.Statement;  
  
public class DatabaseConfig {  
  
    // シングルトンインスタンスを保持する静的変数  
    private static DatabaseConfig instance;  
  
    private String jdbcUrl;  
    private String jdbcDriver;  
    private String dbUser;  
    private String dbPass;  
  
    // コンストラクタを private にして外部からのインスタンス生成を防止  
    private DatabaseConfig() {  
        System.out.println("Database configuring...");  
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {  
  
            Properties prop = new Properties();  
            prop.load(input);  
  
            String dbType = prop.getProperty("db.type");  
  
            if ("sqlite".equalsIgnoreCase(dbType)) {  
                jdbcUrl = prop.getProperty("sqlite.jdbc.url");  
                jdbcDriver = prop.getProperty("sqlite.jdbc.driver");  
                dbUser = prop.getProperty("sqlite.db.user");  
                dbPass = prop.getProperty("sqlite.db.pass");  
  
                // SQLite 用のデータベース初期化メソッドを呼び出す  
                initializeDatabase();  
  
            } else if ("azure".equalsIgnoreCase(dbType)) {  
                jdbcUrl = prop.getProperty("azure.jdbc.url");  
                jdbcDriver = prop.getProperty("azure.jdbc.driver");  
                dbUser = prop.getProperty("azure.db.user");  
                dbPass = prop.getProperty("azure.db.pass");  
            } else {  
                throw new RuntimeException("Unsupported db.type: " + dbType);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    // シングルトンインスタンスを取得するためのメソッド  
    public static synchronized DatabaseConfig getInstance() {  
        if (instance == null) {  
            instance = new DatabaseConfig();  
        }  
        return instance;  
    }  
  
    // SQLite 用のデータベース初期化メソッド  
    private void initializeDatabase() {  
        System.out.println("Initializing database...");  
  
        String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers ("  
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"  
                + "name TEXT NOT NULL,"  
                + "email TEXT,"  
                + "phone TEXT,"  
                + "address TEXT"  
                + ");";  
  
        String createHearingsTable = "CREATE TABLE IF NOT EXISTS hearings ("  
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"  
                + "customer_id INTEGER NOT NULL,"  
                + "content TEXT NOT NULL,"  
                + "date TEXT NOT NULL,"  
                + "FOREIGN KEY (customer_id) REFERENCES customers(id)"  
                + ");";  
  
        try {  
            // JDBC ドライバのロード  
            Class.forName(jdbcDriver);  
  
            // ユーザー名とパスワードを指定せずに接続（SQLite は不要）  
            try (Connection conn = DriverManager.getConnection(jdbcUrl);  
                    Statement stmt = conn.createStatement()) {  
  
                // テーブル作成の SQL を実行  
                stmt.executeUpdate(createCustomersTable);  
                stmt.executeUpdate(createHearingsTable);  
  
                System.out.println("データベースの初期化が完了しました。");  
  
            } catch (Exception e) {  
                System.err.println("データベースの初期化に失敗しました。");  
                e.printStackTrace();  
            }  
        } catch (ClassNotFoundException e) {  
            System.err.println("JDBC ドライバが見つかりません。");  
            e.printStackTrace();  
        }  
    }  
  
    // ゲッターメソッド  
    public String getJdbcUrl() {  
        return jdbcUrl;  
    }  
  
    public String getJdbcDriver() {  
        return jdbcDriver;  
    }  
  
    public String getDbUser() {  
        return dbUser;  
    }  
  
    public String getDbPass() {  
        return dbPass;  
    }  
}
