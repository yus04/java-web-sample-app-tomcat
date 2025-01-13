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
    private String dbType;
  
    // コンストラクタを private にして外部からのインスタンス生成を防止  
    private DatabaseConfig() {  
        System.out.println("Database configuring...");  
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {  
  
            Properties prop = new Properties();  
            prop.load(input);  
  
            dbType = prop.getProperty("db.type");  
  
            if ("sqlite".equalsIgnoreCase(dbType)) {  
                jdbcUrl = prop.getProperty("sqlite.jdbc.url");  
                jdbcDriver = prop.getProperty("sqlite.jdbc.driver");  
                dbUser = prop.getProperty("sqlite.db.user");  
                dbPass = prop.getProperty("sqlite.db.pass");  
  
                // SQLite 用のデータベース初期化メソッドを呼び出す  
                initializeSQliteDatabase();  
  
            } else if ("azure".equalsIgnoreCase(dbType)) {  
                jdbcUrl = prop.getProperty("azure.jdbc.url");  
                jdbcDriver = prop.getProperty("azure.jdbc.driver");  
                dbUser = prop.getProperty("azure.db.user");  
                dbPass = prop.getProperty("azure.db.pass");  

                // Azure SQL Database 用のデータベース初期化メソッドを呼び出す  
                initializeAzureSQLDatabase(); 
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
    private void initializeSQliteDatabase() {  
        System.out.println("Initializing SQLite database...");  
  
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

    // Azure SQL Database 用のデータベース初期化メソッド  
    private void initializeAzureSQLDatabase() {  
        System.out.println("Initializing Azure SQL database...");  
    
        String createCustomersTable =  
            "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'customers' AND type = 'U') " +  
            "BEGIN " +  
            "CREATE TABLE customers (" +  
            "id INT IDENTITY(1,1) PRIMARY KEY, " +  
            "name NVARCHAR(255) NOT NULL, " +  
            "email NVARCHAR(255), " +  
            "phone NVARCHAR(50), " +  
            "address NVARCHAR(255)" +  
            "); " +  
            "END";  
    
        String createHearingsTable =  
            "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'hearings' AND type = 'U') " +  
            "BEGIN " +  
            "CREATE TABLE hearings (" +  
            "id INT IDENTITY(1,1) PRIMARY KEY, " +  
            "customer_id INT NOT NULL, " +  
            "content NVARCHAR(MAX) NOT NULL, " +  
            "date NVARCHAR(20) NOT NULL, " + 
            "CONSTRAINT FK_hearings_customers FOREIGN KEY (customer_id) REFERENCES customers(id)" +  
            "); " +  
            "END";  
    
        try {  
            // JDBC ドライバのロード  
            Class.forName(jdbcDriver);  
    
            // データベースに接続  
            try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);  
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
