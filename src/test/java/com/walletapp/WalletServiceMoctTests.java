package com.walletapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
@SpringBootTest
public class WalletServiceMoctTests {

    @Autowired
    private WalletService walletService;

    @MockBean
    private CollectionWalletRepository walletRepository;

    @Test
    void createWalletTest(){
        WalletDto newWallet = new WalletDto(1,"Dayalan",2345.0);
        assertEquals("Dayalan", this.walletService.createWallet(newWallet).getName());
    }
}
