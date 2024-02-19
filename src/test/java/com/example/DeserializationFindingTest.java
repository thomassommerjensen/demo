package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class DeserializationFindingTest {

    @Inject
    private MyClassRepository repository;

    @Test
    void shouldFailToDeserializeLocalDateTimeObjectInStringObjectMap() {
        MyClass myClass = MyClass.builder()
            .id(UUID.randomUUID().toString())
            .stringObjectMap(Map.of("date", LocalDateTime.now()))
            .build();
        repository.save(myClass);

        Exception e = assertThrows(DataAccessException.class, () -> repository.findById(myClass.getId()));
        assertTrue(e.getMessage().startsWith("Cannot deserialize"));
    }

    @Test
    void shouldNotFailToDeserializeLocalDateTimeObjectInStringLocalDateTimeMap() {
        MyClass myClass = MyClass.builder()
            .id(UUID.randomUUID().toString())
            .stringLocalDateTimeMap(Map.of("date", LocalDateTime.now()))
            .build();
        repository.save(myClass);

        assertDoesNotThrow(() -> repository.findById(myClass.getId()));
    }
}