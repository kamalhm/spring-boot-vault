package com.khm.springvault;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SecretController {

  private final VaultHelper vaultHelper;

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

}
