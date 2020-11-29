insert into usr (id, username, password, active)
values (1,'zhenia','$2a$08$37uQOvGW1sqQmfbuNaeCjuI4dNQRJmW9sSBjegza7QKaVSjySxJhq',true);

insert into user_role (roles, user_id)
values ('USER', 1), ('ADMIN', 1);