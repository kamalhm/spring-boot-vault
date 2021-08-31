package com.khm.springvault;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SecretController {

  private final VaultHelper vaultHelper;
  private final List<Secret> secretList = new ArrayList<>();

  @GetMapping("/encrypt/{plainText}")
  public String encryptWithAesKey(@PathVariable String plainText) {
    return vaultHelper.encryptWithAesKey(plainText);
  }

  @GetMapping("/decrypt")
  public String decryptWithAesKey(@RequestBody String cipherText) {
    return vaultHelper.decryptWithAesKey(cipherText);
  }

  @GetMapping("/encrypt/convergent/{plainText}")
  public String encryptWithConvergentKey(@PathVariable String plainText) {
    return vaultHelper.encryptWithConvergentKey(plainText);
  }

  @GetMapping("/decrypt/convergent")
  public String decryptWithConvergentKey(@RequestBody String cipherText) {
    return vaultHelper.decryptWithConvergentKey(cipherText);
  }

  @GetMapping("/secret")
  public List<Secret> getAllSecret() {
    return secretList;
  }

  @GetMapping("/secret/decrypted")
  public List<Secret> getAllDecryptedSecret() {
    return secretList.stream()
        .map(secret -> new Secret(secret.username(), decryptWithAesKey(secret.password())))
        .collect(Collectors.toList());
  }

  @PostMapping("/secret")
  public Secret saveSecret(@RequestBody Secret secret) {
    final Secret newSecret =
        new Secret(secret.username(), vaultHelper.encryptWithAesKey(secret.password()));
    secretList.add(newSecret);
    return newSecret;
  }

  @PostMapping("/secret/convergent")
  public Secret saveSecretWithConvergentKey(@RequestBody Secret secret) {
    final Secret newSecret =
        new Secret(secret.username(), vaultHelper.encryptWithAesKey(secret.password()));
    secretList.add(newSecret);
    return newSecret;
  }
}
