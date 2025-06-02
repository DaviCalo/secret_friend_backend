alter table if exists wish_lists 
       add constraint fk_wish_lists_users_groups
       foreign key (user_group_id) 
       references users_groups(user_group_id);