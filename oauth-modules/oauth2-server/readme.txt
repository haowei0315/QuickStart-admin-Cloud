1.密码模式
http://127.0.0.1:8762/auth/oauth/token?username=root&password=root&grant_type=password
demo:
{
    "access_token": "504333ce-7150-46da-87f7-f28bc62c425d",
    "token_type": "bearer",
    "expires_in": 31157,
    "scope": "all read",
    "code": 2000
}

2.验证码模式，注这里redirect_uri地址需和数据库里保持一致
http://localhost:8762/auth/oauth/authorize?response_type=code&client_id=spdata&redirect_uri=http://localhost:9000
http://127.0.0.1:8762/auth/oauth/token?client_id=spdata&client_secret=secret&grant_type=authorization_code&redirect_uri=http://localhost:9000&code=uRi8lj

3.resource资源服务器请求方式，header头中加入上面取得的access_token
127.0.0.1:8763/admin/role/list
Authorization	bearer 504333ce-7150-46da-87f7-f28bc62c425d

