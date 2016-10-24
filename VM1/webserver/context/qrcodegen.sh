#Generate QRcode for a specific client. Pass the number of the client in argument.
#echo "startscprit"


pathkey="/etc/pki/client$1"
pubkey=$pathkey/client.pub
privkey=$pathkey/client.pvk

pathotp="/var/www/otp/client$1"
mkdir -p $pathotp




< /dev/urandom tr -dc 0-9 | head -c 8 > $pathotp/otp.pt
#echo $((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))



#echo $((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10)) > $pathotp/otp.pt



#Encrypt it with the client public key
openssl rsautl -in $pathotp/otp.pt -out $pathotp/otp.rsa -inkey $pubkey -keyform PEM -pubin -encrypt

#Pre-encode so qrencode will unsersand the input
openssl enc -base64 -in $pathotp/otp.rsa -out $pathotp/otp.rsa.base64 -e -kfile $privkey

#Encode as a qrcode
cat $pathotp/otp.rsa.base64 | qrencode -o /var/www/otp/qrCode$1.png
#echo $((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))$((RANDOM%10))

#echo "   Script Done!!   "
