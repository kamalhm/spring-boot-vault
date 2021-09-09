package com.khm.springvault;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringVaultApplication {

    /*
    - Before run, create a transit engine in vault with path `transit`
    - Hit curl to generate key, derived false
    curl --request POST \
      --url http://127.0.0.1:8200/v1/transit/keys/aes-key \
      --header 'X-Vault-Token: root' \
      --data '{
      "type": "aes256-gcm96"
    }
    '
    - Create another key with convergent encryption true
    curl --request POST \
      --url http://127.0.0.1:8200/v1/transit/keys/convergent-key \
      --header 'X-Vault-Token: root' \
      --data '{
      "type": "aes256-gcm96",
      "derived": true,
      "convergent_encryption": true
    }
    '
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringVaultApplication.class, args);
    }

}

