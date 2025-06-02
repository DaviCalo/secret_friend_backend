alter table if exists groups 
       add constraint fk_groups_users
       foreign key (creator_user_id) 
       references users(user_id);