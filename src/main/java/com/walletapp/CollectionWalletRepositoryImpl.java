package com.walletapp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Repository
public class CollectionWalletRepositoryImpl implements CollectionWalletRepository {

    private Map<Integer,WalletDto> walletMap = new HashMap<>();

    @Override
    public WalletDto createWallet(WalletDto newWallet) {
         this.walletMap.put(newWallet.getId(),newWallet);
        return newWallet;
    }

    @Override
    public WalletDto getWalletById(Integer walletId) {
        return this.walletMap.get(walletId);
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) {
        this.walletMap.replace(wallet.getId(),wallet);
        return wallet;
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) {

        return  this.walletMap.remove(walletId);
    }

    @Override
    public Collection<WalletDto> getAllWallets() {
        return  this.walletMap.values();
    }
}
