alter table if exists users_groups 
       add constraint fk_groups_users_groups
       foreign key (group_id) 
       references groups(group_id);