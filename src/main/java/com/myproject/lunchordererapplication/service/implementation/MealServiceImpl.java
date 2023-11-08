package com.myproject.lunchordererapplication.service.implementation;

import static com.myproject.lunchordererapplication.infrastructure.Constants.DAILY_MENU_URL;

import com.myproject.lunchordererapplication.model.Meal;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.myproject.lunchordererapplication.repository.MealRepository;
import com.myproject.lunchordererapplication.service.MealService;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<Meal> getDailyMenu() {
        log.info("Fetching daily menu.");
        List<Meal> dailyMenu = mealRepository.findAll();
        if (dailyMenu.isEmpty()) {
            log.info("Daily menu has not been fetched yet. Crawling for web...");
            dailyMenu = crawlAndPopulateDailyMenuFromWeb();
        }
        return dailyMenu;
    }

    @Override
    public List<Meal> getMealsByIds(List<Long> mealIds) {
        log.info("Fetching meals by following IDs: {}.", mealIds);
        return mealRepository.findAllById(mealIds);
    }

    public List<Meal> crawlAndPopulateDailyMenuFromWeb() {

        log.info("MealService::crawlAndPopulateDailyMenuFromWeb()");

        final ResponseEntity<String> response = restTemplate.getForEntity(DAILY_MENU_URL, String.class);
        final String htmlContent = response.getBody();

        assert htmlContent != null: "No HTML content found!";
        final Document document = Jsoup.parse(htmlContent);

        final Elements tableRows = document.select("table tbody tr");

        List<Meal> dailyMenu = new ArrayList<>();

        for (int i = 1; i < tableRows.size(); i++) {
            final Element row = tableRows.get(i);
            final Elements cells = row.select("td");

            if (cells.size() == 3) {
                final Long mealId = Long.parseLong(cells.get(0).text());
                final String mealName = cells.get(1).text();
                final double price = Double.parseDouble(cells.get(2).text().replace("€", "").replace(",", "."));

                Meal meal = new Meal();
                meal.setMealId(mealId);
                meal.setMealName(mealName);
                meal.setPrice(price);

                Meal savedMeal = mealRepository.save(meal);
                log.info("Saved meal \"{}\" to the daily menu.", savedMeal.getMealName());
                dailyMenu.add(savedMeal);
            }
        }
        return dailyMenu;
    }

}
