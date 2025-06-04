package org.example.model.enums;

import org.example.model.repository.FileRepository;
import org.example.model.repository.InMemoryRepository;
import org.example.model.repository.Repository;
import org.example.utils.AbsolutePathFinder;

public enum TypeStore {
    IN_MEMORY(new InMemoryRepository()), IN_DATABASE(null);

    private Repository repository;

    TypeStore (Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }
}
