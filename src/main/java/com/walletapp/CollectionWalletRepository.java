package com.walletapp;

import java.util.Collection;

public interface CollectionWalletRepository {
    WalletDto createWallet(WalletDto wallet);
    WalletDto getWalletById(Integer  id);
    WalletDto updateWallet(WalletDto walletDto);
    WalletDto deleteWalletById(Integer walletId);
    public Collection<WalletDto> getAllWallets();
}

