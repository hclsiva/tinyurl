package study.tinyurl.bean;

import io.swagger.annotations.ApiModelProperty;

public class UrlCountInput {
    @ApiModelProperty(notes="No. of urls to be inserted. min = 1, Max = "+Integer.MAX_VALUE,name="urlCount",required = true,value="1")
    private int urlCount =0;

    @Override
    public String toString() {
        return "UrlCountInput{" +
                "urlCount='" + urlCount + '\'' +
                '}';
    }

    public int getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(int urlCount) {
        this.urlCount = urlCount;
    }
}
