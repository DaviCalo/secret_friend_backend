alter table if exists letters
       add constraint fk_letters_users_groups
       foreign key (user_group_id)
       references users_groups(user_group_id);