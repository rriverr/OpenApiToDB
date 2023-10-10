package com.example.apitest.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    TestService testService;

    static final String SERVICE_KEY = "";
    static final String API_URL = "http://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List?ServiceKey=" + SERVICE_KEY + "&type=json&pageNo=1&numOfRows=1000";

    @GetMapping("test")
    public String RequestOpenApi() {
        String result = "";
        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject response = (JSONObject) jsonParser.parse(result);
            JSONArray jsonArray = (JSONArray) response.get("TsunamiShelter");
            JSONObject rowJsonObject = (JSONObject) jsonArray.get(1);
            JSONArray rowJsonArray = (JSONArray) rowJsonObject.get("row");
            for (int i = 0; i < rowJsonArray.size(); i++) {
                JSONObject item = (JSONObject) rowJsonArray.get(i);

                String address = (String) item.get("address");
                String shel_nm = (String) item.get("shel_nm");
                Long lenth = (Long) item.get("lenth");
                String seismic = (String) item.get("seismic");
                String sido_name = (String) item.get("sido_name");
                Double lon = (Double) item.get("lon");
                String sigungu_name = (String) item.get("sigungu_name");
                String shel_div_type = (String) item.get("shel_div_type");
                Long id = (Long) item.get("id");
                String remarks = (String) item.get("remarks");
                Double lat = (Double) item.get("lat");
                Long shel_av = (Long) item.get("shel_av");
                Long height = (Long) item.get("height");

                Datas data = new Datas(address, shel_nm, lenth, seismic, sido_name, lon, sigungu_name, shel_div_type, id, remarks, lat, shel_av, height);
                testService.save(data);
            }
            return "성공 >_<";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "실패...인듯해...";
    }
}
