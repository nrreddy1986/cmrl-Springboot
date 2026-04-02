package com.shellinfo.demo.service;

import com.shellinfo.demo.model.dto.UserAddressesDto;
import com.shellinfo.demo.model.entity.UserAddress;
import com.shellinfo.demo.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private UserAddressRepository repo;

    /// 🔹 Add Address
    public UserAddress addAddress(String userId, UserAddress address) {

        address.setUserId(userId);

        /// ✅ If first address → make default
        if (repo.findByUserId(userId).isEmpty()) {
            address.setDefault(true);
        }

        /// ✅ If marked default → unset old one
        if (address.isDefault()) {
            repo.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(old -> {
                        old.setDefault(false);
                        repo.save(old);
                    });
        }

        return repo.save(address);
    }

    /// 🔹 Get All
    public UserAddressesDto getAddresses(String userId) {

        UserAddressesDto userAddressesDto = new UserAddressesDto();
        userAddressesDto.setAddresses(repo.findByUserId(userId));
        return userAddressesDto;
    }

    /// 🔹 Update Address
    public UserAddress updateAddress(String userId, Long id, UserAddress updated) {

        UserAddress address = repo.findById(id).orElseThrow();

        if (!address.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        address.setName(updated.getName());
        address.setMobile(updated.getMobile());
        address.setAddressLine1(updated.getAddressLine1());
        address.setAddressLine2(updated.getAddressLine2());
        address.setCity(updated.getCity());
        address.setState(updated.getState());
        address.setPinCode(updated.getPinCode());

        /// ✅ Handle default
        if (updated.isDefault()) {
            repo.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(old -> {
                        old.setDefault(false);
                        repo.save(old);
                    });
            address.setDefault(true);
        }

        return repo.save(address);
    }

    /// 🔹 Delete Address
    public void deleteAddress(String userId, Long id) {

        UserAddress address = repo.findById(id).orElseThrow();

        if (!address.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(address);
    }

    /// 🔹 Set Default Address
    public void setDefault(String userId, Long id) {

        UserAddress newDefault = repo.findById(id).orElseThrow();

        if (!newDefault.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        /// unset old
        repo.findByUserIdAndIsDefaultTrue(userId)
                .ifPresent(old -> {
                    old.setDefault(false);
                    repo.save(old);
                });

        newDefault.setDefault(true);
        repo.save(newDefault);
    }

    /// 🔹 Get Default Address
    public UserAddress getDefault(String userId) {

        return repo.findByUserIdAndIsDefaultTrue(userId)
                .orElseThrow(() -> new RuntimeException("Default address not found"));
    }
}
