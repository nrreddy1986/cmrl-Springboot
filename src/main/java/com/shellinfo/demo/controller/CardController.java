package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.entity.UserCard;
import com.shellinfo.demo.service.CardService;
import com.shellinfo.demo.service.CommonAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService service;

    @Autowired
    private CommonAuthService authService;

    /// 🔹 Add Card (token-based)
    @PostMapping
    public ResponseEntity<?> addCard(HttpServletRequest request,
                                     @RequestBody UserCard card) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(service.addCard(userId, card));
    }

    /// 🔹 Get Cards
    @GetMapping
    public ResponseEntity<?> getCards(HttpServletRequest request) {

        String userId = authService.getUserIdFromRequest(request);

        return ResponseEntity.ok(service.getCards(userId));
    }

    /// 🔹 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,
                                    @PathVariable Long id) {

        String userId = authService.getUserIdFromRequest(request);

        service.deleteCard(userId, id);
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