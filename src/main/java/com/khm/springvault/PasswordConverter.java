package com.khm.springvault;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Converter
@RequiredArgsConstructor
public class PasswordConverter implements AttributeConverter<String, String> {

  private final VaultHelper vaultHelper;

  @Override
  public String convertToDatabaseColumn(String attribute) {
    return vaultHelper.encryptWithAesKey(attribute);
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    return vaultHelper.decryptWithAesKey(dbData);
  }
}
