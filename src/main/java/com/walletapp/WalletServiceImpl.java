package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletJpaRepository walletJpaRepository;

    @Override
    public WalletDto registerWallet(WalletDto newWallet) {
        return this.walletJpaRepository.save(newWallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if (walletOptional.isEmpty())
            throw new WalletException("Wallet could not be found id:" + walletId);
        return walletOptional.get();
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(wallet.getId());
        if (walletOptional.isEmpty())
            throw new WalletException("Wallet could not be updated,not found id:" + wallet.getId());
        return this.walletJpaRepository.save(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if (walletOptional.isEmpty())
            throw new WalletException("Wallet could not be Deleted,not found id:" + walletId);
        WalletDto foundWallet = walletOptional.get();
        this.walletJpaRepository.delete(foundWallet);
        return foundWallet;
    }



    @Override
    public List<WalletDto> getAllWallets() {
        return this.walletJpaRepository.findAll();
    }

}