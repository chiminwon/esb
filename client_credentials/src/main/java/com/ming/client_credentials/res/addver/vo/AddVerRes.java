package com.ming.client_credentials.res.addver.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddVerRes {

  @JsonProperty("messageCode")
  private String messageCode;

  @JsonProperty("addr")
  private AddressVO addressVO;

  @JsonProperty("addressSuggestions")
  private List<AddressSuggestionVO> addSugVOList;

  @JsonProperty("resultCd")
  private String resultCd;
}
