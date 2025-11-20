package com.solvd.airportsystem.service.base;

import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.Optional;

public abstract class BaseService<T, ID> {

    protected void prepareForCreate(T entity) {
        setId(entity, null);
    }

    protected T validateAndGet(ID id, Optional<T> optional, String entityName) {
        EntityValidator.validateId((Long) id, entityName);
        T entity = optional.orElse(null);
        EntityValidator.validateEntityExists(entity, entityName, (Long) id);
        return entity;
    }

    protected abstract void setId(T entity, ID id);
}

