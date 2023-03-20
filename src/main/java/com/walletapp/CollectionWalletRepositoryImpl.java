package com.walletapp;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CollectionWalletRepositoryImpl implements CollectionWalletRepository {

    Map<Integer, WalletDto> walletDtoMap = new HashMap<>();


    public WalletDto createWallet(WalletDto wallet) {
        this.walletDtoMap.put(wallet.getId(), wallet);
        return wallet;
    }


    public WalletDto getWalletById(Integer id) {
        return walletDtoMap.get(id);
    }


    public WalletDto updateWallet(WalletDto walletDto) {
       walletDtoMap.replace(walletDto.getId(), walletDto);
       return walletDto;
    }


    public WalletDto deleteWalletById(Integer walletId) {

       walletDtoMap.remove(walletId);
       return walletDtoMap.get(walletId);
    }
    public Collection<WalletDto> getAllWallets() {

        return  this.walletDtoMap.values();
    }
}
