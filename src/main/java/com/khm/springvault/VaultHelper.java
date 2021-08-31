package com.khm.springvault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.VaultTransitContext;

@Service
@RequiredArgsConstructor
public class VaultHelper {

  private final VaultTemplate vaultTemplate;

  public static final String AES_KEY = "aes-key";
  public static final String CONVERGENT_KEY = "convergent-key";

  private VaultTransitOperations getTransit() {
    return vaultTemplate.opsForTransit("transit");
  }

  public String encryptWithAesKey(String plainText) {
    return getTransit().encrypt(AES_KEY, plainText);
  }

  public String decryptWithAesKey(String cipherText) {
    return getTransit().decrypt(AES_KEY, cipherText);
  }

  public String encryptWithConvergentKey(String plainText) {
    final VaultTransitContext context =
        VaultTransitContext.builder()
            .context("context".getBytes())
            .nonce("random".getBytes())
            .build();

    return getTransit().encrypt(CONVERGENT_KEY, plainText.getBytes(), context);
  }

  public String decryptWithConvergentKey(String cipherText) {
    final VaultTransitContext context =
        VaultTransitContext.builder()
            .context("context".getBytes())
            .nonce("random".getBytes())
            .build();
    return new String(getTransit().decrypt(CONVERGENT_KEY, cipherText, context));
  }
}
