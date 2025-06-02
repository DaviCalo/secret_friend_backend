package br.com.secret.friend.dto.giftDTO;

import br.com.secret.friend.model.WishList;

public record WishListResponseDTO(
        Long giftId,
        Long userGroupId,
        Long wishListId
) {

    public WishListResponseDTO(WishList wishList) {
        this(
                wishList.getGift().getIdGift(),
                wishList.getUserGroup().getIdUserGroup(),
                wishList.getIdWishList()
        );
    }
}
