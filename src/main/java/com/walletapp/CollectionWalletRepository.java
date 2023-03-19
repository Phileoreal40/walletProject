package com.walletapp;

import java.util.Collection;

public interface CollectionWalletRepository {
    WalletDto addWallet(WalletDto wallet);
    WalletDto getWallet(Integer  id);
    WalletDto updateWallet(WalletDto walletDto);
    WalletDto deleteWallet(Integer walletId);

}

