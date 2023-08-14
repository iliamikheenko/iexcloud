CREATE TABLE stockInfo.price_history (
    id bigserial primary key,
    company_id bigint ,
    price double precision,
    date_time timestamp,
    foreign key (company_id) references stockInfo.company(id)
);