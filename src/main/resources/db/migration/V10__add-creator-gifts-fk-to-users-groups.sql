alter table if exists users_groups 
       add constraint fk_users_groups_gifts
       foreign key (gift_select_id) 
       references gifts(gift_id);