package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialPageRequest extends PageRequest {
    
	public enum SearchItem {
		NONE,
		MATERIAL,
		TAG,
		MAKER,
	}
	
	/** 検索キー */
	private SearchItem searchItem;
	
    /** 検索値 */
    private String value;
}
