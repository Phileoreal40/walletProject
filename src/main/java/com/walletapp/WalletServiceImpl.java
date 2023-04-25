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
    public WalletDto createWallet(WalletDto newWallet) {
        return this.walletJpaRepository.save(newWallet);
    }

    @Override
    public WalletDto  getWalletByID(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if (walletOptional.isEmpty()){
            throw new WalletException("Wallet of id " + walletId + " could not be found !");
    }
        return walletOptional.get();
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(wallet.getId());
        if (walletOptional.isEmpty())
            throw new WalletException("Wallet could not be updated,not found ");
        return this.walletJpaRepository.save(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletJpaRepository.findById(walletId);
        if (walletOptional.isEmpty())
            throw new WalletException("Wallet of id " + walletId + " could not be found !");
        WalletDto foundWallet = walletOptional.get();
        this.walletJpaRepository.delete(foundWallet);
        return foundWallet;
    }

    @Override
    public Double addFunds(Integer walletId, Double amount) throws WalletException {
        Optional<WalletDto> walletDtoOptional = this.walletJpaRepository.findById(walletId);
        if ( walletDtoOptional.isEmpty()){
            throw new WalletException("Wallet of id " + walletId + " could not be found !");

        }
        WalletDto foundWallet = walletDtoOptional.get();
        Double newBalance = foundWallet.getBalance() + amount;
        foundWallet.setBalance(newBalance);
        this.walletJpaRepository.save(foundWallet);
        return newBalance;
    }

    @Override
    public Double withdrawFunds(Integer walletId, Double amount) throws WalletException {
        Optional<WalletDto> walletDtoOptional = this.walletJpaRepository.findById(walletId);
        if ( walletDtoOptional.isEmpty()){
            throw new WalletException("Wallet of id " + walletId + " could not be found !");

        }
        WalletDto foundWallet = walletDtoOptional.get();
        Double newBalance = foundWallet.getBalance() - amount;
        foundWallet.setBalance(newBalance);
        this.walletJpaRepository.save(foundWallet);
        return newBalance;
    }
    @Override
    public String tranfersFunds(Integer fromId, Integer toId, Double amount) throws WalletException {
        Optional<WalletDto> fromWalletDtoOptional = this.walletJpaRepository.findById(fromId);
        Optional<WalletDto> toWalletDtoOptional = this.walletJpaRepository.findById(toId);
        if ( fromWalletDtoOptional.isEmpty() || toWalletDtoOptional.isEmpty()){
            throw new WalletException("Enter valid ID");

        }
        WalletDto fromWallet =fromWalletDtoOptional.get();
        WalletDto toWallet = toWalletDtoOptional.get();

        Double fromWalletBalance = fromWallet.getBalance() - amount ;
        Double toWalletBalance = toWallet.getBalance() + amount;

        fromWallet.setBalance(fromWalletBalance);
        toWallet.setBalance(toWalletBalance);

        this.walletJpaRepository.save(fromWallet);
        this.walletJpaRepository.save(toWallet);
        return "Tranfer Complete" + "\n Balance of : \n "+fromId + " : "+fromWalletBalance
                +" \n " + toId + " : " + toWalletBalance;
    }



    @Override
    public Collection<WalletDto> getAllWallets() {
        return this.walletJpaRepository.findAll();
    }

    @Override
    public WalletDto registerWallet(WalletDto wallet) {
        return null;
    }

    @Override
    public void getWalletById(int i) {

    }
}

