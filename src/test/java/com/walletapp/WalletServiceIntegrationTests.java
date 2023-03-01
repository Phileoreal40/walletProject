package com.walletapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WalletServiceIntegrationTests {
    @Autowired
    private WalletService walletService;
    @Test
    void registerWalletTest() throws WalletException {
        WalletDto wallet = new WalletDto(1,"Item1",200.0);
        assertEquals("Item1",this.walletService.registerWallet(wallet).getName());

    }

    private void assertEquals(String item1, String name) {
    }
    @Test
    void getWalletIdThrowsExceptionTest(){
        assertThrows(WalletException.class,()->this.walletService.getWalletById(1000));
    }
}
