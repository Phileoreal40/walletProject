package com.walletapp;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CollectionWalletRepositoryImpl implements CollectionWalletRepository {

    Map<Integer, WalletDto> walletDtoMap = new HashMap<>();

    @Override
    public WalletDto addWallet(WalletDto wallet) {
        this.walletDtoMap.put(wallet.getId(), wallet);
        return wallet;
    }

    @Override
    public WalletDto getWallet(Integer id) {
        return walletDtoMap.get(id);
    }

    @Override
    public WalletDto updateWallet(WalletDto walletDto) {
        return walletDtoMap.replace(walletDto.getId(), walletDto);
    }

    @Override
    public WalletDto deleteWallet(Integer walletId) {

        return this.walletDtoMap.remove(walletId);
    }

}
