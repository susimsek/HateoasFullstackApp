package io.github.susimsek.hateoasbackend.exception;

import lombok.NonNull;

public class ResourceNotFoundException extends RestException {

    public ResourceNotFoundException(@NonNull String message, Object[] args) {
        super(message, args);
    }
}
