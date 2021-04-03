package study.tinyurl.bean;

import java.util.ArrayList;
import java.util.List;

public class TinyURLBeanList {
	private List<TinyURLBean> tinyURLBeanList = null;

	public List<TinyURLBean> getTinyURLBeanList() {
		return tinyURLBeanList;
	}

	public void setTinyURLBeanList(List<TinyURLBean> tinyURLBeanList) {
		this.tinyURLBeanList = tinyURLBeanList;
	}

	public TinyURLBeanList() {
		this.tinyURLBeanList = new ArrayList<TinyURLBean>(0);
	}

	public void add(TinyURLBean tinyurlbean) {
		this.tinyURLBeanList.add(tinyurlbean);
	}
	
}
