create or replace function stockinfo.insert_price_history()
returns trigger as $$
    begin
    insert into stockinfo.price_history (company_id, price, date_time)
    values (NEW.company_id, NEW.latest_Price, NOW());
    return NEW;
    end;
$$ language plpgsql;

create or replace function stockinfo.update_price_history()
returns trigger as $$
    begin
    insert into stockinfo.price_history (company_id, price, date_time)
    values (NEW.company_id, NEW.latest_Price, NOW());
    return new;
    end ;
$$ language plpgsql;