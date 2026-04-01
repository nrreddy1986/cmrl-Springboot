package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.entity.UserAddress;
import com.shellinfo.demo.service.AddressService;
import com.shellinfo.demo.service.CommonAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private CommonAuthService authService;

    /// 🔹 Add
    @PostMapping
    public ResponseEntity<?> add(HttpServletRequest request,
                                 @RequestBody UserAddress address) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(
                service.addAddress(userId, address)
        );
    }

    /// 🔹 Get All
    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(
                service.getAddresses(userId)
        );
    }

    /// 🔹 Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @PathVariable Long id,
                                    @RequestBody UserAddress address) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(
                service.updateAddress(userId, id, address)
        );
    }

    /// 🔹 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,
                                    @PathVariable Long id) {

        String userId = authService.getUserIdFromRequest(request);

        service.deleteAddress(userId, id);

        return ResponseEntity.ok("Deleted");
    }

    /// 🔹 Set Default
    @PostMapping("/default/{id}")
    public ResponseEntity<?> setDefault(HttpServletRequest request,
                                        @PathVariable Long id) {

        String userId = authService.getUserIdFromRequest(request);

        service.setDefault(userId, id);

        return ResponseEntity.ok("Default updated");
    }
}
