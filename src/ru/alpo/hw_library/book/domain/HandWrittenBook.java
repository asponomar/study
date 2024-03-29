package ru.alpo.hw_library.book.domain;

public class HandWrittenBook extends Book {
    private String writingInkType;

    public String getWritingInkType() {
        return writingInkType;
    }

    public void setWritingInkType(String writingInkType) {
        this.writingInkType = writingInkType;
    }

    @Override
    public String toString() {
        return "HandWrittenBook{" + super.toString() +
                " writingInkType = '" + writingInkType + '\'' +
                '}';
    }
}
