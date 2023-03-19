package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
//@Component
//@Service
public class CollectionWalletServiceImpl implements WalletService{

    @Autowired
    private CollectionWalletRepository walletRepository;

    @Override
    public WalletDto registerWallet(WalletDto nweWallet)  {
        return walletRepository.addWallet(nweWallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {

        WalletDto foundwallet = walletRepository.getWallet(walletId);
        if(foundwallet==null)
            throw new WalletException("Wallet id does not exists:");
        return foundwallet;
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) {
        return walletRepository.updateWallet(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        WalletDto foundwallet = walletRepository.getWallet(walletId);
        if(foundwallet==null)
            throw new WalletException("Wallet id does not exists:"+walletId);
        return this.walletRepository.deleteWallet(walletId);
    }




    @Override
    public List<WalletDto> getAllWallets() {
        return null;
    }
}
