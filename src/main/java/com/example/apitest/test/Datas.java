package com.example.apitest.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="datas")
public class Datas {
  private String address;
  private String shel_nm;
  private Long lenth;
  private String seismic;
  private String sido_name;
  private Double lon;
  private String sigungu_name;
  private String shel_div_type;
  @Id
  private Long id;
  private String remarks;
  private Double lat;
  private Long shel_av;
  private Long height;

}
