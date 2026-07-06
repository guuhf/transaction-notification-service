package com.guuh.transaction_notification_service.business.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryReportDto {
    private String name;
    private BigDecimal total;
}
