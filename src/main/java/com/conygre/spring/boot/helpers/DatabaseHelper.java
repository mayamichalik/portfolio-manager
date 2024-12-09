package com.conygre.spring.boot.helpers;

import com.conygre.spring.boot.entities.Stock;
import com.conygre.spring.boot.entities.InfoStocks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.*;

@Service
public class DatabaseHelper {
    private static final String API_URL = "https://api.twelvedata.com/";
    private static final String AV_API_URL = "https://www.alphavantage.co/query?";
    private static final String API_KEY = Dotenv.configure().load().get("API_KEY");
    private static final String AV_API_KEY = Dotenv.configure().load().get("AV_API_KEY");
    private static Logger logger = LogManager.getLogger(DatabaseHelper.class);
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public void fetchDataForStock(String stock) {
        String[] commands = {"python", "src/python/pythonScript.py", stock};
        executePythonScript(commands);
    }

    private void executePythonScript(String[] commands) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            logger.info("Here is the result of the command:\n");
            String s;
            JSONArray stockTimeSeries = null;
            while ((s = stdInput.readLine()) != null) {
                logger.info(s);
                stockTimeSeries = new JSONArray(s);
            }
            logger.info(stockTimeSeries);

            logger.info("Here is the error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                logger.info(s);
            }

            proc.waitFor();
        } catch (Exception e) {
            logger.error("Failed to fetch data");
        }
    }

    public List<Stock> fetchAllStocks() {
        String url = String.format("%sstocks?apikey=%s",
                DatabaseHelper.API_URL, DatabaseHelper.API_KEY);
        logger.info(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        List<Stock> stocks = fetchStocksResult(request);
        return stocks;
    }
    public List<InfoStocks> fetchTimeSeries(Map<String, String> params) {
        String url = formatTimeSeriesUrl(params);

        logger.info(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        String symbol = params.get("symbol");
        List<InfoStocks> result = fetchTimeSeriesResult(request, symbol);
        return result;
    }

    public Insights fetchInsights(String symbol) {
        String url = String.format("%sfunction=OVERVIEW&apikey=%s&symbol=%s",
                DatabaseHelper.AV_API_URL, DatabaseHelper.AV_API_KEY, symbol);
        logger.info(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        Insights result = fetchInsightsResult(request);
        return result;
    }

    public StockPrice fetchLiveStockPrice(String symbol) {
        String url = String.format("%sprice?apikey=%s&symbol=%s",
                DatabaseHelper.API_URL, DatabaseHelper.API_KEY, symbol);
        logger.info(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        StockPrice result = fetchLivePriceResult(request, symbol);
        return result;
    }

    private List<Stock> fetchStocksResult(HttpRequest request) {
        List<Stock> result = new ArrayList<>();
        try {
            result = convertResultToStocks(fetchResult(request, "data"));
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private List<InfoStocks> fetchTimeSeriesResult(HttpRequest request, String symbol) {
        List<InfoStocks> result = new ArrayList<>();
        try {
            result = convertResultToInfoStock(fetchResult(request, "values"));
        } catch (Exception e) {
            logger.error(e);
        }
        result.forEach((a) -> a.setTicker_symbol(symbol));
        return result;
    }

    private StockPrice fetchLivePriceResult(HttpRequest request, String symbol) {
        StockPrice result = null;
        try {
            String response = sendRequest(request);
            result = gson.fromJson(response, StockPrice.class);
            result.setSymbol(symbol);
        } catch (Exception e ){
            logger.error(e);
        }
        return result;
    }

    private Insights fetchInsightsResult(HttpRequest request) {
        Insights result = null;
        try {
            String response = sendRequest(request);
            result = gson.fromJson(response, Insights.class);
        } catch (Exception e ){
            logger.error(e);
        }
        return result;
    }

    private String fetchResult(HttpRequest request, String key) throws IOException, InterruptedException {
        String response = sendRequest(request);
        JSONObject obj = new JSONObject(response);

        String result = obj.get(key).toString();
        return result;
    }

    private String sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String formatTimeSeriesUrl(Map<String, String> params) {
        String url = String.format("%stime_series?apikey=%s&format=JSON&type=stock",
                DatabaseHelper.API_URL, DatabaseHelper.API_KEY);

        for (Entry<String, String> pair : params.entrySet()) {
            url += "&"+pair.getKey()+"="+pair.getValue();
        }
        return url;
    }

    private List<Stock> convertResultToStocks(String response) {
        Stock[] result = gson.fromJson(response, Stock[].class);
        return Arrays.stream(result).toList();
    }

    private List<InfoStocks> convertResultToInfoStock(String response) {
        InfoStocks[] result = gson.fromJson(response, InfoStocks[].class);
        return Arrays.stream(result).toList();
    }
}
