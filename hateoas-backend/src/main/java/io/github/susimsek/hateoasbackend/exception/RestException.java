package io.github.susimsek.hateoasbackend.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RestException extends RuntimeException {

    @NonNull
    final String message;

    @Nullable
    final transient Object[] args;
}