#!/bin/bash
apiKey=AIzaSyA4yiZZUzPKUrKiHz3FvOToGUkVQgDKmHo
email=$1
pass=$2
curl 'https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key='$apiKey \
-H 'Content-Type: application/json' \
--data-binary '{"email":"'$email'","password":"'$pass'","returnSecureToken":true}'