package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialAddRequest {

    /** 材料名 */
    private String name;

    /** タグ1 */
    private String tag1;

    /** タグ2 */
    private String tag2;
    
    /** タグ */
    private String tag3;
    
    /** メーカー名 */
    private String makerName;
    
    /** メーカー担当者 */
    private String makerCharge;
    
    /** メーカー連絡先 */
    private String makerContact;
    
    /** 備考 */
    private String remarks;
}
