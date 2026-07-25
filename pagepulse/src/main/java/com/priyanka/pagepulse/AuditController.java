package com.priyanka.pagepulse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.Cacheable;


@RestController
@RequestMapping("/api")
public class AuditController {

    @Value("${cache.window}")
    private long cacheWindow;

    private final Map<String, CacheEntry> cache = new HashMap<>();
    private final Map<String,List<Long>>requestLog = new HashMap<>();
    private final Map<String,List<Long>>responseLog = new HashMap<>();

    @Cacheable(value = "auditCatch",key = "#request.url")
    @PostMapping("/audit")
    public AuditResponse audit(@Valid @RequestBody AuditRequest request) {

        String client = "default";

        long now = System.currentTimeMillis();

        requestLog.putIfAbsent(client, new ArrayList<>());

        List<Long> requests = requestLog.get(client);


        requests.removeIf(time -> now - time > 60000);


        if (requests.size() >= 5) {
            return new AuditResponse(
                    request.getUrl(),
                    429,
                    false,
                    0,
                    "Too many requests. Please wait a minute."
            );
        }

        requests.add(now);

        CacheEntry entry = cache.get(request.getUrl());

        if (entry != null) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - entry.getTimestamp() < cacheWindow) {
                return entry.getResponse();
            }
        }

        long startTime = System.currentTimeMillis();

        try {
            URL url = new URL(request.getUrl());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            long endTime = System.currentTimeMillis();

            AuditResponse response = new AuditResponse(
                    request.getUrl(),
                    status,
                    true,
                    endTime - startTime,
                    "Website is reachable"
            );

            cache.put(
                    request.getUrl(),
                    new CacheEntry(response, System.currentTimeMillis())
            );

            return response;

        } catch (Exception e) {

            long endTime = System.currentTimeMillis();

            String message = "Website is unreachable.";

            if (e.getMessage() != null && e.getMessage().contains("no protocol")) {
                message = "Please enter a valid URL starting with http:// or https://";
            }

            AuditResponse response = new AuditResponse(
                    request.getUrl(),
                    500,
                    false,
                    endTime - startTime,
                    message
            );

            cache.put(
                    request.getUrl(),
                    new CacheEntry(response, System.currentTimeMillis())
            );

            return response;
        }
    }
}