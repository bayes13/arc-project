package org.acme.model;

import java.io.Serializable;

public class BaseHttpModel<T> implements Serializable {
    private PageMetaData pageMetaData;

    private T body;

    public BaseHttpModel() {
    }

    public PageMetaData getPageMetaData() {
        return pageMetaData;
    }

    public void setPageMetaData(PageMetaData pageMetaData) {
        this.pageMetaData = pageMetaData;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
