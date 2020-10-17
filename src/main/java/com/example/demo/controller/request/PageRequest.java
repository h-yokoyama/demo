package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {
    
	/** ページ */
	private int page;
	
    /** サイズ */
    private int size;
}
