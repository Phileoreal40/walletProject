package com.walletapp;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

@RestController
@RequestMapping(value ="/v1/wallet/")
@CrossOrigin(origins = "http://localhost:4200/")




public class WalletController {

    @Autowired
   WalletService walletService ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("user")
    public String registerNewUser(@RequestBody Users user){
        // check for email already exists throw exception
        this.userRepository.save(user);
        return "User registration success.";
    }

    @PostMapping("login")
    public Users login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {

        // Create a user service and log in method
        Users user = this.userRepository.findByEmail(loginDto.getEmail());
        if(user == null) throw new Exception("User does not exists");
        if(! user.getPassword().equals(loginDto.getPassword()))
            throw new Exception("User password does not match");

        // JWT util
        String issuer = loginDto.getEmail();
        Date expiry= new Date(System.currentTimeMillis() + (1000 * 60 * 60 ));
        String jwt = Jwts.builder().setIssuer(issuer).setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256,"secretKey").compact();

        Cookie cookie = new Cookie("jwt",jwt);
        user.setJwt(jwt);
        response.addCookie(cookie);
        //return jwt;
        return user;
    }
    @PostMapping("logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt","");
        response.addCookie(cookie);
        return "Logout Success !";
    }

    @GetMapping("user")
    public Users getUser(@CookieValue("jwt") String jwt) throws Exception {
        if(jwt == null)
            throw new Exception("Unauthenticated !");
        // Jwt Util class
        Claims claim=null;
        String email=null;
        try{
            claim = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(jwt).getBody();
            email = claim.getIssuer();

        }
        catch (ExpiredJwtException e){
            throw new Exception("JWT got Expired please log in again.");

        }
        catch (SignatureException e){
            throw new Exception("JWT Signature Exception.");
        }
        catch (Exception e){
            throw  new Exception("Unauthenticated !");
        }

        return this.userRepository.findByEmail(email);

    }

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

    @PatchMapping("/withdrawFunds/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Double withdrawFunds(@PathVariable Integer id, @RequestParam Double balance) throws WalletException {
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

    @GetMapping("userinfo")
    public Users getUserInfo(@RequestHeader("Authorization") String bearerToken ) throws Exception {
        String jwt = bearerToken.substring(7);
        String email = jwtUtil.validateJwtAndGetUserEmail(jwt);
        return this.userRepository.findByEmail(email);

    }

}
