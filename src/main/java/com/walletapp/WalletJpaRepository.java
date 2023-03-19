package com.walletapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletDto,Integer> {
    List<WalletDto> findByName(String name);

    List<WalletDto> findByNameContaining(String name);

    List<WalletDto> findByBalanceBetweenOrderByBalanceDesc(Double minBalance, Double maxBalance);
    List<WalletDto> findByBalanceBetweenOrderByNameAsc(Double minBalance, Double maxBalance);
    List<WalletDto> findByBalanceBetweenOrderByNameDesc(Double minBalance, Double maxBalance);
    @Query("SELECT wallet FROM WalletDto wallet")
    List<WalletDto> getAllWallets();
    @Query("SELECT wallet FROM WalletDto wallet WHERE wallet.name LIKE :name")
    List<WalletDto> getAllwalletsHavingNameLike(String name);
}
