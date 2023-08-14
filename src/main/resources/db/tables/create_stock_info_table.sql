CREATE TABLE stockInfo.stock_info (
    symbol varchar(10) primary key ,
    company_Id bigserial,
    company_name varchar(255),
    latest_Price double precision,
    previous_Close double precision,
    change_Percent double precision,
    foreign key (company_id) references stockinfo.company(id)
);