package com.example.partIdGenerator.Service;

import com.example.partIdGenerator.Entity.CustomerIdInput;

public interface CustomerIdService {
    String createPartyId(CustomerIdInput customerIdInput);
}