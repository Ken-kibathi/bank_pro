package com.accounts.accounts.services;
import com.accounts.accounts.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@FeignClient(name = "account-service", url = "http://localhost:8080")
public interface AccountServiceClient {
    @GetMapping("/auth/authenticate")

    Optional<Account> findByAccountNumber(@RequestParam String AccountNumber);

}
