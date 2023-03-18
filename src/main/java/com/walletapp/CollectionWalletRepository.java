package com.walletapp;

import java.util.Collection;

public interface CollectionWalletRepository {
    WalletDto createWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer  walletId);
    WalletDto updateWallet(WalletDto wallet);
    WalletDto deleteWalletById(Integer walletId);
    Collection<WalletDto> getAllWallets();
}

