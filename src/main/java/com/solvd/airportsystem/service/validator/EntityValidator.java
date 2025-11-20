package com.solvd.airportsystem.service.validator;

public class EntityValidator {

    public static <T> T validateEntityExists(T entity, String entityName, Long id) {
        if (entity == null) {
            throw new com.solvd.airportsystem.service.exception.EntityNotFoundException(
                    entityName + " with id " + id + " cannot be found");
        }
        return entity;
    }

    public static void validateId(Long id, String entityName) {
        if (id == null) {
            throw new IllegalArgumentException(entityName + " id cannot be null");
        }
    }
}

