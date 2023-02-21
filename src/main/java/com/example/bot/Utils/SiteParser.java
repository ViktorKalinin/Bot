package com.example.bot.Utils;

import com.example.bot.model.ZodiacSign;
import com.example.bot.repositories.ZodiacSignRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class SiteParser {
    private final ZodiacSignRepository zodiacSignRepository;
    public SiteParser(ZodiacSignRepository zodiacSignRepository) {
        this.zodiacSignRepository = zodiacSignRepository;
    }

    public void setInfo() throws IOException {
        String key;
        String value;
            Document doc = Jsoup.connect("https://74.ru/horoscope/daily/")
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get();

        Elements elements = doc.select("article[class=IGRa5]");
        for (Element element : elements) {
            key = element.select("h3").text();
            value = element.select("div[class=BDPZt KUbeq]").text();
            ZodiacSign zodiac = new ZodiacSign(key, value);
            zodiacSignRepository.save(zodiac);
        }
    }
}
