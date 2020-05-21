package com.ming.noauth.addver.controller;

import com.ming.noauth.addver.vo.AddVerReq;
import com.ming.noauth.addver.vo.AddVerRes;
import com.ming.noauth.addver.vo.AddressSuggestionVO;
import com.ming.noauth.addver.vo.AddressVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddVerController {

    public static final String EXACTMATCH = "ExactMatch";
    public static final String APPROXIMATEMATCH = "ApproximateMatch";
    public static final String NOMATCH = "NoMatch";
    public static final String GOOD = "GOOD";

    @PostMapping("/verify")
    public AddVerRes verify(@RequestBody AddVerReq addVerReq) {

        AddVerRes addVerRes = new AddVerRes();
        System.out.println("RequestBody is : " + addVerReq);
        if (null != addVerReq && null != addVerReq.getAddressVO()) {
            AddressVO addressVO = addVerReq.getAddressVO();
            String addr1 = addressVO.getAddr1();
            if (null != addr1) {
                switch (addr1) {
                    case "1 S Roselle Rd":
                        addVerRes.setMessageCode(EXACTMATCH);
                        addVerRes.setAddressVO(addressVO);
                        break;
                    case "2 S Roselle Rd":
                        List<AddressSuggestionVO> addSugVOList = new ArrayList<>();
                        AddressSuggestionVO addSugVO = new AddressSuggestionVO();
                        for (int i = 1; i < 6; i++) {
                            addSugVO.setAddressRange("1501");
                            addSugVO.setPreDirection("S");
                            addSugVO.setStreetName("WOLF");
                            addSugVO.setStreetType("RD");
                            addSugVO.setStreetType("RD");
                            addSugVO.setSuiteName("APT");
                            addSugVO.setSuiteRange(i + "");
                            addSugVO.setCity("Prospect heights");
                            addSugVO.setStateProvCd("IL");
                            addSugVO.setPostalCode("60070");
                            addSugVO.setPlus4("1373");
                            addSugVOList.add(addSugVO);
                        }
                        addVerRes.setMessageCode(APPROXIMATEMATCH);
                        addVerRes.setAddSugVOList(addSugVOList);
                        break;
                    default:
                        addVerRes.setMessageCode(NOMATCH);
                        addVerRes.setResultCd(GOOD);
                        break;
                }
            }
        }
        System.out.println("ResponseBody is : " + addVerRes);
        return addVerRes;
    }
}
