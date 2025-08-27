package org.acme.model;


import java.util.List;

public class PageMetaData {
    private int index;
    private int size;
    private int totalData;
    List<Sort> sortList;

    public PageMetaData() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public List<Sort> getSortList() {
        return sortList;
    }

    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }

    public static class Sort {
        private String column;
        private String direction;

        public Sort() {
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }
}
