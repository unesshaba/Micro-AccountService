package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repository.BankAccountRepository;
import org.sid.bankaccountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    @Autowired
      BankAccountRepository bankAccountRepository;
    @Autowired
    CustomerRepository customerRepository;
    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }
    @Bean
CommandLineRunner start(BankAccountRepository bankAccountRepository){
        return args -> {
            Stream.of("youness","karim","amina","hanaa").forEach(c->{
                Customer customer=Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                for (int i = 0; i <10 ; i++) {


                    BankAccount bankAccount =BankAccount.builder( )
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .balance( 1000+Math.random()*9000)
                            .createdAt(new Date() )
                            .currency("MAD")
                            .customer(customer)
                            .build();

                    bankAccountRepository.save(bankAccount);
                }
            });

        };

}
}
