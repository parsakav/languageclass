package com.parsakav.langclass.service;

import com.parsakav.langclass.model.Language;

import java.util.List;

public interface LanguageService {
    Language getLanguageByName(String langname);
    List<Language> getAllLanguage();
    void saveLanguage(Language language);
    boolean languageExist(String langname);
}
