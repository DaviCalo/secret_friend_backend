alter table if exists wish_lists 
       add constraint fk_wish_lists_gifts
       foreign key (gift_id) 
       references gifts(gift_id);