package com.zma.highload.course;

public class Measurement {
    private long datasetLength;
    private long insert;
    private long search;
    private long delete;

    public Measurement(long datasetLength, long insert, long search, long delete) {
        this.datasetLength = datasetLength;
        this.insert = insert;
        this.search = search;
        this.delete = delete;
    }

    @Override
    public String toString() {
        return datasetLength +
                "," + insert +
                "," + search +
                "," + delete;
    }
}
