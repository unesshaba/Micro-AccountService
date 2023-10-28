package org.sid.bankaccountservice.service;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountMapper accountMapper;
@Autowired
    private BankAccountRepository bankAccountRepository;


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();
        BankAccount saveBankAccount=bankAccountRepository.save(bankAccount);
//        BankAccountResponseDTO bankAccountResponseDTO= BankAccountResponseDTO.builder()
//                .id(saveBankAccount.getId())
//                .createdAt(saveBankAccount.getCreatedAt())
//                .balance(saveBankAccount.getBalance())
//                .type(saveBankAccount.getType())
//                .currency(saveBankAccount.getCurrency())
//                .build();
       BankAccountResponseDTO bankAccountResponseDTO =accountMapper.fromBankAccount(saveBankAccount);

        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();
        BankAccount saveBankAccount=bankAccountRepository.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO =accountMapper.fromBankAccount(saveBankAccount);

        return bankAccountResponseDTO;
    }
}
