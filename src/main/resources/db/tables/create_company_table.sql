create table stockInfo.company (
    id bigserial primary key,
    symbol varchar(10) unique not null,
    is_enabled boolean not null
);