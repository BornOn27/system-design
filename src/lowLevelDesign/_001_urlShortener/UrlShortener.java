package lowLevelDesign._001_urlShortener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UrlShortener {
    private String PREFIX = "https://short.lk/";
    Map<String, String> LRU_INTERNAL_MEMORY = new HashMap<>();
    Map<String, String> REDIS_CACHE_MEMORY = new HashMap<>();
    Map<String, String> SQL_DB = new HashMap<>();

    public static void main(String[] args) {
        String userInput = "this.is.my.link";
        String shortenedUrl = new UrlShortener().getShortenedUrl(userInput);
        System.out.println("Shortened Url :: " + shortenedUrl);

//        try {
//            String userUrl = new UrlShortener().getUserUrl(shortenedUrl);
//
//            System.out.println("User Url :: "+userUrl);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    public String getShortenedUrl(String userInput){
        if(SQL_DB.get(userInput) != null){
            return SQL_DB.get(userInput);
        }
        Integer randomNumber = new Random().nextInt(999999999);

        String answer = PREFIX+base62Encoder(randomNumber);

        SQL_DB.put(userInput, answer);

        return answer;
    }

    private String base62Encoder(Integer number){
        String hash = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String result = "";

        while (number > 0){
            result = result + hash.charAt(number % hash.length());
            number = number / hash.length();
        }

        return result;
    }

    public String getUserUrl(String shortenedUrl) throws Exception {
        if(LRU_INTERNAL_MEMORY.get(shortenedUrl) != null){
            return LRU_INTERNAL_MEMORY.get(shortenedUrl);
        } else if(REDIS_CACHE_MEMORY.get(shortenedUrl) != null){
            return REDIS_CACHE_MEMORY.get(shortenedUrl);
        } else if(SQL_DB.get(shortenedUrl) != null){
            return SQL_DB.get(shortenedUrl);
        }

        throw new Exception("This is 400 BAD_REQUEST");
    }
}
