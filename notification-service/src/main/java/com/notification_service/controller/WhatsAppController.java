package com.notification_service.controller;

import com.notification_service.dto.WhatsAppRequest;
import com.notification_service.service.WhatsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/whatsapp")
@RequiredArgsConstructor
public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    @PostMapping("/send")
    public ResponseEntity<String> sendWhatsAppMessage(@RequestBody WhatsAppRequest request) {
        return ResponseEntity.ok(whatsAppService.sendWhatsAppMessage(request));
    }
}