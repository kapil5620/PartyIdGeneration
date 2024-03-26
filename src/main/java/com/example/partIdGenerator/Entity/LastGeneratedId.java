package com.example.partIdGenerator.Entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("last_generated_customer_id")
public class LastGeneratedId implements Serializable {
    Integer id;
    String last_generated_id;
}