package com.walletapp;

import java.util.Collection;
import java.util.List;

public interface WalletService {

    WalletDto createWallet(WalletDto newWallet);
    WalletDto getWalletByID(Integer walletId) throws WalletException;
    WalletDto updateWallet(WalletDto wallet)throws WalletException;
    WalletDto deleteWalletById(Integer walletId)throws WalletException;
    Double addFunds(Integer id, Double amount) throws WalletException;

    Double withdrawFunds(Integer id, Double amount) throws WalletException;

    String tranfersFunds(Integer fromId, Integer toId, Double amount) throws WalletException;



   public Collection<WalletDto> getAllWallets();


    WalletDto registerWallet(WalletDto wallet);

    void getWalletById(int i);
}
