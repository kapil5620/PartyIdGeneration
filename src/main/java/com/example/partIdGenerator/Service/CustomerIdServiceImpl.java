package com.example.partIdGenerator.Service;

import com.example.partIdGenerator.Entity.CustomerIdInput;
import com.example.partIdGenerator.Entity.CustomerIdSave;
import com.example.partIdGenerator.Repository.CustomerIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerIdServiceImpl implements CustomerIdService {

    @Autowired
    CustomerIdDao dao;

    @Override
    public synchronized String createPartyId(CustomerIdInput customerIdInput) {
        CustomerIdSave save = new CustomerIdSave();
        String partyId = generatePartyId();

        save.setId(customerIdInput.getId());
        save.setFirstName(customerIdInput.getFirstName());
        save.setLastName(customerIdInput.getLastName());
        save.setEmail(customerIdInput.getEmail());
        save.setMobileNo(customerIdInput.getMobileNo());
        save.setCustomerId(partyId);

        if (!dao.saveData(save).isEmpty()) {
            return partyId;
        } else {
            return "CustomerId is not generated";
        }
    }

    private String generatePartyId() {
        String latestPartyId = dao.getLastGeneratedPartyId();
        if (latestPartyId == null) {
            latestPartyId = "ABM0";
        }
        String prefix = latestPartyId.replaceAll("[^A-Za-z]", "");
        int numericPart = Integer.parseInt(latestPartyId.replaceAll("[^0-9]", ""));
        numericPart++;
        return prefix + String.format("%09d", numericPart);
    }
}