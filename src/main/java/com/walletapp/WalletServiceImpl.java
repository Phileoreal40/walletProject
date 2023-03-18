package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Optional;

@Service

public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletJpaRepository walletJpaRepository;

    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException {
        return this.walletJpaRepository.save(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletDtoOptional = this.walletJpaRepository.findById(walletId);
        if (walletDtoOptional.isEmpty())
            throw new WalletException("Wallet could not be found id:" + walletId);
        return walletDtoOptional.get();
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        Optional<WalletDto> walletDtoOptional = this.walletJpaRepository.findById(wallet.getId());
        if (walletDtoOptional.isEmpty())
            throw new WalletException("Wallet could not be updated,not found id:" + wallet.getId());
        return this.walletJpaRepository.save(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletDtoOptional = this.walletJpaRepository.findById(walletId);
        if (walletDtoOptional.isEmpty())
            throw new WalletException("Wallet could not be Deleted,not found id:" + walletId);
        WalletDto foundWallet = walletDtoOptional.get();
        this.walletJpaRepository.delete(foundWallet);
        return foundWallet;
    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        return null;
    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException {
        return null;
    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
        return null;
    }

    //    @Override
//    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
//        return null;
//    }
//
//    @Override
//    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException {
//        return null;
//    }
//
//    @Override
//    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
//        return null;
//    }
//
    @Override
    public Collection<WalletDto> getAllWallets() {
        return null;
    }
//}
}