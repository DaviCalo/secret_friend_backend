package br.com.secret.friend.controller;

import br.com.secret.friend.dto.giftDTO.WishListRequesterDTO;
import br.com.secret.friend.dto.giftDTO.WishListResponseDTO;
import br.com.secret.friend.service.WishListService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/wishList")
@SecurityRequirement(name = "bearer-key")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @PostMapping()
    public ResponseEntity<WishListResponseDTO> registerWishList(@RequestBody @Valid WishListRequesterDTO wishListDto, UriComponentsBuilder uriComponentsBuilder) {
        WishListResponseDTO responseDto = wishListService.createWishList(wishListDto);
        URI uri = uriComponentsBuilder.path("/api/wishList/{id}").buildAndExpand(responseDto.wishListId()).toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/{wishListID}")
    public ResponseEntity<WishListResponseDTO> registerWishList(@PathVariable("wishListID") Long wishListID) {
        WishListResponseDTO responseDto = wishListService.getByIdWishList(wishListID);
        return ResponseEntity.ok(responseDto);
    }
}
