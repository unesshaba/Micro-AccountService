package org.sid.bankaccountservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.enums.AccountType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountRequestDTO {
    private Double balance;
    private String currency;
     private AccountType type;
}
