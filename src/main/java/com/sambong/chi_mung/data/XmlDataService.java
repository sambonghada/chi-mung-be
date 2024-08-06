package com.sambong.chi_mung.data;
//
//import com.sambong.chi_mung.proverb.entity.Proverb;
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.w3c.dom.*;
//import javax.xml.parsers.*;
//import java.io.*;
//import java.util.ArrayList;
//import org.springframework.stereotype.Service;
//
//@Component
//@RequiredArgsConstructor
//public class XmlDataService {
//
//    private final Init init;
//    @PostConstruct
//    public void start() {
//        init.processXmlAndSave("./src/main/java/com/sambong/chi_mung/data/proverb.xml");
//    }
//
//    @Component
//    @RequiredArgsConstructor
//    static class Init {
//
//        private final EntityManager entityManager;
//
//        public void processXmlAndSave(String path) {
////            String path = "./src/main/java/com/sambong/chi_mung/data/proverb.xml";
//            try {
//                // XML 파일 파싱
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                Document doc = builder.parse(new File(path));
//
//                // 루트 엘리먼트 가져오기
//                Element rootElement = doc.getDocumentElement();
//
//                // 특정 엘리먼트 찾기 (예: <item> 태그의 데이터 저장)
//                NodeList items = rootElement.getElementsByTagName("items");
//                List<Proverb> entityList = new ArrayList<>();
//
//                for (int i = 0; i < items.getLength(); i++) {
//                    Element item = (Element) items.item(i);
//
//                    // 원하는 정보 추출 (예: id, name, value 등)
//                    String content = item.getElementsByTagName("name").item(0).getTextContent();
//                    int idx = item.getElementsByTagName("contents").item(0).getTextContent()
//                        .indexOf(".");
//
//                    String answer = item.getElementsByTagName("contents").item(0).getTextContent()
//                        .substring(0, idx + 1);
//                    String helpText = item.getElementsByTagName("contents").item(0).getTextContent()
//                        .substring(idx + 1);
//
//                    // 엔티티 객체 생성 및 값 설정
//                    Proverb entity = Proverb.builder().content(content).answer(answer)
//                        .helpText(helpText)
//                        .build();
//                    entityList.add(entity);
//                }
//
//                // JPA를 통해 엔티티 객체 저장
//                for (Proverb entity : entityList) {
//                    entityManager.persist(entity);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    }

import com.sambong.chi_mung.proverb.entity.Proverb;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Component
@RequiredArgsConstructor
public class XmlDataService {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional(transactionManager = "transactionManager")
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager entityManager;

        public void dbInit() {
            init();
       }

        public void init() {
            String path = "./src/main/java/com/sambong/chi_mung/data/proverb.xml";
            try {
                // XML 파일 파싱
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new File(path));

                // 루트 엘리먼트 가져오기
                Element rootElement = doc.getDocumentElement();

                // 특정 엘리먼트 찾기 (예: <item> 태그의 데이터 저장)
                NodeList items = rootElement.getElementsByTagName("item");
                List<Proverb> entityList = new ArrayList<>();

                for (int i = 0; i < items.getLength(); i++) {
                    Element item = (Element) items.item(i);

                    // 원하는 정보 추출 (예: id, name, value 등)
                    String content = item.getElementsByTagName("name").item(0).getTextContent();
                    int idx = item.getElementsByTagName("contents").item(0).getTextContent()
                        .indexOf(".");

                    String text = item.getElementsByTagName("contents").item(0)
                        .getTextContent();
                    String answer = text.substring(0, idx + 1);
                    int max = text.length();
                    if(max >1000){
                        System.out.println("max :"+max);
                        max = 1000;
                    }
                    String helpText = text.substring(idx + 1, max);
                    String soundUrl = item.getElementsByTagName("soundUrl").item(0)
                        .getTextContent();

                    // 엔티티 객체 생성 및 값 설정
                    Proverb entity = Proverb.builder().content(content).answer(answer)
                        .helpText(helpText)
                        .soundUrl(soundUrl)
                        .build();
                    entityList.add(entity);
                }

                // JPA를 통해 엔티티 객체 저장
                for (Proverb entity : entityList) {
                    entityManager.persist(entity);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        }



}
