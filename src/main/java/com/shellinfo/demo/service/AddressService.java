package com.shellinfo.demo.service;

import com.shellinfo.demo.model.entity.UserAddress;
import com.shellinfo.demo.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    /// 🔹 Add Address
    public UserAddress addAddress(String userId, UserAddress address) {

        address.setUserId(userId);

        /// ✅ If first address → make default
        if (userAddressRepository.findByUserId(userId).isEmpty()) {
            address.setDefaultAddress(true);
        }

        /// ✅ If marked default → unset old one
        if (address.isDefaultAddress()) {
            userAddressRepository.findByUserIdAndDefaultAddressTrue(userId)
                    .ifPresent(old -> {
                        old.setDefaultAddress(false);
                        userAddressRepository.save(old);
                    });
        }

        return userAddressRepository.save(address);
    }

    /// 🔹 Get All
    public List<UserAddress> getAddresses(String userId) {
        return userAddressRepository.findByUserId(userId);
    }

    /// 🔹 Update Address
    public UserAddress updateAddress(String userId, Long id, UserAddress updated) {

        UserAddress address = userAddressRepository.findById(id).orElseThrow();

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
        address.setCountry(updated.getCountry());
        address.setCountryCode(updated.getCountryCode());
        address.setLandmark(updated.getLandmark());
        address.setAddressType(updated.getAddressType());

        /// ✅ Handle default
        if (updated.isDefaultAddress()) {
            userAddressRepository.findByUserIdAndDefaultAddressTrue(userId)
                    .ifPresent(old -> {
                        old.setDefaultAddress(false);
                        userAddressRepository.save(old);
                    });
            address.setDefaultAddress(true);
        }

        return userAddressRepository.save(address);
    }

    /// 🔹 Delete Address
    public void deleteAddress(String userId, Long id) {

        UserAddress address = userAddressRepository.findById(id).orElseThrow();

        if (!address.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        userAddressRepository.delete(address);
    }

    /// 🔹 Set Default Address
    public void setDefaultAddress(String userId, Long id) {

        UserAddress newDefault = userAddressRepository.findById(id).orElseThrow();

        if (!newDefault.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        /// unset old
        userAddressRepository.findByUserIdAndDefaultAddressTrue(userId)
                .ifPresent(old -> {
                    old.setDefaultAddress(false);
                    userAddressRepository.save(old);
                });

        newDefault.setDefaultAddress(true);
        userAddressRepository.save(newDefault);
    }

    /// 🔹 Get Default Address
    public UserAddress getDefault(String userId) {

        return userAddressRepository.findByUserIdAndDefaultAddressTrue(userId)
                .orElseThrow(() -> new RuntimeException("Default address not found"));
    }
}
