package net.skhu.bum;

import java.util.ArrayList;
import java.util.List;

public class Seoul {

        String[][] seoul = {{"JONGNO","JUNGGU","YONGSAN","SEONGDONG","GWANGJIN","DDM","JUNGNANG",
                            "SEONGBUK","GANGBUK","DOBONG","NOWON","EP","SDM","MAPO","YANGCHEON","GANGSEO",
                            "GURO", "GEUMCHEON","YDP","GWANAK","SEOCHO","GANGNAM","SONGPA","GANGDONG","DONGJAK","ETC"},
                {"JONGNOADD","JUNGGUADD","YONGSANADD","SEONGDONGADD","GWANGJINADD","DDMADD","JUNGNANGADD",
                "SEONGBUKADD","GANGBUKADD","DOBONGADD","NOWONADD","EPADD","SDMADD","MAPOADD","YANGCHEONADD","GANGSEOADD",
                "GUROADD", "GEUMCHEONADD","YDPADD","GWANAKADD","SEOCHOADD","GANGNAMADD","SONGPAADD","GANGDONGADD","DONGJAKADD","ETCADD"},
                {"종로구", "중구","용산구","성동구","광진구","동대문구","중랑구","성북구","강북구","도봉구","노원구",
                "은평구","서대문구","마포구","양천구","강서구","구로구","금천구","영등포구","관악구","서초구","강남구",
                "송파구","강동구","동작구","기타"}};

    public String[][] set() {
        return seoul;
    }
    public List<String> getName() {
        List<String> list = new ArrayList<>();
        for(String temp : seoul[2] ){
            list.add(temp);
        }
        return list;
    }

}
