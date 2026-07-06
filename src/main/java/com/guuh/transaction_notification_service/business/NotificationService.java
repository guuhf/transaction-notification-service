package com.guuh.transaction_notification_service.business;

import com.guuh.transaction_notification_service.business.dtos.ReportDto;
import com.guuh.transaction_notification_service.infrastructure.exception.EmailSendException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${send.email.from}")
    public String from;
    @Value("${send.email.personalName}")
    public String personalName;

    public void sendEmail(ReportDto dto, String email){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper
                    (message, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(from, personalName));
            mimeMessageHelper.setTo(InternetAddress.parse((email)));
            mimeMessageHelper.setSubject("Task Notification");

            Context context = new Context();
            context.setVariable("totalIncome", dto.getTotalIncome());
            context.setVariable("totalExpense", dto.getTotalExpense());
            context.setVariable("openingBalance", dto.getOpeningBalance());
            context.setVariable("balance", dto.getBalance());
            context.setVariable("totalTransactions", dto.getTotalTransactions());
            context.setVariable("initialDate", dto.getInitialDate());
            context.setVariable("finalDate", dto.getFinalDate());
            context.setVariable("categories", dto.getCategories());

            String template = templateEngine.process("notification", context);
            mimeMessageHelper.setText(template, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send email notification to {}", email, e);
            throw new EmailSendException(
                    "Failed to send email notification to: " + email +
                            ". Cause: " + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()),
                    e
            );
        }
    }
}
