package com.dev.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleTO {
    private Long id;
    private Long quantity;
    private Double unitaryValue;
    private Double totalValue;
    private Double icms;
    private Double ipi;
    private Double shipping;
    private Double netValue;
    private Double totalAmountInvoice;
    private Long dataId;
    private Long productId;
    private Long cfopId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}
