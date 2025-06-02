alter table if exists users_groups 
       add constraint fk_users_groups_users_recipient
       foreign key (recipient_user_id) 
       references users(user_id);