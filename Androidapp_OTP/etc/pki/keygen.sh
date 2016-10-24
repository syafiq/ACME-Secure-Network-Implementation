mkdir -p client$1/
cd client$1/

#Generate regular private key
openssl genrsa -out client.key 4096	

#convert key so android can process
openssl pkcs8 -topk8 -in client.key -outform DER -nocrypt -out client.pvk

#Generate public key from private key
openssl rsa -in client.key -out client.pub -outform PEM -pubout

