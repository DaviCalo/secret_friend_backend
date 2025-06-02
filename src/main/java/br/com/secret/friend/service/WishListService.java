package br.com.secret.friend.service;

import br.com.secret.friend.dto.giftDTO.WishListRequesterDTO;
import br.com.secret.friend.dto.giftDTO.WishListResponseDTO;
import br.com.secret.friend.infra.exceptions.GroupNotFoundException;
import br.com.secret.friend.infra.exceptions.UserNotFoundException;
import br.com.secret.friend.model.*;
import br.com.secret.friend.repository.GiftRepository;
import br.com.secret.friend.repository.UserGroupRepository;
import br.com.secret.friend.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Transactional
    public WishListResponseDTO createWishList(WishListRequesterDTO wishListDto) {
        Gift gift = giftRepository.findById(wishListDto.giftId())
                .orElseThrow(() -> new UserNotFoundException("Invalid gift"));
        UserGroup userGroup = userGroupRepository.findById(wishListDto.userGroupId())
                .orElseThrow(() -> new GroupNotFoundException("Invalid userGroup"));

        WishList wishList = wishListRepository.save(new WishList(gift, userGroup));
        return new WishListResponseDTO(wishList);
    }

    public WishListResponseDTO getByIdWishList(Long wishListID) {
        WishList wishList = wishListRepository.findById(wishListID)
                .orElseThrow(() -> new GroupNotFoundException("Invalid group"));
        return new WishListResponseDTO(wishList);
    }
}
