package com.walletapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CollectionWalletRepositoryTests {

    @Autowired
    private CollectionWalletRepository walletRepository;


    @BeforeEach
    public void init(){
        walletRepository.createWallet(new WalletDto(1,"Item 1",200.0));
        
    }
    @Test
    public void deleteWalletTest(){
        WalletDto deletedWallet = walletRepository.deleteWalletById(1);
        assertEquals("Item1",deletedWallet.getName());
    }

    private void assertEquals(String item1, String name) {
    }

}
