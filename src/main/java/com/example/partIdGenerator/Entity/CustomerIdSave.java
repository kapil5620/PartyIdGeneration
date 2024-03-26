package com.example.partIdGenerator.Entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Customer_Id")
public class CustomerIdSave implements Serializable {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String mobileNo;
    String customerId;
}