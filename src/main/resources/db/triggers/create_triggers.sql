create trigger insert_price_history_trigger
    after insert on stockinfo.stock_info
    for each row
    execute function stockinfo.insert_price_history();

create trigger update_price_history_trigger
    after update on stockInfo.stock_info
    for each row
    when (OLD.latest_price is distinct from NEW.latest_Price)
    execute function stockinfo.update_price_history();