# Azure Migrate を利用する
Azure Migrate: App Containerization Tool を利用するためには、アプリケーションサーバーの 22 番ポートに SSH 接続ができる状態である必要があります。本ドキュメントでは、ssh サービスを開始するための手順を説明します。

## ssh サービスを開始する手順
### サービスのステータス確認
以下のコマンドを実行して ssh サービスの状況を確認します。
```
service ssh status
```

#### ssh サービスが起動している場合
以下が返ってた場合は ssh サービスが起動しています。
```
ssh.service - OpenBSD Secure Shell server
    Loaded: loaded (/lib/systemd/system/ssh.service; enabled; vendor preset: enabled)
    Active: active (running) since Thu 2025-01-09 16:26:15 JST; 1min 6s ago
        Docs: man:sshd(8)
            man:sshd_config(5)
    Main PID: 6145 (sshd)
        Tasks: 1 (limit: 9294)
        Memory: 2.1M
        CGroup: /system.slice/ssh.service
                └─6145 "sshd: /usr/sbin/sshd -D [listener] 0 of 10-100 startups"
```

#### ssh サービスが終了している場合
以下が返ってきた場合は ssh サービスが終了しています。
```
ssh.service - LSB: OpenBSD Secure Shell server
    Loaded: loaded (/etc/init.d/ssh; generated)
    Active: active (exited) since Thu 2025-01-09 15:27:39 JST; 9s ago
        Docs: man:systemd-sysv-generator(8)
    Process: 44163 ExecStart=/etc/init.d/ssh start (code=exited, status=0/SUCCESS)
```

ssh サービスが終了している場合は、以下のコマンドを実行して openssh-server をインストールして下さい。
```
sudo apt update
sudo apt install openssh-server
```

インストール後、以下のコマンドを実行して ssh サービスを起動します。
サービス起動後は ssh サービスの状況を確認して、サービスが開始していることを確認して下さい。
```
sudo systemctl start ssh
```

#### ssh サービスが無効化されている場合
以下が返ってきた場合は、ssh サービスが無効化されています。
```
ssh.service
    Loaded: masked (Reason: Unit ssh.service is masked.)
    Active: inactive (dead)
```

ssh サービスを開始するために以下のコマンドを実行して、まずはマスク状態を解除します。
```
sudo systemctl unmask ssh
```

(任意) 自動起動の設定を行う場合は以下のコマンドを実行します。
```
sudo systemctl enable ssh
```

以下のコマンドを実行して ssh サービスを起動します。
サービス起動後、ssh サービスの状況を確認して、サービスが開始していることを確認します。
```
sudo systemctl start ssh
```

### root ユーザーのパスワード設定
サービスのステータスを確認し、ssh サービスが開始していることを確認したら、以下のコマンドにより root ユーザーのパスワードを設定してください。root ユーザーのパスワードはツールを使って SSH 接続をする際に必要になります。
```
sudo passwd root
```

## 利用するデータベースへの切り替え
本リポジトリのコードはあくまでサンプルです。sqlite の利用を設定した状態で Azure Migrate を使ったデプロイを行った場合、sqlite のデータベースファイルアクセス時に Permission Error が出ることを確認しています。Azure Migrate を使ったデプロイをする際、database.properties で `db.type=azure` を設定して Azure Database SQL を利用するように切り替えてください。

## Azure SQL Database への接続設定
Azure にデプロイしたアプリケーションが Azure SQL Database へ接続できるように、Azure ポータルの SQL Server のネットワーク設定から「Azure サービスおよびリソースにこのサーバーへのアクセスを許可する」を有効化して下さい。

## Azure Migrate ツールの利用
省略
