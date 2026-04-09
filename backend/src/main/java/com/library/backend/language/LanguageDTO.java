package com.library.backend.language;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageDTO {
  private String language;
  private String languageKey;

  public LanguageDTO(Language l) {
      this.language = l.getLanguage();
      this.languageKey = l.getLanguage();
  }
}
