package ru.vtb.marketplace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.marketplace.pojo.BodyPhoto;
import ru.vtb.marketplace.pojo.BrandInfo;
import ru.vtb.marketplace.pojo.CarBody;
import ru.vtb.marketplace.pojo.CarInfo;
import ru.vtb.marketplace.pojo.CarModel;
import ru.vtb.marketplace.pojo.Marketplace;

/**
 * @author denis-panin
 */
@Service
@Slf4j
public class MarketplaceService {
    private final VtbApiClient apiClient;
    private Map<String, CarInfo> carInfoByAlias;

    @Autowired
    public MarketplaceService(VtbApiClient apiClient) {
        this.apiClient = apiClient;
        this.carInfoByAlias = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        updateCarInfo();
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(this::updateCarInfo, 0, 10, TimeUnit.SECONDS);
    }

    public Optional<CarInfo> getCarInfo(String carName) {
        String alias = MarketplaceUtils.NAME_TO_ALIAS.getOrDefault(carName, "");
        return Optional.ofNullable(carInfoByAlias.get(alias));
    }

    private void updateCarInfo() {
        Marketplace marketplace = apiClient.getMarketplace();

        HashMap<String, CarInfo> carInfoByAlias = new HashMap<>();

        for (BrandInfo brand : marketplace.list) {
            for (CarModel model : brand.models) {

                List<CarBody> sortedBodyInfo = model.bodies.stream()
                        .sorted(MarketplaceUtils.bodyPriorityComparator)
                        .collect(Collectors.toList());

                List<String> bodies = sortedBodyInfo.stream().map(b -> b.title).collect(Collectors.toList());
                List<String> bodiesByPriority = sortedBodyInfo.stream().map(b -> b.alias).collect(Collectors.toList());
                List<String> bodyPhotoLinks = getBodyPhotoLinks(bodiesByPriority, model);

                CarInfo carInfo = new CarInfo(
                        brand.title,
                        brand.logo,
                        model.title,
                        bodyPhotoLinks,
                        model.minPrice,
                        bodies);

                carInfoByAlias.put(model.alias, carInfo);
            }
        }

        this.carInfoByAlias = carInfoByAlias;
    }

    private List<String> getBodyPhotoLinks(List<String> bodiesByPriority, CarModel model) {
        ArrayList<String> bodyPhotoLinks = new ArrayList<>();

        Optional<Map<String, BodyPhoto>> renderPhotoO = model.renderPhotos.values().stream().findFirst();

        if (renderPhotoO.isEmpty()) {
            return List.of();
        };

        Map<String, BodyPhoto> renderPhoto = renderPhotoO.get();
        Optional<String> bodyAliasWithPhotosO = bodiesByPriority.stream().filter(renderPhoto::containsKey).findFirst();

        if (bodyAliasWithPhotosO.isEmpty()) {
            return List.of();
        }

        String bodyAliasWithPhotos = bodyAliasWithPhotosO.get();

        for (Map<String, BodyPhoto> bodyPhoto : model.renderPhotos.values()) {
            if (bodyPhoto.containsKey(bodyAliasWithPhotos)) {
                bodyPhotoLinks.add(bodyPhoto.get(bodyAliasWithPhotos).url);
            }
        }

        return bodyPhotoLinks;
    }
}
