package com.guuh.transaction_notification_service.controller;


import com.guuh.transaction_notification_service.business.NotificationService;
import com.guuh.transaction_notification_service.business.dtos.ReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody ReportDto dto,
                                          @RequestParam String email){
        notificationService.sendEmail(dto, email);
        return ResponseEntity.ok().build();
    }

}
