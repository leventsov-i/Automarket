package ru.vtb.marketplace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.marketplace.pojo.Dealer;

@Service
public class CarDealerService {
    private static final List<Dealer> DEALERS = List.of(
            new Dealer("https://topdiler.ru/wp-content/uploads/2017/11/Major-1.png", "Мэйджор Авто", "Москва, МКАД 47 км"),
            new Dealer("https://avilon.ru/upload/resize_cache/iblock/494/1100_209_1/494993e301a346c11f95dfe509995a59.jpg", "Авилон", "Волгоградский пр-т., 43, к. 3, Москва"),
            new Dealer("https://upload.wikimedia.org/wikipedia/commons/e/e8/LOGO_ROLF_2020.png", "Рольф", "Ленинградское шоссе, д. 63Б"));


    @Autowired
    public CarDealerService() {
    }

    public List<Dealer> getDealers(String carBrand) {
        return DEALERS;
    }
}
