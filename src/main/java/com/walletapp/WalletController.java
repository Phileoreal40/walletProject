package com.walletapp;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value ="/v1")
@CrossOrigin(value = "http://localhost:4200/")
public class WalletController {

    @Autowired
    private WalletService walletService ;

    @RequestMapping(method = RequestMethod.GET,value = "/")

   // @GetMapping("/")
    public String greet(){
        return " Welcome to wallet app.";
    }



    @GetMapping("/wallet/{id}")
    public WalletDto getWalletById(@PathVariable Integer id) throws WalletException {
        return walletService.getWalletById(id);
    }
    @PostMapping("/wallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    public WalletDto addResource(@Valid @RequestBody WalletDto wallet) throws WalletException {
        return walletService.registerWallet(wallet);
    }
    @PutMapping("/wallet")
    public WalletDto replaceResource(@Valid @RequestBody WalletDto wallet) throws WalletException {
        return walletService.updateWallet(wallet);
    }
    @DeleteMapping("/wallet/{walletId}")
    public WalletDto deleteResource(@PathVariable("walletId") Integer walletId) throws WalletException {
     return walletService.deleteWalletById(walletId);
    }

    @PatchMapping("/wallet/{id}/name/{walletName}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
        public String updateResourceName(@PathVariable("id")Integer walletId,@PathVariable("walletName")String walletName) {
        return  "Patch !"+walletId+":"+walletName;
    }
    @GetMapping("wallet")
    public List<WalletDto> getAllEmployees(){
        return this.walletService.getAllWallets();
    }

    @Autowired
    private WalletJpaRepository walletJpaRepository;
    @GetMapping("wallets/name/{name}")
    public List<WalletDto> getAllWalletsHavingName(@PathVariable("name") String name){
        return this.walletJpaRepository.findByName(name);
    }
    @GetMapping("wallets/contain/{name}")
    public List<WalletDto> getAllWalletsContainingName(@PathVariable("name") String name){
        return this.walletJpaRepository.findByNameContaining(name);
    }

    @GetMapping("wallets/balance/{minbalance}/{maxbalance}")
    public List<WalletDto> findAllWalletsHavingBalanceBetween(@PathVariable("minBalance") Double minBalance,
                                                                 @PathVariable("maxBalance")Double maxBalance){
        return this.walletJpaRepository.findByBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);

    }

    @GetMapping("custom/wallets")
    public List<WalletDto> findAllWallets(){
        return this.walletJpaRepository.getAllWallets();
    }

    @GetMapping("custom/wallets/{name}")
    public List<WalletDto> findAllwalletsHavingName(@PathVariable("name") String name){
        return this.walletJpaRepository.getAllwalletsHavingNameLike("%"+name+"%");
    }



}
