package com.walletapp;

import java.util.List;

public interface WalletService {

    WalletDto registerWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer walletId) throws WalletException;
    WalletDto updateWallet(WalletDto wallet)throws WalletException;
    WalletDto deleteWalletById(Integer walletId)throws WalletException;




    List<WalletDto> getAllWallets();
}
