# OAuth2 authorize code flow (for web server)
localhost:8082/oauth/authorize?response_type=code&client_id=lin&redirect_uri=http://microservice&scope=read
localhost:8082/oauth/token?grant_type=authorization_code&redirect_uri=http://microservice&code=
use short period access token and long time refresh token.

# OAuth2 implicit flow (for SPA)
localhost:8082/oauth/authorize?response_type=token&client_id=sampleClientId&redirect_uri=http://lin.com&scope=read
Cannot issue refresh token for implicit flow

# OAuth2 password flow

# generate JWT RSA key
keytool -genkeypair -alias mytest -keyalg RSA -keypass mypass -keystore mytest.jks -storepass mypass
keytool -importkeystore -srckeystore mytest.jks -destkeystore mytest.jks -deststoretype pkcs12

public key can retrieve from public key endpoint:
localhost:8082//oauth/tokey_key
