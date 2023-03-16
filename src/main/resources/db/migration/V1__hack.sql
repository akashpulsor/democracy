CREATE DATABASE IF NOT EXISTS democracy ;
    
create table if not exists event (
       id bigint not null auto_increment,
        event_state_id bigint not null,
        leader_id bigint not null,
        propaganda_free_media_id bigint not null,
        report_state_id bigint not null,
        primary key (id)
    ) engine=InnoDB

create table if not exists leader (
       id bigint not null auto_increment,
        primary key (id)
) engine=InnoDB

    create table if not exists propaganda_free_media (
       id bigint not null auto_increment,
        free_media_id bigint not null,
        propaganda_media_id bigint not null,
        primary key (id)
    ) engine=InnoDB


    create table if not exists state (
       id bigint not null auto_increment,
        camp varchar(255),
        type varchar(255),
        url varchar(255),
        primary key (id)
    ) engine=InnoDB


    alter table event
       add constraint FKowpvlsmggvquw270favnoa4i4
       foreign key (event_state_id)
       references state (id)


    alter table event
       add constraint FKfchs57bvo0x3fr4ib1tvpeee5
       foreign key (leader_id)
       references leader (id)


    alter table event
       add constraint FKbdv26vs9xq9odhp1gj2jk54yd
       foreign key (propaganda_free_media_id)
       references propaganda_free_media (id)


    alter table event
       add constraint FK5plbnrqyrs3lv9t45udytlbx6
       foreign key (report_state_id)
       references state (id)


    alter table propaganda_free_media
       add constraint FKr4pcslew5tr9kc8if3rlm97o1
       foreign key (free_media_id)
       references state (id)

    
    alter table propaganda_free_media 
       add constraint FK7uxhnfp3nxj7qnjcs8e67rnxu 
       foreign key (propaganda_media_id) 
       references state (id)