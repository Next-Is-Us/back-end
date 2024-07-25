package com.nextisus.project.util.loader;

import com.nextisus.project.domain.Hospital;
import com.nextisus.project.repository.HospitalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ApiDataLoader implements CommandLineRunner {

    private final HospitalRepository hospitalRepository;
    private final RestTemplate restTemplate;

    public ApiDataLoader(HospitalRepository hospitalRepository, RestTemplate restTemplate) {
        this.hospitalRepository = hospitalRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String serviceKey = "o3EWDRotdgYjzHFQ8iYGsXMaC43KutGQFNvAXJGKLQe7aPyiXdNJUplheNRqzASQC2W2GwdXXTyjCl8yw%2BGOig%3D%3D";
        String pageNo = "1";
        String numOfRows = "10";
        String encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8.toString());
        String apiURL = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncFullDown?ServiceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows;

        String response = restTemplate.getForObject(apiURL, String.class);

        if (response.contains("<returnAuthMsg>SERVICE_KEY_IS_NOT_REGISTERED_ERROR</returnAuthMsg>")) {
            log.error("API 서비스 키 오류: 서비스 키가 등록되지 않았습니다.");
            return;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(response)));

        doc.getDocumentElement().normalize();
        NodeList itemList = doc.getElementsByTagName("item");

        for (int i = 0; i < itemList.getLength(); i++) {
            Element item = (Element) itemList.item(i);

            String dutyAddr = getTagValue("dutyAddr", item);
            String dutyName = getTagValue("dutyName", item);
            String dutyTel = getTagValue("dutyTel1", item);

            log.info("주소: {}", dutyAddr);
            log.info("이름: {}", dutyName);
            log.info("전화번호: {}", dutyTel);

            Hospital hospital = Hospital.builder()
                    .hospitalName(dutyName)
                    .hospitalAddress(dutyAddr)
                    .hospitalTel(dutyTel)
                    .build();
            hospitalRepository.save(hospital);
        }
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        return (nodeList != null && nodeList.getLength() > 0) ? nodeList.item(0).getNodeValue() : "N/A";
    }
}
