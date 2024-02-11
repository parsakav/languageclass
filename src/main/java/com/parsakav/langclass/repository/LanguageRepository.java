package com.parsakav.langclass.repository;

import com.parsakav.langclass.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {

    public Language findLanguageByLangname(String langname);

}
