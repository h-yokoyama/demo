package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "materials")
@Setter
@Getter
public class Material {

    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 材料名 */
    @Column(name = "name")
    private String name;

    /** タグ1 */
    @Column(name = "tag1")
    private String tag1;

    /** タグ2 */
    @Column(name = "tag2")
    private String tag2;
    
    /** タグ */
    @Column(name = "tag3")
    private String tag3;
    
    /** メーカー名 */
    @Column(name = "maker_name")
    private String makerName;
    
    /** メーカー担当者 */
    @Column(name = "maker_charge")
    private String makerCharge;
    
    /** メーカー連絡先 */
    @Column(name = "maker_contact")
    private String makerContact;
    
    /** 備考 */
    @Column(name = "remarks")
    private String remarks;
    
    public static Material newMaterial(String name, String tag1,String tag2, String tag3,String makerName,String makerCharge,String makerContact,String remarks) {
    	Material material = new Material();
    	material.setName(name);
    	material.setTag1(tag1);
    	material.setTag2(tag2);
    	material.setTag3(tag3);
    	material.setMakerName(makerName);
    	material.setMakerCharge(makerCharge);
    	material.setMakerContact(makerContact);
    	material.setRemarks(remarks);
    	return material;
    }
    
    public static Material newMaterial(Long id, String name, String tag1,String tag2, String tag3,String makerName,String makerCharge,String makerContact,String remarks) {
    	Material material = new Material();
    	material.setId(id);
    	material.setName(name);
    	material.setTag1(tag1);
    	material.setTag2(tag2);
    	material.setTag3(tag3);
    	material.setMakerName(makerName);
    	material.setMakerCharge(makerCharge);
    	material.setMakerContact(makerContact);
    	material.setRemarks(remarks);
    	return material;
    }
}