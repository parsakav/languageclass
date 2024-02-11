package com.parsakav.langclass.service;


import com.parsakav.langclass.model.Language;
import com.parsakav.langclass.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    LanguageRepository languageDao;

    @Override
    public Language getLanguageByName(String langname) {
        return languageDao.findLanguageByLangname(langname);
    }

    @Override
    public List<Language> getAllLanguage() {
        return languageDao.findAll();
    }

    @Override
    public void saveLanguage(Language language) {
        languageDao.save(language);

    }

    @Override
    public boolean languageExist(String langname) {
        return getLanguageByName(langname) !=null;

    }

}
