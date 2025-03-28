package com.example.cat_gallery;

import com.example.cat_gallery.catgallery.CatImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class CatController {

    private static final String API_KEY = "live_OsWQZJqkFlWFbMcOLNo7aWpHClqQeI83bcLZCNYNAgvLoNhkPDdtSRlJLQcy0CtX";
    private static final String API_URL = "https://api.thecatapi.com/v1/images/search?limit=6&size=small&api_key=" + API_KEY;
    private static final String BREEDS_URL = "https://api.thecatapi.com/v1/breeds?api_key=" + API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String getCats(@RequestParam(required = false) String breed, Model model) {
        String url = API_URL + (breed != null ? "&breed_ids=" + breed : "");

        CatImage[] catImages = restTemplate.getForObject(url, CatImage[].class);
        List<Map<String, Object>> breeds = Arrays.asList(restTemplate.getForObject(BREEDS_URL, Map[].class));

        model.addAttribute("catImages", catImages);
        model.addAttribute("breeds", breeds);
        return "gallery";
    }
}
