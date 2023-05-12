CREATE DATABASE IF NOT EXISTS democracy ;
    
    alter table event
       drop
       foreign key FKowpvlsmggvquw270favnoa4i4


    alter table event
       drop
       foreign key FKowpvlsmggvquw270favnoa4i4
                     :

    alter table event
       drop
       foreign key FKfchs57bvo0x3fr4ib1tvpeee5

    alter table event
       drop
       foreign key FKfchs57bvo0x3fr4ib1tvpeee5
                       :

    alter table event
       drop
       foreign key FK5plbnrqyrs3lv9t45udytlbx6

    alter table event
       drop
       foreign key FK5plbnrqyrs3lv9t45udytlbx6

    alter table player
       drop
       foreign key FK8095bt0vv5capccv9870ln2n

    alter table player
       drop
       foreign key FK8095bt0vv5capccv9870ln2n

    alter table refresh_token
       drop
       foreign key FKfgk1klcib7i15utalmcqo7krt

    alter table refresh_token
       drop
       foreign key FKfgk1klcib7i15utalmcqo7krt

    alter table state
       drop
       foreign key FKldfkois3n7a25co9uio2ebgdi

    alter table state
       drop
       foreign key FKldfkois3n7a25co9uio2ebgdi

    alter table user_roles
       drop
       foreign key FKh8ciramu9cc9q3qcqiv4ue8a6

    alter table user_roles
       drop
       foreign key FKh8ciramu9cc9q3qcqiv4ue8a6

    alter table user_roles
       drop
       foreign key FK55itppkw3i07do3h7qoclqd4k

    alter table user_roles
       drop
       foreign key FK55itppkw3i07do3h7qoclqd4k

    drop table if exists current_game_instance

    drop table if exists current_game_instance

    drop table if exists event

    drop table if exists event

    drop table if exists game

    drop table if exists game

    drop table if exists hibernate_sequence

    drop table if exists hibernate_sequence

    drop table if exists leader

    drop table if exists leader

    drop table if exists like_history

    drop table if exists like_history

    drop table if exists media

    drop table if exists media

    drop table if exists player

    drop table if exists player

    drop table if exists player_history

    drop table if exists player_history

    drop table if exists refresh_token

    drop table if exists refresh_token

    drop table if exists roles

    drop table if exists roles

    drop table if exists state

    drop table if exists state

    drop table if exists user

    drop table if exists user

    drop table if exists user_roles

    drop table if exists user_roles

    drop table if exists view_history

    drop table if exists view_history

    create table current_game_instance (
       id bigint not null auto_increment,
        current_no_count bigint,
        current_yes_count bigint,
        game_id bigint,
        player_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table current_game_instance (
       id bigint not null auto_increment,
        current_no_count bigint,
        current_yes_count bigint,
        game_id bigint,
        player_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table event (
       id bigint not null auto_increment,
        event_state_id bigint not null,
        leader_id bigint not null,
        report_state_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table event (
       id bigint not null auto_increment,
        event_state_id bigint not null,
        leader_id bigint not null,
        report_state_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table game (
       game_id bigint not null auto_increment,
        leader_id integer,
        primary key (game_id)
    ) engine=InnoDB

    create table game (
       game_id bigint not null auto_increment,
        leader_id integer,
        primary key (game_id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table leader (
       id bigint not null auto_increment,
        primary key (id)
    ) engine=InnoDB

    create table leader (
       id bigint not null auto_increment,
        primary key (id)
    ) engine=InnoDB

    create table like_history (
       id bigint not null auto_increment,
        created_at datetime(6),
        post_id bigint,
        updated_at datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table like_history (
       id bigint not null auto_increment,
        created_at datetime(6),
        post_id bigint,
        updated_at datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table media (
       id bigint not null auto_increment,
        created_at datetime(6),
        like_count bigint,
        media_source integer,
        source_url varchar(255),
        thumbnail_url varchar(255),
        updated_at datetime(6),
        user_id bigint,
        view_count bigint,
        primary key (id)
    ) engine=InnoDB

    create table media (
       id bigint not null auto_increment,
        created_at datetime(6),
        like_count bigint,
        media_source integer,
        source_url varchar(255),
        thumbnail_url varchar(255),
        updated_at datetime(6),
        user_id bigint,
        view_count bigint,
        primary key (id)
    ) engine=InnoDB

    create table player (
       id bigint not null auto_increment,
        free_media_enabled bit,
        player_name varchar(255),
        game_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table player (
       id bigint not null auto_increment,
        free_media_enabled bit,
        player_name varchar(255),
        game_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table player_history (
       id bigint not null auto_increment,
        current_game_instance_id bigint,
        player_id bigint,
        watched_event bigint,
        watched_state bigint,
        primary key (id)
    ) engine=InnoDB

    create table player_history (
       id bigint not null auto_increment,
        current_game_instance_id bigint,
        player_id bigint,
        watched_event bigint,
        watched_state bigint,
        primary key (id)
    ) engine=InnoDB

    create table refresh_token (
       id bigint not null,
        expiry_date datetime(6) not null,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table refresh_token (
       id bigint not null,
        expiry_date datetime(6) not null,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table roles (
       id integer not null auto_increment,
        name varchar(20),
        primary key (id)
    ) engine=InnoDB

    create table roles (
       id integer not null auto_increment,
        name varchar(20),
        primary key (id)
    ) engine=InnoDB

    create table state (
       id bigint not null auto_increment,
        camp varchar(255),
        type varchar(255),
        url varchar(255),
        event_state_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table state (
       id bigint not null auto_increment,
        camp varchar(255),
        type varchar(255),
        url varchar(255),
        event_state_id bigint not null,
        primary key (id)
    ) engine=InnoDB

    create table user (
       id bigint not null auto_increment,
        account_non_expired bit,
        account_non_locked bit,
        credentials_non_expired bit,
        email varchar(50),
        follower_count bigint,
        phone_number varchar(255),
        password varchar(120),
        profile_image varchar(255),
        user_name varchar(20),
        primary key (id)
    ) engine=InnoDB

    create table user (
       id bigint not null auto_increment,
        account_non_expired bit,
        account_non_locked bit,
        credentials_non_expired bit,
        email varchar(50),
        follower_count bigint,
        phone_number varchar(255),
        password varchar(120),
        profile_image varchar(255),
        user_name varchar(20),
        primary key (id)
    ) engine=InnoDB

    create table user_roles (
       user_id bigint not null,
        role_id integer not null,
        primary key (user_id, role_id)
    ) engine=InnoDB

    create table user_roles (
       user_id bigint not null,
        role_id integer not null,
        primary key (user_id, role_id)
    ) engine=InnoDB

    create table view_history (
       id bigint not null auto_increment,
        created_at datetime(6),
        post_id bigint,
        updated_at datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table view_history (
       id bigint not null auto_increment,
        created_at datetime(6),
        post_id bigint,
        updated_at datetime(6),
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    alter table refresh_token
       add constraint UK_r4k4edos30bx9neoq81mdvwph unique (token)

    alter table refresh_token
       add constraint UK_r4k4edos30bx9neoq81mdvwph unique (token)

    alter table user
       add constraint UKlqjrcobrh9jc8wpcar64q1bfh unique (user_name)

    alter table user
       add constraint UKlqjrcobrh9jc8wpcar64q1bfh unique (user_name)

    alter table user
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table user
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table user
       add constraint UK_4bgmpi98dylab6qdvf9xyaxu4 unique (phone_number)

    alter table user
       add constraint UK_4bgmpi98dylab6qdvf9xyaxu4 unique (phone_number)

    alter table event
       add constraint FKowpvlsmggvquw270favnoa4i4
       foreign key (event_state_id)
       references state (id)

    alter table event
       add constraint FKowpvlsmggvquw270favnoa4i4
       foreign key (event_state_id)
       references state (id)

    alter table event
       add constraint FKfchs57bvo0x3fr4ib1tvpeee5
       foreign key (leader_id)
       references leader (id)

    alter table event
       add constraint FKfchs57bvo0x3fr4ib1tvpeee5
       foreign key (leader_id)
       references leader (id)

    alter table event
       add constraint FK5plbnrqyrs3lv9t45udytlbx6
       foreign key (report_state_id)
       references state (id)

    alter table event
       add constraint FK5plbnrqyrs3lv9t45udytlbx6
       foreign key (report_state_id)
       references state (id)

    alter table player
       add constraint FK8095bt0vv5capccv9870ln2n
       foreign key (game_id)
       references game (game_id)

    alter table player
       add constraint FK8095bt0vv5capccv9870ln2n
       foreign key (game_id)
       references game (game_id)

    alter table refresh_token
       add constraint FKfgk1klcib7i15utalmcqo7krt
       foreign key (user_id)
       references user (id)

    alter table refresh_token
       add constraint FKfgk1klcib7i15utalmcqo7krt
       foreign key (user_id)
       references user (id)

    alter table state
       add constraint FKldfkois3n7a25co9uio2ebgdi
       foreign key (event_state_id)
       references event (id)

    alter table state
       add constraint FKldfkois3n7a25co9uio2ebgdi
       foreign key (event_state_id)
       references event (id)

    alter table user_roles
       add constraint FKh8ciramu9cc9q3qcqiv4ue8a6
       foreign key (role_id)
       references roles (id)

    alter table user_roles
       add constraint FKh8ciramu9cc9q3qcqiv4ue8a6
       foreign key (role_id)
       references roles (id)

    alter table user_roles
       add constraint FK55itppkw3i07do3h7qoclqd4k
       foreign key (user_id)
       references user (id)

    alter table user_roles
       add constraint FK55itppkw3i07do3h7qoclqd4k
       foreign key (user_id)
       references user (id)