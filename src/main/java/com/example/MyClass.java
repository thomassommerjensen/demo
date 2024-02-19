package com.example;

import java.time.LocalDateTime;
import java.util.Map;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@MappedEntity
public class MyClass {

    @Id
    public String id;
    public Map<String, Object> stringObjectMap;
    public Map<String, LocalDateTime> stringLocalDateTimeMap;
}