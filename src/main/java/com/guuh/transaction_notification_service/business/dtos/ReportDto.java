package com.guuh.transaction_notification_service.business.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal openingBalance;
    private BigDecimal balance;
    private Integer totalTransactions;
    private String email;

    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

    private List<CategoryReportDto> categories;
}
