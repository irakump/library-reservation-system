package com.library.backend.language;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for accessing Language entities through API calls.
 */
public interface LanguageRepository extends CrudRepository<Language, String> {
}
