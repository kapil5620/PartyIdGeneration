package com.example.partIdGenerator.Repository;

import com.example.partIdGenerator.Entity.CustomerIdSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerIdDao {
    public static final String CUSTOMER_ID_HASH_KEY = "Customer_Id";
    public static final String LAST_GENERATED_HASH_KEY = "Last_Generated_Customer_Id";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String saveData(CustomerIdSave customerIdSave) {
        String partyId = customerIdSave.getCustomerId();
        redisTemplate.opsForHash().put(CUSTOMER_ID_HASH_KEY, partyId, customerIdSave);
        setLastGeneratedPartyId(customerIdSave.getCustomerId());
        return customerIdSave.getCustomerId();
    }

    public String getLastGeneratedPartyId() {
        return (String) redisTemplate.opsForHash().get(LAST_GENERATED_HASH_KEY, "last_generated_customer_id");
    }

    public void setLastGeneratedPartyId(String lastGeneratedPartyId) {
        redisTemplate.opsForHash().put(LAST_GENERATED_HASH_KEY, "last_generated_customer_id", lastGeneratedPartyId);
    }
}