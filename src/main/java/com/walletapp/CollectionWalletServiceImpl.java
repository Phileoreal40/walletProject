package com.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
//@Component
//@Service
public class CollectionWalletServiceImpl implements WalletService{

 //  @Autowired
    private CollectionWalletRepository walletRepository;


    public WalletDto createWallet(WalletDto newWallet)  {

        return walletRepository.createWallet(newWallet);
    }

    @Override
    public WalletDto  getWalletByID(Integer walletId) throws WalletException {

        WalletDto temp = walletRepository.getWalletById(walletId);
        if(temp==null)
            throw new WalletException("Wallet id does not exists:");
        return temp;
    }
   WalletDto getWalletById(Integer WalletId){

        return walletRepository.getWalletById(WalletId);
    }
    public WalletDto updateWallet(WalletDto wallet) {
        return walletRepository.updateWallet(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        WalletDto foundwallet = walletRepository.getWalletById(walletId);
        if(foundwallet==null) {
        }
        this.walletRepository.deleteWalletById(walletId);
        return foundwallet;
    }
    public Double addFunds(Integer walletId,Double amount){
        WalletDto temp = walletRepository.getWalletById(walletId);
        Double newBalance = temp.getBalance() + amount;
        walletRepository.updateWallet(temp);
        return newBalance;
    }
    public Double withdrawFunds(Integer walletId,Double amount){
        WalletDto temp = walletRepository.getWalletById(walletId);
        Double newBalance = temp.getBalance() - amount;
        temp.setBalance(newBalance);
        walletRepository.updateWallet(temp);
        return newBalance;
    }




    public String tranfersFunds(Integer walletId, Integer told,Double amount){
        WalletDto temp = walletRepository.getWalletById(walletId);
        WalletDto toWallet = walletRepository.getWalletById(told);
        Double fromWalletBalance = temp.getBalance() - amount;
        temp.setBalance(fromWalletBalance);
        Double toWalletBalance = toWallet.getBalance() + amount;
        toWallet.setBalance(toWalletBalance);
        return "balance of" + temp.getId() + ":" + fromWalletBalance + "\n balance of" + toWallet.getId() + ":" + toWalletBalance;
    }
    public Collection<WalletDto> getAllWallets() {
        return this.walletRepository.getAllWallets();

    }






}
