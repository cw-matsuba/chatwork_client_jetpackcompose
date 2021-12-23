# chatwork_client_jetpackcompose
ChatWorkのAPIを使い簡単なクライアントアプリを作りました。  
JetpackComposeを使い、Viewを生成する。

Viewで用いる変数についてはViewModelでMutableState型の変数をCompose内でObserveしている。

### 実装した機能
- Chatwork API Tokenを入力するログイン画面
- ルーム一覧画面
### 使用したライブラリ
- JetpackCompose
- Hilt
- Retrofit
- OKHttpClient
