package zb.Team.showticket.show.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zb.Team.showticket.show.model.DateInput;
import zb.Team.showticket.show.model.ShowDetail;
import zb.Team.showticket.show.model.ShowList;
import zb.Team.showticket.show.repository.ShowDetailRepository;
import zb.Team.showticket.show.repository.ShowListRepository;

import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final ShowDetailRepository showDetailRepository;
    private final ShowListRepository showListRepository;


    @GetMapping("/api/show/{mt20id}")
    public String showDetail(@PathVariable String mt20id) {

        String service_code = "23c56f648806470fb24ff55ab6e80037";

        String api_url = String.format("http://www.kopis.or.kr/openApi/restful/pblprfr/%s?service=%s", mt20id, service_code);
        String apiResult = "";
        System.out.println(mt20id);
        Map<String, Object> map;


        try {
            URI uri = new URI(String.format(api_url));

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            String result = restTemplate.getForObject(uri, String.class);

            apiResult = result;

            org.json.JSONObject xmlJSONObj = XML.toJSONObject(result.toString());
            String xmlJSONObjString = xmlJSONObj.toString();

            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});
            Map<String, Object> dataResponse = (Map<String, Object>) map.get("dbs");
            Map<String, Object> dbs = (Map<String, Object>) dataResponse.get("db");

            ShowDetail showDetail = new ShowDetail();
            showDetail.setMt20id((String) dbs.get("mt20id"));
            showDetail.setPrfnm((String) dbs.get("prfnm"));
            showDetail.setPrfpdfrom((String) dbs.get("prfpdfrom"));
            showDetail.setPrfpdto((String) dbs.get("prfpdto"));
            showDetail.setFcltynm((String) dbs.get("fcltynm"));
            showDetail.setPrfcast((String) dbs.get("prfcast"));
            showDetail.setPrfcast((String) dbs.get("prfcrew"));
            showDetail.setPrfruntime((String) dbs.get("prfruntime"));
            showDetail.setPrfage((String) dbs.get("prfage"));
            showDetail.setEntrpsnm((String) dbs.get("entrpsnm"));
            showDetail.setPcseguidance((String) dbs.get("pcseguidance"));
            showDetail.setPoster((String) dbs.get("poster"));
            showDetail.setGenrenm((String) dbs.get("genrenm"));
            showDetail.setPrfstate((String) dbs.get("prfstate"));
            showDetail.setOpenrun((String) dbs.get("openrun"));
            showDetail.setMt10id((String) dbs.get("mt10id"));
            showDetail.setDtguidance((String) dbs.get("dtguidance"));

            showDetailRepository.save(showDetail);
            //System.out.println(dbs);

            Optional<ShowDetail> List = showDetailRepository.findByMt20id(mt20id);

            ShowDetail detail = List.get();
            //System.out.println(detail.getMt20id());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiResult;
    }

    @GetMapping("/api/show/list")
    public String showList(@RequestBody DateInput dateInput) {

        Integer stdate = dateInput.StartDate();
        Integer eddate = dateInput.EndDate();
        Integer cpage = dateInput.cpageCount();
        String service_code = "23c56f648806470fb24ff55ab6e80037";
        String api_url = String.format("http://www.kopis.or.kr/openApi/restful/pblprfr?service=%s&stdate=%d&eddate=%d&rows=20&cpage=%d", service_code, stdate, eddate, cpage);
        String apiResult = "";

        try {
            URI uri = new URI(String.format(api_url));

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            String result = restTemplate.getForObject(uri, String.class);

            apiResult = result;

            JSONObject xmlJSONObj = XML.toJSONObject(result.toString());
            String xmlJSONObjString = xmlJSONObj.toString();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map;
            map = mapper.readValue(xmlJSONObjString, new TypeReference<>() {
            });
            Map<String, Object> dataResponse = (Map<String, Object>) map.get("dbs");
            ArrayList<Map<String, Object>> dbs = (ArrayList<Map<String, Object>>) dataResponse.get("db");

            System.out.println(dbs);


            List<ShowList> dbsList = new ArrayList<>();
            for (int i = 0; i < dbs.size(); i++) {

                ShowList showList = new ShowList();

                showList.setMt20id((String) dbs.get(i).get("mt20id"));
                showList.setPrfnm((String) dbs.get(i).get("prfnm"));
                showList.setGenrenm((String) dbs.get(i).get("genrenm"));
                showList.setPrfstate((String) dbs.get(i).get("prfstate"));
                showList.setPrfpdfrom((String) dbs.get(i).get("prfpdfrom"));
                showList.setPrfpdto((String) dbs.get(i).get("prfpdto"));
                showList.setPoster((String) dbs.get(i).get("poster"));
                showList.setFcltynm((String) dbs.get(i).get("fcltynm"));
                showList.setOpenrun((String) dbs.get(i).get("openrun"));

                dbsList.add(showList);
                showListRepository.save(showList);

                Optional<ShowList> list = showListRepository.findByMt20id(showList.getMt20id());
                ShowList sList = list.get();
                System.out.println(sList.getMt20id());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResult;
    }


}
