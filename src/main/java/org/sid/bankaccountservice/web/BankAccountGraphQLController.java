package org.sid.bankaccountservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repository.BankAccountRepository;
import org.sid.bankaccountservice.repository.CustomerRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;
    @QueryMapping
    public List<BankAccount>accountsList(){
        return  bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById( @Argument String id){
        return  bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO update(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public  void deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);

    }
    @QueryMapping
    public  List<Customer> customers(){
     return    customerRepository.findAll();


    }

//    @Data @NoArgsConstructor@AllArgsConstructor
//    class BankAccountDTO{
//        private Double balance;
//        private String currency;
//        private AccountType type;
//    }
//    record BankAccountDTO(Double balance,String type,String currency){
//
//    }
}
