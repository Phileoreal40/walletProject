package com.walletapp;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
@RestController
@RequestMapping(value ="/v1/wallet/")
@CrossOrigin(origins = "http://localhost:4200/")
public class WalletController {

    @Autowired
   WalletService walletService ;
    @PostMapping("/wallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    WalletDto addWallet(@Valid @RequestBody WalletDto wallet) {
        return walletService.createWallet(wallet);
    }

    @GetMapping("/{id}")
     WalletDto getWalletById(@PathVariable Integer id) throws WalletException {
        return walletService.getWalletByID(id);
    }
    @PutMapping("/")
     WalletDto updateWallet( @RequestBody WalletDto wallet) throws WalletException {
        return walletService.updateWallet(wallet);
    }
    @DeleteMapping("/{id}")
    WalletDto deleteWallet(@PathVariable Integer id) throws WalletException {
        return walletService.deleteWalletById(id);
    }
    @PatchMapping("/addFund/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
 Double addFunds(@PathVariable Integer id, @RequestParam Double balance) throws WalletException {
        return  walletService.withdrawFunds(id,balance);
    }
    @PatchMapping("/tranferFunds/{fromId}/{toId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    String fundTranfer(Integer fromId, Integer toId, Double amount) throws WalletException{

        return walletService.tranfersFunds(fromId,toId,amount);
    }
    @GetMapping("wallets")
    public Collection<WalletDto> getAllWallets(){
        return this.walletService.getAllWallets();
    }

//
//    @Autowired
//    private WalletJpaRepository walletJpaRepository;
//    @GetMapping("wallets/name/{name}")
//    public List<WalletDto> getAllWalletsHavingName(@PathVariable("name") String name){
//        return this.walletJpaRepository.findByName(name);
//    }
//    @GetMapping("wallets/contain/{name}")
//    public List<WalletDto> getAllWalletsContainingName(@PathVariable("name") String name){
//        return this.walletJpaRepository.findByNameContaining(name);
//    }
//
//    @GetMapping("wallets/balance/{minbalance}/{maxbalance}")
//    public List<WalletDto> findAllWalletsHavingBalanceBetween(@PathVariable("minBalance") Double minBalance,
//                                                                 @PathVariable("maxBalance")Double maxBalance){
//        return this.walletJpaRepository.findByBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);
//
//    }
//
//    @GetMapping("custom/wallets")
//    public List<WalletDto> findAllWallets(){
//        return this.walletJpaRepository.getAllWallets();
//    }
//
//    @GetMapping("custom/wallets/{name}")
//    public List<WalletDto> findAllwalletsHavingName(@PathVariable("name") String name){
//        return this.walletJpaRepository.getAllwalletsHavingNameLike("%"+name+"%");
//    }



}
