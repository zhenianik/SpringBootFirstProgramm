delete from user_role;
delete from usr;

insert into usr(id, username, password, active)
values (1,'zhenia','$2a$08$37uQOvGW1sqQmfbuNaeCjuI4dNQRJmW9sSBjegza7QKaVSjySxJhq',true),
       (2, 'larisa', '$2a$08$37uQOvGW1sqQmfbuNaeCjuI4dNQRJmW9sSBjegza7QKaVSjySxJhq.oFRzC', true);

insert into user_role(user_id, roles) values
(1, 'ADMIN'), (1, 'USER'),
(2, 'USER');