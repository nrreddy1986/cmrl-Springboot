package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.ApiResponse;
import com.shellinfo.demo.model.dto.UserAddressesDto;
import com.shellinfo.demo.model.entity.UserAddress;
import com.shellinfo.demo.service.AddressService;
import com.shellinfo.demo.service.CommonAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private CommonAuthService authService;

    /// 🔹 Get All
    @GetMapping
    public ResponseEntity<ApiResponse<UserAddressesDto>> getAll(HttpServletRequest request) {

        String userId = authService.getUserIdFromRequest(request);

        UserAddressesDto userAddressesDto = service.getAddresses(userId);

        int noRecords = userAddressesDto.getAddresses().size();
        return ResponseEntity.ok(
                ApiResponse.success(noRecords + " records found", userAddressesDto)
        );
    }

    /// 🔹 Add
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<UserAddress>> add(HttpServletRequest request,
                                                        @RequestBody UserAddress address) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(
                ApiResponse.success("Address Added Successfully.",
                        service.addAddress(userId, address)
                ));
    }

    /// 🔹 Update
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<UserAddress>> update(HttpServletRequest request,
                                                           @RequestBody UserAddress address) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(
                ApiResponse.success("Address Updated Successfully.",
                        service.updateAddress(userId, address.getId(), address)
                ));
    }

    /// 🔹 Delete
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<Map<String, String>>> delete(HttpServletRequest request,
                                                                   @RequestBody UserAddress address) {

        String userId = authService.getUserIdFromRequest(request);

        service.deleteAddress(userId, address.getId());

        Map<String, String> data =
                Collections.singletonMap("id", address.getId().toString());
        return ResponseEntity.ok(
                ApiResponse.success("Address Deleted Successfully.",
                        data
                ));
    }

    /// 🔹 Set Default
    @PostMapping("/default")
    public ResponseEntity<ApiResponse<Map<String, String>>> setDefault(HttpServletRequest request,
                                                                       @RequestBody Long id) {

        String userId = authService.getUserIdFromRequest(request);

        service.setDefault(userId, id);

        Map<String, String> data =
                Collections.singletonMap("id", id.toString());

        return ResponseEntity.ok(ApiResponse.success("Address Default updated Successfully.", data));
    }

    /// 🔹 Get Default
    @GetMapping("/default")
    public ResponseEntity<ApiResponse<UserAddress>> getDefault(HttpServletRequest request) {

        String userId = authService.getUserIdFromRequest(request);

        UserAddress userAddress = service.getDefault(userId);

        return ResponseEntity.ok(
                ApiResponse.success("Record fetched", userAddress)
        );
    }
}
